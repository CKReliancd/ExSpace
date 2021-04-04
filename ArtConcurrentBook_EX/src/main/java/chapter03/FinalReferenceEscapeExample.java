package chapter03;

public class FinalReferenceEscapeExample {

    final int i;
    static FinalReferenceEscapeExample obj;

    public static void main(String[] args) {
        Thread a线程 = new Thread(() -> {
            write();
        }, "A线程");
        Thread b线程 = new Thread(() -> {
            reader();
        }, "B线程");
        a线程.start();
        b线程.start();
    }

    public FinalReferenceEscapeExample() {
        //写final域
        i = 1;
        //this引用这里逸出
        obj = this;
    }

    public static void write() {
        System.out.println(Thread.currentThread().getName() + "进入write new对象");
        new FinalReferenceEscapeExample();
    }

    public static void reader() {
        System.out.println(Thread.currentThread().getName() + "进入reader");
        if (obj != null) {
            int temp = obj.i;
            System.out.println("temp：" + temp);
        }
    }
}
