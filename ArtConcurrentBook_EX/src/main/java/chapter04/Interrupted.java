package chapter04;

import java.util.concurrent.TimeUnit;

/**
 * 从Java的API中可以看到，许多声明抛出InterruptedException的方法(例如Thread.sleep(long millis)方法)
 * 在抛出InterruptedException之前，Java虚拟机会先将该线程的中断标志位清除，
 * 然后抛出InterruptedException，此时调用isInterrupted()方法将会返回false。
 */
public class Interrupted {

    public static void main(String[] args) throws InterruptedException {

        //sleep线程不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);

        //busy线程不停的运行
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        //main线程休眠五秒，让两个子线程充分的运行
        TimeUnit.SECONDS.sleep(5);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("sleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("busyThread interrupted is " + busyThread.isInterrupted());

        //sleepThread interrupted is false
        //busyThread interrupted is true
        //从结果可以看出，抛出InterruptedException的线程SleepThread，其中中断标志位被清除，
        //而一直忙碌着的BusyThread，中断标志位没有被清除


        //防止子线程立刻退出
        TimeUnit.SECONDS.sleep(2);
    }


    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
            }
        }
    }

}
