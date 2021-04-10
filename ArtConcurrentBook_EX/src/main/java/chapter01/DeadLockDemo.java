package chapter01;

/**
 * @author Administrator
 * @Description
 * @Date 2021/1/21 13:49
 */
public class DeadLockDemo {

    private static String A = "A";
    private static Integer B = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (A) {
                System.out.println("t1:???????A");
                try {
                    Thread.currentThread().sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("t1:?????B");
                }
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (B) {
                System.out.println("t2?????B");
                synchronized (A) {
                    System.out.println("t2?????A");
                }
            }
        }, "t2").start();
    }
}
