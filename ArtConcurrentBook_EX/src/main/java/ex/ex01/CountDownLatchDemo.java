package ex.ex01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

    private static int count = 5;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 1; i <= count; i++) {
            new Thread(() -> {
                new Service(countDownLatch).run();
            }, String.valueOf(i)).start();
        }
        System.out.println("main thread await.");
        countDownLatch.await();
        System.out.println("main thread finishes await.");
    }

    static class Service implements Runnable {
        private CountDownLatch latch;
        public Service(CountDownLatch latch) {
            this.latch = latch;
        }
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " excute " +
                        "task. ");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " finsih " +
                        "task. ");
            } catch (InterruptedException e) {
            } finally {
                latch.countDown();
            }
        }
    }


}
