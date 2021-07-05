package leetcode.openlock;

/**
 * @author Administrator
 * @Description
 * @Date 2021/7/5 16:33
 */
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
