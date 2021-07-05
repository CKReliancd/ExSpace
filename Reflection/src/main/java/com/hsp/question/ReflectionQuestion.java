package com.hsp.question;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author Administrator
 * @Description
 * @Date 2021/5/21 9:54
 */
public class ReflectionQuestion {
    public static void main(String[] args) throws IOException, ClassNotFoundException,
            IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Properties properties = new Properties();

        //ClassLoader.getResource()的资源获取不能以 / 开头，统一从根路径开始搜索资源。
        FileInputStream fi = new FileInputStream(ClassLoader.getSystemClassLoader()
                .getResource("re.properties").getPath());
//
//        InputStream resourceAsStream = Thread.currentThread()
//                .getContextClassLoader().getResourceAsStream("re.properties");

        properties.load(fi);

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
