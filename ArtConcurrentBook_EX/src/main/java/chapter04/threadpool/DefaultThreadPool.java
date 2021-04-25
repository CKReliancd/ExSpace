package chapter04.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements Thread {
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


    /**
     * 初始化工作者线程
     *
     * @param num
     */
    private void initiallizeWorks(int num) {

        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            java.lang.Thread thread = new java.lang.Thread(worker,
                    "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    @Override
    public void execute(Runnable joa) {

    }

    @Override
    public void shutDown() {

    }

    @Override
    public void addWorkers(int num) {

    }

    @Override
    public void removeWorks(int num) {

    }

    @Override
    public int getJobSize() {
        return 0;
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
    }
}
