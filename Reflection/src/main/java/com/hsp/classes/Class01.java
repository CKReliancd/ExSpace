package com.hsp.classes;

import com.hsp.Cat;

/**
 * @author Administrator
 * @Description
 * @Date 2021/5/21 17:22
 */
public class Class01 {

    public static void main(String[] args) throws ClassNotFoundException {

        /**
         * JVM只会创建一次对象的ClassLoader然后放在缓存中 ,
         * 后续JVM需要再创建对象则直接从缓存中获取类的ClassLoader即可
         *
         * public Class<?> loadClass(String name) throws ClassNotFoundException {
         *         return loadClass(name, false);
         *  }
         */
        Cat cat = new Cat();

        Class<?> aClass = Class.forName("com.hsp.Cat");


    }

}
