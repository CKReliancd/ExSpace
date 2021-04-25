package chapter04.threadpool;

public interface Thread<Job extends Runnable> {

    // 执行一个job，这个Job需要实现Runnable
    void execute(Job joa);

    // 关闭线程池
    void shutDown();

    // 增加工作者线程
    void addWorkers(int num);

    // 减少工作者线程
    void removeWorks(int num);

    // 得到正在执行的任务数量
    int getJobSize();

}
