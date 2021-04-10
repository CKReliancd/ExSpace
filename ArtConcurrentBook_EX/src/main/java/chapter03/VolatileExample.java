package chapter03;

import java.util.Timer;

public class VolatileExample {

    int a = 0;
    volatile boolean flag = false;

    public static void main(String[] args) {

        final VolatileExample obj = new VolatileExample();

        new Thread(new Runnable() {
            public void run() {
                obj.read();
            }
        }, "BB").start();

        new Thread(new Runnable() {
            public void run() {
                obj.writer();
            }
        }, "AA").start();
    }

    public void writer() {
        System.out.println(Thread.currentThread().getName() + "线程进入，修改变量");
        a = 1;
        flag = true;
    }

    public void read() {
        System.out.println(Thread.currentThread().getName() + "线程进入，读取变量");
        int count = 0;
        while (!flag) {
            count++;
        }
        System.out.println("循环等待次数: " + count);
        if (flag) {
            int i = a;
            System.out.println("普通变量 i = " + i);
        }
    }


}
