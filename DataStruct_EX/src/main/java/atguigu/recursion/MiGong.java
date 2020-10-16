package atguigu.recursion;

public class MiGong {

    public static void main(String[] args) throws ClassNotFoundException {

        int[][] map = new int[8][7];
        drawMap(map);
        setWay(map,1,1);

        //输出新的地图，小球走过并标识过的递归
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

    }

    /**
     * 使用递归回溯来给小球找路
     * 说明
     * 1、map表示地图
     * 2、i，j表示从地图的那个位置开始出发(i,j)
     * 3、如果小球能到map[6][5]位置，说明通路找到
     * 4、约定：当map[i][j]为 0表示该点没有走过，1表示墙，2表示通路可以走，3表示该点已经走过，但是走不通
     * 5、在走迷宫是，需要确定一个策略 下->右->上->左
     *
     * @param map 表示地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 如果找到通路，就返回true，否则就返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {

        if (map[6][5] == 2) {//説明通路找到
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前这个点还没有走过
                //假定该点是可以走通的
                map[i][j] = 2;
                //按照策略 下->右->上->左
                if(setWay(map,i+1,j)) {//往下走
                    //如果通，则返回true
                    return true;
                }else if (setWay(map,i,j+1)){
                    return true;
                } else if (setWay(map,i-1,j)){//往右走
                    return true;
                } else if (setWay(map,i,j-1)){//往左走
                    return true;
                } else {
                    //说明该点走不通，是死路
                    map[i][j] =3;
                    return false;
                }
            } else {//不为0，可能是1，2，3
                return false;
            }

        }


    }

    private static void drawMap(int[][] map) {
        //使用1表示墙，顶行和底行设置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //左右两列设置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("地图的情况");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }


}
