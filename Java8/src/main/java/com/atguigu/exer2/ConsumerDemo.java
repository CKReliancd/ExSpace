package com.atguigu.exer2;

import java.util.function.Consumer;

/**
 * @author Administrator
 * @Description
 * @Date 2021/3/29 16:27
 */
public class ConsumerDemo {

    public static void main(String[] args) {
        consumerDemo1();
        consumerDemo2(2021,
                integer -> System.out.println(integer + "清明时令雨连绵，游子客乡忆祖先"));
        consumerDemo2(2021,
                integer -> {
                    int i = integer + 1;
                    System.out.println(i);
                });

    }

    static void consumerDemo3() {

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                int i = integer + 1;
                return;
            }
        };
    }

    static void consumerDemo2(Integer integer, Consumer<Integer> consumer) {
        consumer.accept(integer);
    }

    static void consumerDemo1() {
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer + " 清明时令雨连绵，游子客乡忆祖先");
            }
        };
        consumer.accept(2021);
    }

}
