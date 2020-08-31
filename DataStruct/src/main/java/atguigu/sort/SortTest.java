package atguigu.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author Administrator
 * @Description <p>
 * ----冒泡排序：
 * ---------i7770HQ花了12秒多<p>
 * ---------Intel(R) Core(TM) i5-8500 CPU @ 3.20GHz (6 CPUs), ~3.0GHz 花了9.19秒<p>
 * ----选择排序：1.723秒
 * ----插入排序：0.5秒
 * ----希尔排序: 0.020秒
 * @Date 2020/8/3 13:59
 */
public class SortTest {
    public static void main(String[] args) {

        int[] arr = new int[80000];

        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

//        System.out.println("排序前的数组：" + Arrays.toString(arr));
        System.out.println("排序前的时间：" + LocalDateTime.now());

//        BubbleSort.bubbleSort(arr);

//        SelectSort.selectSort(arr);

//        InsertSort.inserSort(arr);

        ShellSort.shellSort(arr);

        System.out.println("排序后的时间：" + LocalDateTime.now());
//        System.out.println("排序后的数组：" + Arrays.toString(arr));

    }

}
