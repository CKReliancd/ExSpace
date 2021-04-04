package chapter03;

public class FinalExample {
    //普通变量
    int i;
    //final变量
    final int j;
    static FinalExample obj;

    public FinalExample() {
        //写普通域
        i = 1;
        //写final域
        j = 2;
    }

    public static void main(String[] args) {
        Thread b = new Thread(() -> {
            reader();
        }, "B");

        Thread a = new Thread(() -> {
            writer();
        }, "A");

        a.start();
        b.start();
    }

    /**
     * 线程A执行
     */
    public static void writer() {
        System.out.println("Thread.currentThread().getName():" + Thread.currentThread().getName() + "start");
        obj = new FinalExample();
    }

    /**
     * 线程B执行
     */
    public static void reader() {
        System.out.println("Thread.currentThread().getName():" + Thread.currentThread().getName() + "start");
        //读对象的引用
        FinalExample object = obj;
        //读普通域
        int a = object.i;
        System.out.println("普通变量a:" + a);
        //读final域
        int b = object.j;
        System.out.println("finalB:" + b);
    }

}
