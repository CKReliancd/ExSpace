package chapter02;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @Description
 * @Date 2021/3/18 19:34
 */
public class Count {
    private int i;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private void unsafeCount() {
        i++;
    }

    private void safeCount() {
        while (true) {
            int i = atomicInteger.get();
            boolean b = atomicInteger.compareAndSet(i, ++i);
            if (b) {
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Count count = new Count();

        List<Thread> threadList = Lists.newArrayList();

        long start = System.currentTimeMillis();
        System.out.println(start);

        for (int i = 1; i <= 100000; i++) {
            Thread t = new Thread(() -> {
                count.unsafeCount();
                count.safeCount();
            }, String.valueOf(i));

            threadList.add(t);
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        for (Thread thread : threadList) {
            thread.join();
        }

        System.out.println(count.i);
        System.out.println(count.atomicInteger.get());

        System.out.println(new Date(System.currentTimeMillis() - start));

    }


}
