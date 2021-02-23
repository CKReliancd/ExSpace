package atguigu.recursion;

/**
 * ´òÓ¡
 * ½×³Ë
 */
public class Recursion {

    public static void main(String[] args) {

        System.out.println(factorial(4));
    }

    /**
     * ½×³Ë
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {

        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }


}
