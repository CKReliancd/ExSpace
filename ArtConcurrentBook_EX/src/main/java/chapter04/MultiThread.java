package chapter04;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.text.MessageFormat;

/**
 * @author Administrator
 * @Description
 * @Date 2021/4/9 10:41
 */
public class MultiThread {

    public static void main(String[] args) {

        //java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        //不需要获取同步的Monitor和syschronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

        //遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(MessageFormat.format("线程ID：{0}，线程名：{1}",
                    threadInfo.getThreadId(), threadInfo.getThreadName()));
        }

        //线程ID：6，线程名：Monitor Ctrl-Break

        //线程ID：5，线程名：Attach Listener

        //线程ID：4，线程名：Signal Dispatcher ：分发处理发送给JVM信号的线程

        //线程ID：3，线程名：Finalizer : 调用对象finalize方法的线程

        //线程ID：2，线程名：Reference Handler : 清除Reference的线程

        //线程ID：1，线程名：main : main线程，用户程序入口


    }


}
