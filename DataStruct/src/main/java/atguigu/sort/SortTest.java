package atguigu.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author Administrator
 * @Description <p>
 * <p>
 * 80W级别
 * ----冒泡排序：
 * ---------i7770HQ花了12秒多<p>
 * ---------Intel(R) Core(TM) i5-8500 CPU @ 3.20GHz (6 CPUs), ~3.0GHz 花了9.19秒<p>
 * ----选择排序：1.723秒
 * ----插入排序: 80W：0.5秒
 * 8KW级别
 * ----希尔排序: 80W：0.020秒  8KW: 31.68 秒
 * ----快速排序: 80W：0.017秒  8KW：12.842 秒
 * @Date 2020/8/3 13:59
 */
public class SortTest {
    public static void main(String[] args) {

        int[] arr = new int[80000000];

        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 80000000);
        }

//        System.out.println("排序前的数组：" + Arrays.toString(arr));
        System.out.println("排序前的时间：" + LocalDateTime.now());

//        BubbleSort.bubbleSort(arr);

//        SelectSort.selectSort(arr);

//        InsertSort.inserSort(arr);

//        ShellSort.shellSort(arr);

        QuickSort.quickSort(arr, 0, arr.length - 1);

        System.out.println("排序后的时间：" + LocalDateTime.now());
//        System.out.println("排序后的数组：" + Arrays.toString(arr));

    }

}
