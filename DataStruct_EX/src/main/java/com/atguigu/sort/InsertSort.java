package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author Administrator
 * @Description
 * @Date 2020/8/3 15:03
 */
public class InsertSort {

    public static void main(String[] args) {

        int[] arr = new int[]{34, 119, 110, 1};

        System.out.println("排序之前:" + Arrays.toString(arr));

        inserSort2(arr);

    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void inserSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {

            int insertValue = arr[i];
            int insertIndex = i - 1;
            
            /**
             * 给insertValue找插入位置:
             * (1) 如果insertIndex--已经下标越界，
             * 那么说明找到了无序数组最开头的0位置不能再往下，那么插入位置找到
             * (2) 如果inserValue < arr[insertIndex],
             * 原本的元素后移，下标前移找最适合的位置
             */
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环的时候，说明插入的位置已经找到
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }
//            System.out.println("第" + i + "次排序后：" + Arrays.toString(arr));
        }
    }

    /**
     * 插入排序2
     *
     * @param arr
     */
    public static void inserSort2(int[] arr) {

        int insertValue = arr[2];
        int insertIndex = 2 - 1;
        /**
         * 给insertValue找插入位置:
         * (1) 如果insertIndex--已经下标越界，
         * 那么说明找到了无序数组最开头的0位置不能再往下，那么插入位置找到
         * (2) 如果inserValue < arr[insertIndex],
         * 原本的元素后移，下标前移找最适合的位置
         */
        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环的时候，说明插入的位置已经找到
//        if (insertIndex + 1 != 2) {
            arr[insertIndex + 1] = insertValue;
//        }
        System.out.println("第" + 2 + "次排序后：" + Arrays.toString(arr));
    }

    /**
     * 插入排序3
     *
     * @param arr
     */
    public static void inserSort3(int[] arr) {


        int insertValue = arr[3];
        int insertIndex = 3 - 1;
        /**
         * 给insertValue找插入位置:
         * (1) 如果insertIndex--已经下标越界，
         * 那么说明找到了无序数组最开头的0位置不能再往下，那么插入位置找到
         * (2) 如果inserValue < arr[insertIndex],
         * 原本的元素后移，下标前移找最适合的位置
         */

        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环的时候，说明插入的位置已经找到
        if (insertIndex + 1 != 3) {
            arr[insertIndex + 1] = insertValue;
        }
        System.out.println("第" + 3 + "次排序后：" + Arrays.toString(arr));
    }

}
