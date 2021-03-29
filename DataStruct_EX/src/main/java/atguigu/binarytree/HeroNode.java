package atguigu.binarytree;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
public class HeroNode {

    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (left != null) {
            left.infixOrder();
        }
        System.out.println(this);
        if (right != null) {
            right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (left != null) {
            left.infixOrder();
        }
        if (right != null) {
            right.infixOrder();
        }
        System.out.println(this);
    }


}
