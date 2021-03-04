package atguigu.hashtable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmpLinkedList {

    private Emp head;

    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空！");
            return null;
        }
        Emp temp = head;
        while (true) {
            if (temp == null) {
                System.out.println("没找到Id对应节点");
                return null;
            }
            if (temp.getId() == id) {

                return temp;
            }
            temp = temp.next;
        }
    }

    public void delete(int id) {
        if (head == null) {
            System.out.println("链表为空！");
            return;
        }
        int headId = head.getId();
        if (headId == id) {
            head = head.next;
            System.out.printf("删除节点:%s", head.toString());
            return;
        }
        Emp temp = head;
        while (true) {
            if (temp == null || temp.next == null) {
                System.out.println("没找到对应ID的节点");
                return;
            }
            if (temp.next.getId() == id) {
                break;
            }
            temp = temp.next;
        }
        System.out.printf("已删除节点：%s", temp.next.toString());
        temp.next = temp.next.next;
    }

    /**
     * 展示
     *
     * @param i
     */
    public void showLinkedList(int i) {
        if (head == null) {
            System.out.printf("第i%d条链表为空");
            return;
        }
        Emp temp = head;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.printf("第i%d条链表%s", i, temp.toString());
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * 新增
     *
     * @param emp
     */
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp temp = this.head;
        while (true) {
            if (temp.next == null) {
                temp.next = emp;
                return;
            }
            temp = temp.next;
        }
    }
}
