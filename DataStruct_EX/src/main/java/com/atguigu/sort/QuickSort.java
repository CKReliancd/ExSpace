package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author Administrator
 * @Description
 * @Date 2020/8/31 17:29
 */
public class QuickSort {

    public static void main(String[] args) {

//        int[] arr = {-9, 78, 0, 23, -567, 70};
        int[] arr = {-9, 78, 0, 23, -9, 70, -1, 900, -4561};

        int left = 0;
        int right = arr.length - 1;

        System.out.println("排序之前：" + Arrays.toString(arr));

        //快速排序
        quickSort(arr, left, right);

        System.out.println("排序之后：" + Arrays.toString(arr));

    }

    /**
     * 快速排序
     * {-9, 78, 0, 23, -567, 70, -1, 900, -4561};
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int[] arr, int left, int right) {

        int l = left;
        int r = right;

        //pivot中轴值
        int pivot = arr[(left + right) / 2];

        //循环遍历数组，找出比pivot大或者小的值
        while (l < r) {
            //左边的下标不停的右移，直到找到比arr[pivot]大的值
            while (arr[l] < pivot) {
                l++;
            }
            //右边的下表不停的左移，直到找到比arr[pivot]小的值
            while (arr[r] > pivot) {
                r--;
            }
            //如果左边下标大过右边，说明左小右大的顺序已经做好，需要递归再次确定
            if (l >= r) {
                break;
            }
            //走到这里说明左边或者右边存在比arr[pivot]大或者小的值，则开始交换
            swap(arr, l, r);
            //如果交换完后发现，左边的值等于pivot,则往左边再移一位
            if (arr[l] == pivot) {
                r--;
            }
            //如果交换完后发现，右的值等于pivot,则往右边再移一位
            if (pivot == arr[r]) {
                l++;
            }
        }

        //l左移，r右移，当l==r,while条件不满足,但是肯定满足下面的向左或者右的递归条件，
        //所以会不停的调用栈，直接进入下面的递归，所以必须让临时下标不满足上面的l<r才不会栈溢出
        if (l == r) {
            l++;
            r--;
        }

        //left在这个方法中是个稳定值，l和r在这个方法中会不停的变化移动，
        //这时候要向左或者向右递归，但是要满足一下条件left < r,或者right >l
        if (left < r) {
            quickSort(arr, left, r);
        }

        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

    /**
     * 普通的交换位置
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void swap(int[] arr, int left, int right) {
        //临时变量
        int temp;
        temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

}
