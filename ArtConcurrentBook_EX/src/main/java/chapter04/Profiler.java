package chapter04;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.TimeUnit;

public class Profiler {

    //第一次get()方法调用时会进行初始化(如果set方法没有调用)，每个线程会调用一次
    private static final ThreadLocal<Long> TIME_THREADLOCAL =
            new ThreadLocal<>();
//            ThreadLocal.withInitial(() -> System.currentTimeMillis());
//            new ThreadLocal<Long>() {
//                protected Long initialValue() {
//                    return System.currentTimeMillis();
//                }
//            };

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final Long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) {
        Profiler.begin();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }
        System.out.println("Cost: " + Profiler.end() + " mills");
    }


}
