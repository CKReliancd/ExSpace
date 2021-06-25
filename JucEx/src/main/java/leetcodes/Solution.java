package leetcodes;

import java.util.*;

/**
 * @author Administrator
 * @Description
 * @Date 2021/6/25 16:18
 */
public class Solution {

    public static void main(String[] args) {

        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";

        Solution solution = new Solution();

        int i = solution.openLock(deadends, target);

        System.out.println(i);

    }

    public int openLock(String[] deadends, String target) {

        if ("0000".equals(target)) {
            return 0;
        }

        Set<String> dead = new HashSet<>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        if (dead.contains("0000")) {
            return -1;
        }

        //旋转的次数
        int step = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        Set<String> seen = new HashSet<>();
        seen.add("0000");
        while (!queue.isEmpty()) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                //当前搜索到的数字
                String status = queue.poll();
                List<String> statusList = get(status);
                for (String nextStatus : statusList) {
                    boolean nonExistSeen = !seen.contains(nextStatus);
                    boolean nonExistDead = !dead.contains(nextStatus);
                    if (nonExistSeen && nonExistDead) {
                        if (nextStatus.equals(target)) {
                            return step;
                        }
                        queue.offer(nextStatus);
                        seen.add(nextStatus);
                    }
                }
            }
        }

        return -1;
    }

    public char numPrev(char x) {
        int xpre = x - 1;
        char xPreChar = (char) (xpre);
        return x == '0' ? '9' : xPreChar;
    }

    public char numSucc(char x) {
        int xnext = x + 1;
        char xNextChar = (char) (xnext);
        return x == '9' ? '0' : xNextChar;
    }

    /**
     * 枚举 status 通过一次旋转得到的数字
     *
     * @param status
     * @return
     */
    public List<String> get(String status) {
        List<String> ret = new ArrayList<>();
        char[] array = status.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char num = array[i];
            array[i] = numPrev(num);
            ret.add(new String(array));
            array[i] = numSucc(num);
            ret.add(new String(array));
            array[i] = num;
        }
        return ret;
    }
}
