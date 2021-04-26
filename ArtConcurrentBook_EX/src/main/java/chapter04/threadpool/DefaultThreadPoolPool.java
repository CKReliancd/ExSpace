package chapter04.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPoolPool<Job extends Runnable> implements ThreadPool<Job> {
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

    public DefaultThreadPoolPool() {
        initiallizeWorks(DEFAULT_WORKS_NUMBERS);
    }

    public DefaultThreadPoolPool(int num) {
        workerNum = num > MAX_WORKS_NUMBERS ?
                MAX_WORKS_NUMBERS :
                num < MIN_WORKER_NUMBERS ?
                        MIN_WORKER_NUMBERS : num;
    }

    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.addLast(job);
                //添加一个Job后，对工作队列jobs调用了其notify()方法，而不是notifyAll()方法，因为能够
                //确定有工作者线程被唤醒，这时使用notify()方法将会比notifyAll()方法获得更小的开销（避免
                //将等待队列中的线程全部移动到阻塞队列中）
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

    @Override
    public void removeWorks(int num) {
        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("beyond workNum");
            }
            //按照给定的数量停止Worker
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
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
     * 初始化工作者线程
     *
     * @param num
     */
    private void initiallizeWorks(int num) {

        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker,
                    "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }


    class Worker implements Runnable {
        //是否工作
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            // 感知到外部对WorkerThread的中断操作，返回
                            java.lang.Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    job.run();
                }
            }
        }

        public void shutDown() {
            running = false;
        }
    }
}
