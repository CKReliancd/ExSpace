package com.atguigu.exer2;

import java.util.function.Function;

/**
 * @author Administrator
 * @Description
 * @Date 2021/3/29 15:49
 */
public class FunctionDemo {

    public static void main(String[] args) {
        functionDemo1();
        functionDemo2();
        functionDemo3();
        functionDemo4("2021年4月4日：", str -> str + "清明时令雨连绵，游子客乡忆祖先");
    }

    static void functionDemo4(String str, Function<String, String> function) {

        System.out.println("functionDemo4 -->" + function.apply(str));

    }

    static void functionDemo3() {

        Function<String, String> function = (str) -> str + "清明时令雨连绵，游子客乡忆祖先";

        System.out.println("functionDemo3 -->" + function.apply("2021年4月4日："));
    }

    static void functionDemo2() {

        Function<String, String> function = (str) -> {
            return str + "清明时令雨连绵，游子客乡忆祖先";
        };

        System.out.println("functionDemo2 -->" + function.apply("2021年4月4日："));
    }

    static void functionDemo1() {

        Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String str) {
                return str + "清明时令雨连绵，游子客乡忆祖先";
            }
        };

        System.out.println("functionDemo1 -->" + function.apply("2021年4月4日："));
    }
}
