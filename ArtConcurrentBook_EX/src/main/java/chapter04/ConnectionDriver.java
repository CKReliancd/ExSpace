package chapter04;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class ConnectionDriver {

    public static final Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(
                ConnectionDriver.class.getClassLoader(),
                Connection.class.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("commit")) {
                            try {
                                TimeUnit.MILLISECONDS.sleep(100);
                            } catch (Exception e) {
                            }

                        }
                        return null;
                    }
                });
    }
}
