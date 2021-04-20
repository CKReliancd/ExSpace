package chapter04;

import java.util.concurrent.TimeUnit;

public class Join {

    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            //每个线程拥有前一个线程的引用，需要等待前一个线程终止才能从等待中返回
            Thread thread = new Thread(new Domino(previous), i + "");
            thread.start();
            previous = thread;
        }
       try{TimeUnit.SECONDS.sleep(2);}catch (Exception e){e.printStackTrace();}
        System.out.println(Thread.currentThread().getName()+ " terminate.");

    }

    static class Domino implements Runnable {

        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }


}
