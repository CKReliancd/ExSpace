package atguigu.recursion;

/**
 * 第一个皇后先放第一行第一列
 * 第二个皇后放在第二行第一列、然后判断是否OK， 如果不OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适
 * 继续第三个皇后，还是第一列、第二列……直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
 * 当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到.
 * 然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4的步骤 【示意图】
 * <p>
 * 说明：理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题. arr[8] = {0 , 4, 7, 5, 2, 6, 1, 3} //对应arr 下标 表示第几行，即第几个皇后，arr[i] = val , val 表示第i+1个皇后，放在第i+1行的第val+1列
 * <p>
 * <p>
 * 一维数组：判断方式array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])
 * <p>
 * 一维数组放置方法中的回溯：
 * check时每一次递归式，进入到check中都有for (int i = 0; i < max; i++)，
 * 因此会有回溯
 * 例如：
 * 0 2 4 1 3 6 0 0
 * 0 2 4 1 3 7 0 0
 * 0 2 4 1 4 7 0 0
 * 0 2 4 1 5 7 0 0
 * 当第四个皇后放在第四列，第五个皇后找不到可以放置的位置时，这个基于n=5的fori循环完成，继续开始n = 4的fori循环，
 * 当n = 4 时，还是找不到，那么继续开始n = 3的放置
 *
 * @author Reliance
 */
public class EightQueen {

    int max = 8;
    /**
     * 定义一维数组，保存皇后位置，比如arr={0,4,7,5,2,6,1,3}
     */
    int[] array = new int[max];
    static int count = 0;

    public static void main(String[] args) {

        EightQueen eightQueen = new EightQueen();
        eightQueen.check(0);
        System.out.println(count);

    }

    /**
     * 放置第n个皇后
     * 特别注意：check时每一次递归式，进入到check中都有for (int i = 0; i < max; i++)，
     * 因此会有回溯
     * 例如：
     * 0 2 4 1 3 6 0 0
     * 0 2 4 1 3 7 0 0
     * 0 2 4 1 4 7 0 0
     * 0 2 4 1 5 7 0 0
     * 当第四个皇后放在第四列，第五个皇后找不到可以放置的位置时
     * ，这个基于n=5的fori循环完成，继续开始n = 4的fori循环
     * ，当n = 4 时，还是找不到，那么继续开始n = 3的放置
     *
     * @param n
     */
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n,放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            print();
            if (judge(n)) {
                check(n + 1);
            }
            //如果冲突，就继续执行array[n] = 1;即将第n个皇后，放置在本行的后移一个位置
        }
    }

    /**
     * 当我们放置第n个皇后,就去检测该皇后是否和前面已经摆放的皇后冲突
     * 1、array[i] == array[n]   表示判断第n个皇后是否和前面的n-1个皇后在同一列
     * 2、Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后在同一斜线
     * 例：n = 1 放置第2列   array[1] = 1
     * Math.abs(1-0) == 1  Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
     * 3、没有必要判断在同一行，因为n每一次都在递增
     *
     * @param n
     * @return
     */
    public boolean judge(int n) {

        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将皇后摆放的位置输出
     */
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
