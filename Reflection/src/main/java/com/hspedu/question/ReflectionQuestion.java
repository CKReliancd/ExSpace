package com.hspedu.question;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author Administrator
 * @Description
 * @Date 2021/5/20 17:26
 */
public class ReflectionQuestion {

    public static void main(String[] args) throws IOException, ClassNotFoundException,
            IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\main\\resources"));

        String classfullpath = properties.getProperty("classfullpath");
        String methodName = properties.getProperty("method");

        System.out.println("classfullpath:" + classfullpath);
        System.out.println("method:" + methodName);

        Class<?> cls = Class.forName(classfullpath);

        Object o = cls.newInstance();

        System.out.println("o的运行类型：" + o.getClass());

        Method method = cls.getMethod(methodName);

        method.invoke(o);


    }


}
