package com.hsp.classes;

import com.hsp.Cat;

import java.lang.reflect.Field;

/**
 * @author Administrator
 * @Description
 * @Date 2021/5/21 17:22
 */
public class Class01 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        /**
         * JVMֻ�ᴴ��һ�ζ����ClassLoaderȻ����ڻ����� ,
         * ����JVM��Ҫ�ٴ���������ֱ�Ӵӻ����л�ȡ���ClassLoader����
         *
         * public Class<?> loadClass(String name) throws ClassNotFoundException {
         *         return loadClass(name, false);
         *  }
         */
        Cat cat = new Cat();

        Class<?> cls = Class.forName("com.hsp.Cat");

        Object o = cls.newInstance();

        Field brand = cls.getField("brand");

        brand.set(o, "��ëè");

        System.out.println(brand.get(o));


    }

}
