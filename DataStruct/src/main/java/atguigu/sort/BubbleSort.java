package atguigu.sort;

import java.time.LocalDateTime;

/**
 * 冒泡排序，花了十二秒多
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] arr = new int[80000];

        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

//        int[] arr = new int[]{-1, -2, 10, 9, 20, 11};

//        System.out.println("排序前的数组：" + Arrays.toString(arr));
        System.out.println("排序前的时间：" + LocalDateTime.now());

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

        System.out.println("排序后的时间：" + LocalDateTime.now());
//        System.out.println("排序后的数组：" + Arrays.toString(arr));

    }


}
