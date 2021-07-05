package leetcode.openlock;

import java.util.*;

/**
 * @author Administrator
 * @Description ����ʽ����
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

    // ö�� status ͨ��һ����ת�õ�������
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
     * F(x) ���� F(x) = G(x) + H(x)��
     * ��Ϊ����� s ���յ� t �ġ����ơ�·�����ȡ�
     * ����������ѡ����С�� F(x) ��Ӧ�� x ����������
     * ��� A* �㷨��Ҫ�������ȶ�����ʵ��
     */
    int f;
    /**
     * G(x) ��ʾ����� s ���ڵ� x �ġ�ʵ�ʡ�·�����ȣ�ע�� G(x) ����һ������̵�
     */
    int g;
    /**
     * H(x) ��ʾ�ӽڵ� x ���յ� t �ġ����ơ����·�����ȣ���Ϊ����������
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
     * ������������
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
            //��9->0������9������10-9=1�����ҳ���С�Ĳ���
            ret += Math.min(dist, 10 - dist);
        }
        return ret;
    }


}
