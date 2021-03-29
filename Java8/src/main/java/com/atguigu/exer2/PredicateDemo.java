package com.atguigu.exer2;

import java.util.function.Predicate;

/**
 * @author Administrator
 * @Description
 * @Date 2021/3/29 16:06
 */
public class PredicateDemo {

    public static void main(String[] args) {
        predicateDemo1();
        predicateDemo2(2020);
        predicateDemo3(20210404, integer -> integer == 20210404);
    }

    static void predicateDemo3(Integer integer, Predicate<Integer> predicate) {
        if (predicate.test(integer)) {
            System.out.println("清明时令雨连绵，游子客乡忆祖先");
        } else {
            System.out.println("没到清明节");
        }
    }

    static void predicateDemo2(Integer integer) {

        Predicate<Integer> predicate = (x) -> integer == 20210404;

        if (predicate.test(integer)) {
            System.out.println("清明时令雨连绵，游子客乡忆祖先");
        } else {
            System.out.println("没到清明节");
        }
    }

    static void predicateDemo1() {

        Predicate<Integer> predicate = integer -> integer == 20210404;
        if (predicate.test(20210404)) {
            System.out.println("清明时令雨连绵，游子客乡忆祖先");
        } else {
            System.out.println("没到清明节");
        }
    }


}
