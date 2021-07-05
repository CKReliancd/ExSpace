package leetcode.openlock;

/**
 * @author Administrator
 * @Description
 * @Date 2021/7/5 16:33
 */
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
