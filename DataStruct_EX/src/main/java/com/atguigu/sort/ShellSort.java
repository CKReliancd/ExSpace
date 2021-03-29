package com.atguigu.sort;

import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args) {

        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        //shellSortDerivation(arr);
        shellSort(arr);

    }

    public static void shellSort(int[] arr) {
//        int count = 1;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i;
                int insertValue = arr[insertIndex];
                if (arr[insertIndex] < arr[insertIndex - gap]) {
                    while (insertIndex - gap >= 0 && insertValue < arr[insertIndex - gap]) {
                        arr[insertIndex] = arr[insertIndex - gap];
                        insertIndex -= gap;
                    }
                    arr[insertIndex] = insertValue;
                }
            }
//            System.out.println("第" + count++ + "次排序" + Arrays.toString(arr));
        }
    }

    /**
     * 希尔排序插入交换法推导过程
     *
     * @param arr
     */
    private static void shellSortDerivation(int[] arr) {

        System.out.println("排序前:" + Arrays.toString(arr));

        //第一轮，十个元素分成五组,从第一组第二个元素也就是数组第六个元素开始进行插入排序
        for (int i = 5; i < arr.length; i++) {
            int insertIndex = i;
            int insertValue = arr[insertIndex];
            //小组中如果后面的元素小于前面的，才需要交换，这里进行优化
            if (arr[insertIndex] < arr[insertIndex - 5]) {

                //insertIndex-5>=0保证下标不越界
                //insertValue<arr[insertIndex-5]：说明小，再往前找更小的，
                // 如果没有，则推出循环
                while (insertIndex - 5 >= 0 && insertValue < arr[insertIndex - 5]) {

                    //当前位置元素后移，下标再往前一个步长
                    arr[insertIndex] = arr[insertIndex - 5];
                    insertIndex -= 5;
                }
                //当退出while循环，就找到了该插入的位置
                arr[insertIndex] = insertValue;
            }
        }
        System.out.println("第一轮排序后:" + Arrays.toString(arr));

        //第二轮，十个元素10/2/2分两组,从第一组第二个元素也就是数组第三个元素开始进行插入排序
        for (int i = 2; i < arr.length; i++) {

            int insertIndex = i;
            int insertValue = arr[insertIndex];

            //小组中如果后面的元素小于前面的，才需要交换，这里进行优化
            if (arr[insertIndex] < arr[insertIndex - 2]) {

                //insertIndex-2>=0保证下标不越界
                //insertValue<arr[insertIndex-2]：说明小，再往前找更小的，
                // 如果没有，则推出循环
                while (insertIndex - 2 >= 0 && insertValue < arr[insertIndex - 2]) {

                    //当前位置元素后移，下标再往前一个步长
                    arr[insertIndex] = arr[insertIndex - 2];
                    insertIndex -= 2;
                }
                //当退出while循环，就找到了该插入的位置
                arr[insertIndex] = insertValue;
            }
        }
        System.out.println("第二轮排序后:" + Arrays.toString(arr));

        //第三轮，十个元素 10/2/2/2 = 1 则不分组,从第二个元素开始插入排序
        for (int i = 1; i < arr.length; i++) {

            int insertIndex = i;
            int insertValue = arr[insertIndex];

            //小组中如果后面的元素小于前面的，才需要交换，这里进行优化
            if (arr[insertIndex] < arr[insertIndex - 1]) {

                //insertIndex-1>=0保证下标不越界
                //insertValue<arr[insertIndex-1]：说明小，再往前找更小的，
                // 如果没有，则推出循环
                while (insertIndex - 1 >= 0 && insertValue < arr[insertIndex - 1]) {

                    //当前位置元素后移，下标再往前一个步长
                    arr[insertIndex] = arr[insertIndex - 1];
                    insertIndex -= 1;
                }
                //当退出while循环，就找到了该插入的位置
                arr[insertIndex] = insertValue;
            }
        }
        System.out.println("第三轮排序后:" + Arrays.toString(arr));

    }


}
