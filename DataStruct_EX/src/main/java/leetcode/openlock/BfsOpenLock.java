package leetcode.openlock;

import java.util.*;

/**
 * @author Administrator
 * @Description
 * @Date 2021/7/5 16:38
 */
public class BfsOpenLock {

    public static void main(String[] args) {

        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";

        BfsOpenLock bfsOpenLock = new BfsOpenLock();
        int i = bfsOpenLock.bfsOpenLock(target, deadends);
        System.out.println(i);

    }

    /**
     * 广度优先搜索
     *
     * @param target
     * @param deadends
     * @return
     */
    public int bfsOpenLock(String target, String[] deadends) {
        String initial = "0000";
        int count = 0;
        if (initial.equals(target)) {
            return count;
        }
        List<String> dead = Arrays.asList(deadends);
        if (dead.contains(target)) {
            return -1;
        }
        Queue<String> quque = new LinkedList<>();
        quque.offer(initial);
        Set<String> seen = new HashSet<>();
        seen.add("0000");
        while (quque.size() > 0) {
            count++;
            String status = quque.poll();
            //根据当前status枚举出下一个status集合
            List<String> nextStatusList = get(status);
            for (String nextStatus : nextStatusList) {
                if (!dead.contains(nextStatus) && !seen.contains(nextStatus)) {
                    if (target.equalsIgnoreCase(nextStatus)) {
                        return count;
                    }
                    seen.add(nextStatus);
                    quque.offer(nextStatus);
                }
            }
        }
        return -1;
    }

    /**
     * 根据当前status枚举出下一个status集合
     *
     * @param status
     * @return
     */
    public List<String> get(String status) {

        List<String> res = new ArrayList<>();
        char[] chars = status.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            char aChar = chars[i];

            chars[i] = preChar(aChar);
            res.add(new String(chars));

            chars[i] = nextChar(aChar);
            res.add(new String(chars));

            chars[i] = aChar;
        }
        return res;
    }

    private char preChar(char c) {
        char p = (char) (c == '0' ? '9' : c - 1);
        return p;
    }

    private char nextChar(char c) {
        char n = (char) (c == '9' ? '0' : c + 1);
        return n;
    }
}
