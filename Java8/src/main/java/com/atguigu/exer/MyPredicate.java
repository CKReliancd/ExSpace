package com.atguigu.exer;

import java.util.function.Predicate;

/**
 * @author Administrator
 * @Description
 * @Date 2021/3/29 15:43
 */
@FunctionalInterface
public interface MyPredicate extends Predicate {

    @Override
    boolean test(Object o);

}
