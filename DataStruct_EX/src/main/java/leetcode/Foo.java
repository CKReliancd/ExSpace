package leetcode;

import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class Foo {

//    private int firstJobDown = 0;
//
//    private int sencondeJobDown = 0;

    private volatile int firstJobDown = 0;

    private volatile int sencondeJobDown = 0;

//    private AtomicInteger firstJobDown = new AtomicInteger(0);
//
//    private AtomicInteger sencondeJobDown = new AtomicInteger(0);

    public Foo() {

    }

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();

        new Thread(() -> {
            try {
                foo.first(() -> System.out.println("first"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.second(() -> System.out.println("second"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.third(() -> System.out.println("third"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void first(Runnable printFirst) {
        System.out.println("第一个线程执行");
        printFirst.run();
        firstJobDown = firstJobDown + 1;
    }

    public void second(Runnable printSecond) {
        int i = 1;
        while (firstJobDown != 1) {
            i++;
        }
        System.out.println(MessageFormat.format("第二线程竞争:{0}次后执行", i));
        printSecond.run();
        sencondeJobDown = sencondeJobDown + 1;
    }

    public void third(Runnable printThird) {
        int i = 1;
        while (sencondeJobDown != 1) {
            i++;
        }
        System.out.println(MessageFormat.format("第三线程竞争:{0}次后执行", i));
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }


}
