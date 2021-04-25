package ex.ex01;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Administrator
 * @Description
 * @Date 2021/4/23 16:46
 */
public class Test {

    public static void main(String[] args) {

//        People people = new Student();
//
//        InvocationHandler handler = new WorkHandler(people);
//        People proxyInstance = (People) Proxy.newProxyInstance(people.getClass().getClassLoader(),
//                people.getClass().getInterfaces(),
//                handler
//        );
//
//        People p = proxyInstance.work("写代码").work("开会").work("上课");
//        System.out.println("返回打印的对象:" + p.getClass());
//        String time = proxyInstance.time();
//        System.out.println(time);

        Teacher teacher = new Teacher();
        WorkHandler teacherHandler = new WorkHandler(teacher);
        Object o = Proxy.newProxyInstance(
                teacher.getClass().getClassLoader(),
                teacher.getClass().getInterfaces(),
                teacherHandler);
        System.out.println(o.getClass().getName());

        Teacher o1 = (Teacher) o;

        Teacher depositMoney = o1.depositMoney(1000L)
                .depositMoney(900L);




    }

}
