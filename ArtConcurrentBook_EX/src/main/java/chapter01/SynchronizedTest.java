package chapter01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @Description
 * @Date 2021/1/20 16:05
 */
public class SynchronizedTest {

    //???????
    static AtomicInteger i = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        final SynchronizedTest test = new SynchronizedTest();

        new Thread(test::method1).start();
        new Thread(test::method2).start();
    }

    public synchronized void method1() {
        System.out.println("method1 started");
        try {
            System.out.println("method1 execute");
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("method1 end");
    }

    public void method2() {
        System.out.println("method2 started");
        try {
            System.out.println("method2 execute");
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("method2 end");
    }

    /**
     * synchronized ???????????
     */
    public static void increase() {
        i.getAndIncrement();
    }

    private static void threadIncreaseInteger() throws InterruptedException {
        Thread aa = new Thread(() -> {
            for (int j = 0; j < 10000; j++) {
                increase();
            }
        }, "AA");

        Thread bb = new Thread(() -> {
            for (int j = 0; j < 10000; j++) {
                increase();
            }
        }, "BB");

        aa.start();
        bb.start();

        aa.join();
        bb.join();

        System.out.printf("i:%d\r\n", i.get());
    }

}
