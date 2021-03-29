package com.atguigu.search;

/**
 * @author Administrator
 * @Description
 * @Date 2021/2/26 11:01
 */
public class InterpolationSearch {

    public static void main(String[] args) {

        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};

        int i = interpolationSearch(arr, 0, arr.length - 1, 8);

        System.out.println(i);
    }

    /**
     * 插值查找，建立在有序数组上，对均匀分布的数组才有意义
     *
     * @param arr
     * @param left
     * @param right
     * @param findValue
     * @return
     */
    private static int interpolationSearch(int[] arr, int left, int right, int findValue) {

        System.out.println("Hello~");

        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return -1;
        }

        //加上left，当右方n*p的期望值是0的时候，取left而不去right？？

        /**
         * 查找的元素占所有元素的比例的期望值p：
         * (findValue - arr[left]) / (arr[right] - arr[left]);
         * 数组长度n:
         * (right - left)
         * 所以我们期望查找的索引值就为：
         * n*p
         *
         */
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);

        int midVal = arr[mid];

        if (findValue < midVal) {
            return interpolationSearch(arr, left, mid - 1, findValue);
        } else if (findValue > midVal) {
            return interpolationSearch(arr, mid + 1, right, findValue);
        } else {
            return mid;
        }
    }


}
