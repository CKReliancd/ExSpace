package atguigu.sort;

import java.util.Arrays;

/**
 * @author Administrator
 * @Description
 * @Date 2020/8/3 10:48
 */
public class SelectSort {

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];//重置min
                    minIndex = j;//重置minIndex
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }


}
