package chapter01;

/**
 * @author Administrator
 * @Description
 * @Date 2021/1/19 16:28
 */
public class ConcurrencyTest {

    private static final long count = 1000000000l;

    public static void main(String[] args) throws InterruptedException {

//        concurrency();

        serial();
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }


    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread thread = new Thread(
                () -> {
                    int a = 0;
                    for (int i = 0; i < count; i++) {
                        a += 5;
                    }
                    System.out.println(a);
                }, "TA");
        thread.start();
        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
//        thread.join();
        System.out.println("concurrency : " + time + " ms , b = " + b);
    }

    private static void threadJoin() throws InterruptedException {
        System.out.println("MainThread run start");

        Thread threadA = new Thread(() -> {
            System.out.println("threadA run start.");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("threadA run finished.");
        }, "");

        threadA.join();

        threadA.start();

        Thread.sleep(1000);

        System.out.println("MainThread join before");

        System.out.println("MainThread run finished");
    }

}
