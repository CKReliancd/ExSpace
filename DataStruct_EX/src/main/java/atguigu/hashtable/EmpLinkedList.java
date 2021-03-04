package atguigu.hashtable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmpLinkedList {

    private Emp head;

    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("����Ϊ�գ�");
            return null;
        }
        Emp temp = head;
        while (true) {
            if (temp == null) {
                System.out.println("û�ҵ�Id��Ӧ�ڵ�");
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
            System.out.println("����Ϊ�գ�");
            return;
        }
        int headId = head.getId();
        if (headId == id) {
            head = head.next;
            System.out.printf("ɾ���ڵ�:%s", head.toString());
            return;
        }
        Emp temp = head;
        while (true) {
            if (temp == null || temp.next == null) {
                System.out.println("û�ҵ���ӦID�Ľڵ�");
                return;
            }
            if (temp.next.getId() == id) {
                break;
            }
            temp = temp.next;
        }
        System.out.printf("��ɾ���ڵ㣺%s", temp.next.toString());
        temp.next = temp.next.next;
    }

    /**
     * չʾ
     *
     * @param i
     */
    public void showLinkedList(int i) {
        if (head == null) {
            System.out.printf("��i%d������Ϊ��");
            return;
        }
        Emp temp = head;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.printf("��i%d������%s", i, temp.toString());
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * ����
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
