package atguigu.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] arr = new int[]{101, 56, 39, 47, 1};

        System.out.println("排序之前:" + Arrays.toString(arr));

        bubbleSort(arr);

    }

    public static void bubbleSort(int[] arr) {

        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }


}
