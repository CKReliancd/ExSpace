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
         * JVMֻ�ᴴ��һ�ζ����ClassLoaderȻ����ڻ����� ,
         * ����JVM��Ҫ�ٴ���������ֱ�Ӵӻ����л�ȡ���ClassLoader����
         *
         * public Class<?> loadClass(String name) throws ClassNotFoundException {
         *         return loadClass(name, false);
         *  }
         */
        Cat cat = new Cat();

        Class<?> aClass = Class.forName("com.hsp.Cat");


    }

}
