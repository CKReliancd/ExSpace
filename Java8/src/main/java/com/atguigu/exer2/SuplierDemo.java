package com.atguigu.exer2;

import java.util.function.Supplier;

/**
 * @author Administrator
 * @Description
 * @Date 2021/3/29 16:38
 */
public class SuplierDemo {

    public static void main(String[] args) {
        suplierDemo1();
        suplierDemo2(() -> 20210404);
    }

    static void suplierDemo2(Supplier<Integer> supplier) {

        System.out.println(supplier.get());
    }

    static void suplierDemo1() {

        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 20210404;
            }
        };
        System.out.println(supplier.get());
    }

}
