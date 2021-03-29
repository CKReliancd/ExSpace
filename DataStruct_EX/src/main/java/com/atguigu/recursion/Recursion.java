package com.atguigu.recursion;

/**
 * ��ӡ
 * �׳�
 */
public class Recursion {

    public static void main(String[] args) {

        System.out.println(factorial(4));
    }

    /**
     * �׳�
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {

        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }


}
