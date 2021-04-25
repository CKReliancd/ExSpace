package ex.ex01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Administrator
 * @Description
 * @Date 2021/4/23 16:43
 */
public class WorkHandler implements InvocationHandler {

    private Object obj;

    public WorkHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before 动态代理...");
        System.out.println("proxy : " + proxy.getClass().getName());
        System.out.println("this.obj : " + this.obj.getClass().getName());

        String methodName = method.getName();

        if (methodName.equals("work")) {
            System.out.println("method : " + methodName);
            method.invoke(this.obj, args);
            System.out.println("after 动态代理...");
            return proxy;
        } else {
            System.out.println("method : " + methodName + " after 动态代理...");
            return method.invoke(this.obj, args);
        }
    }
}
