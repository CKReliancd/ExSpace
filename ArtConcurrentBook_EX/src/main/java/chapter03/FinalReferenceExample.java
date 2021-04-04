package chapter03;

public class FinalReferenceExample {
    //引用类型
    final int[] intArray;
    static FinalReferenceExample obj;

    public static void main(String[] args) {

        Thread a线程 = new Thread(() -> {
            writeOne();
        }, "A线程");
        Thread b线程 = new Thread(() -> {
            writeTwo();
        }, "B线程");
        Thread c线程 = new Thread(() -> {
            reader();
        }, "C线程");
        a线程.start();
        b线程.start();
        c线程.start();
    }

    /**
     * 构造函数给 final域赋值
     */
    public FinalReferenceExample() {
        intArray = new int[1];
        intArray[0] = 1;
    }

    /**
     * 写线程A执行
     */
    public static void writeOne() {
        System.out.println(Thread.currentThread().getName() + "进入WriteOne");
        obj = new FinalReferenceExample();
    }

    /**
     * 写线程B执行
     */
    public static void writeTwo() {
        System.out.println(Thread.currentThread().getName() + "进入WriteTwo");
        obj.intArray[0] = 2;
    }

    /**
     * 读线程C执行
     */
    public static void reader() {
        System.out.println(Thread.currentThread().getName() + "进入Reader");
        if (obj != null) {
            int temp1 = obj.intArray[0];
            System.out.println("intArray[0] : " + temp1);
        }
    }


}
