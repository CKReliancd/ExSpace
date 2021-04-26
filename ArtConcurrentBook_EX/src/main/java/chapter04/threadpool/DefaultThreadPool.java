package chapter04.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程池的本质就是使用了一个线程安全的工作队列连接工作者线程和客户端
 * 线程，客户端线程将任务放入工作队列后便返回，而工作者线程则不断地从工作队列上取出
 * 工作并执行。当工作队列为空时，所有的工作者线程均等待在工作队列上，当有客户端提交了
 * 一个任务之后会通知任意一个工作者线程，随着大量的任务被提交，更多的工作者线程会被唤醒
 *
 * @param <Job>
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    // 线程池最大限制数
    private static final int MAX_WORKS_NUMBERS = 10;
    // 线程池默认的数量
    private static final int DEFAULT_WORKS_NUMBERS = 5;
    // 线程池最小的数量
    private static final int MIN_WORKER_NUMBERS = 1;
    // 这是一个工作列表，将会向里面添加工作
    private final LinkedList<Job> jobs = new LinkedList<>();
    // 工作者列表
    private final List<Worker> workers =
            Collections.synchronizedList(new ArrayList<Worker>());
    // 工作者线程的数量
    private int workerNum = DEFAULT_WORKS_NUMBERS;
    // 线程编号的生成
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initiallizeWorks(DEFAULT_WORKS_NUMBERS);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKS_NUMBERS ?
                MAX_WORKS_NUMBERS :
                num < MIN_WORKER_NUMBERS ?
                        MIN_WORKER_NUMBERS : num;
    }

    /**
     * 初始化工作者线程
     *
     * @param num
     */
    private void initiallizeWorks(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" +
                    threadNum.incrementAndGet());
            thread.start();
        }
    }

    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.addLast(job);
                /**
                 * 添加一个Job后，对工作队列jobs调用了其notify()方法，而不是notifyAll()方法，因为能够
                 * 确定有工作者线程被唤醒，这时使用notify()方法将会比notifyAll()方法获得更小的开销（避免
                 * 将等待队列中的线程全部移动到阻塞队列中）
                 */
                jobs.notify();
            }
        }
    }

    @Override
    public void shutDown() {
        for (Worker worker : workers) {
            worker.shutDown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            //限制新增的数量不能超过最大值
            if (num + this.workerNum > MAX_WORKS_NUMBERS) {
                num = MAX_WORKS_NUMBERS - this.workerNum;
            }
            initiallizeWorks(num);
            this.workerNum += num;
        }
    }

    /**
     * 这里结束线程的方法就是worker跑完run方法
     *
     * @param num
     */
    @Override
    public void removeWorks(int num) {
        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("beyond workNum");
            }
            // 按照给定的数量停止Worker
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    // running = false ,run方法跑完，线程结束
                    worker.shutDown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    /**
     * 从线程池的实现可以看到，当客户端调用execute(Job)方法时，会不断地向任务列表jobs中
     * 添加Job，而每个工作者线程会不断地从jobs上取出一个Job进行执行，当jobs为空时，工作者线
     * 程进入等待状态。
     */
    class Worker implements Runnable {
        //是否工作
        private volatile boolean running = true;

        @Override
        public void run() {
            /**
             * running = false ,run方法跑完，线程结束
             * running = true , run方法一直取工作列表的任务运行
             */
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            // 感知到外部对WorkerThread的中断操作，返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    // 从工作列表取一个工作运行
                    job.run();
                }
            }
        }

        public void shutDown() {
            running = false;
        }
    }
}
