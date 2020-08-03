package atguigu.sort;

import java.util.Arrays;

/**
 * @author Administrator
 * @Description
 * @Date 2020/8/3 15:03
 */
public class InsertSort {

    public static void main(String[] args) {

        int[] arr = new int[]{101, 56, 39, 47, 1};

        System.out.println("排序之前:" + Arrays.toString(arr));

        inserSort(arr);

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
            //给insertValue找插入位置
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
}
