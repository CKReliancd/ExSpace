package leetcode.openlock;

import java.util.*;

/**
 * @author Administrator
 * @Description 启发式搜索
 * @Date 2021/7/5 9:57
 */
public class HeuristicOpenLock {

    public static void main(String[] args) {

        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";

        HeuristicOpenLock aStartOpen = new HeuristicOpenLock();

        long start = System.currentTimeMillis();
        int i = aStartOpen.openLock(deadends, target);
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }

        Set<String> dead = new HashSet<String>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        if (dead.contains("0000")) {
            return -1;
        }

        PriorityQueue<AStar> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.f));
        pq.offer(new AStar("0000", target, 0));
        Set<String> seen = new HashSet<>();
        seen.add("0000");

        while (!pq.isEmpty()) {
            AStar node = pq.poll();
            List<String> statuList = get(node.status);
            for (String nextStatus : statuList) {
                if (!seen.contains(nextStatus) && !dead.contains(nextStatus)) {
                    if (nextStatus.equals(target)) {
                        return node.g + 1;
                    }
                    AStar aStar = new AStar(nextStatus, target, node.g + 1);
                    pq.offer(aStar);
                    seen.add(nextStatus);
                }
            }
        }
        return -1;
    }

    public char numPrev(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    public char numSucc(char x) {
        return x == '9' ? '0' : (char) (x + 1);
    }

    // 枚举 status 通过一次旋转得到的数字
    public List<String> get(String status) {
        List<String> ret = new ArrayList<String>();
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

class AStar {
    String status;
    /**
     * F(x) 满足 F(x) = G(x) + H(x)，
     * 即为从起点 s 到终点 t 的「估计」路径长度。
     * 我们总是挑选出最小的 F(x) 对应的 x 进行搜索，
     * 因此 A* 算法需要借助优先队列来实现
     */
    int f;
    /**
     * G(x) 表示从起点 s 到节点 x 的「实际」路径长度，注意 G(x) 并不一定是最短的
     */
    int g;
    /**
     * H(x) 表示从节点 x 到终点 t 的「估计」最短路径长度，称为启发函数；
     */
    int h;

    /**
     * @param status
     * @param target
     * @param g
     */
    public AStar(String status, String target, int g) {
        this.status = status;
        this.g = g;
        this.h = getH(status, target);
        this.f = this.g + this.h;
    }

    /**
     * 计算启发函数
     *
     * @param status
     * @param target
     * @return
     */
    public static int getH(String status, String target) {
        int ret = 0;
        for (int i = 0; i < 4; ++i) {
            char sc = status.charAt(i);
            char tc = target.charAt(i);
            int dist = Math.abs(sc - tc);
            //从9->0可以有9步或者10-9=1步，找出最小的步数
            ret += Math.min(dist, 10 - dist);
        }
        return ret;
    }


}
