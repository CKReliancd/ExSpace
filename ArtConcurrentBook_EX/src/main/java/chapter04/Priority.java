package chapter04;

/**
 * @author Administrator
 * @Description
 * @Date 2021/4/9 17:40
 */
public class Priority {

    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    public static void main(String[] args) {


    }

    static class Job implements Runnable {

        private int priority;
        private long jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {

            while (notStart) {



            }


        }
    }


}
