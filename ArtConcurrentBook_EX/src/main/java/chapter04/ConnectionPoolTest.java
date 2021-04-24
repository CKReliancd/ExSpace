package chapter04;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest {

    // 线程数量，可以修改线程数量进行观察
    static int threadCount = 10;
    static ConnectionPool pool = new ConnectionPool(10);
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger noGot = new AtomicInteger();

        for (int i = 1; i <= 10; i++) {
            new Thread(new ConnectionRunner(count, got,
                    noGot), String.valueOf(i)).start();
        }
        start.countDown();
        // main线程调用使main线程处于等待状态
        // await()方法也并没有规定只能有一个线程执行该方法，
        // 如果多个线程同时执行await()方法，那么这几个线程都将处于等待状态，
        // 并且以共享模式享有同一个锁
        end.await();
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got  connection :" + noGot);
    }

    static class ConnectionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger noGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger noGot) {
            this.count = count;
            this.got = got;
            this.noGot = noGot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (count > 0) {
                try {
                    // 从线程池中获取连接，如果1000ms内无法获取到，将会返回null
                    // 分别统计连接获取的数量got和未获取到的数量notGot
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        noGot.incrementAndGet();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            // countDown()方法并没有规定一个线程只能调用一次，
            // 当同一个线程调用多次countDown()方法时，
            // 每次都会使计数器减一
            end.countDown();
        }
    }
}
