package atguigu.sort;

import java.util.Arrays;

/**
 * @author Administrator
 * @Description
 * @Date 2020/8/31 17:29
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};

        int left = 0;
        int right = arr.length;

        System.out.println("排序之前：" + Arrays.toString(arr));

        //快速排序
        quickSort(arr, left, right);

        System.out.println("排序之后：" + Arrays.toString(arr));

    }

    /**
     * 快速排序
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSort(int[] arr, int left, int right) {
        //pivot中轴值
        int pivot = arr[(left + right) / 2];

        //临时变量
        int temp = 0;

        //循环遍历数组，找出比pivot大或者小的值
        while (left < right) {

            //左边的下标不停的右移，直到找到比arr[pivot]大的值
            if (arr[left] < pivot) {
                left++;
            }

            //右边的下表不停的左移，直到找到比arr[pivot]小的值
            if (pivot < right) {
                right--;
            }

            //如果左边下表大过右边，说明左小右大的顺序已经做好，需要递归再次确定
            if (left > right) {
                break;
            }

            //如果左边或者右边存在比arr[pivot]大或者小的值，则开始交换
            swap(arr, left, right);

            //如果交换完后发现，左边的值等于pivot,则往左边再移一位
            if (arr[left] == pivot) {
                right--;
            }

            //如果交换完后发现，右的值等于pivot,则往右边再移一位
            if (pivot == arr[right]) {
                left++;
            }
        }

        //向左递归
        if (left < pivot) {
            quickSort(arr, left, right);
        }

        //向右递归
        if (pivot > right) {
            quickSort(arr, left, right);
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
        int temp;
        temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

}
