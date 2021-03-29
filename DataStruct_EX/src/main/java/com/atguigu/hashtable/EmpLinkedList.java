package com.atguigu.hashtable;

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
                System.out.printf("没找到id:%d对应节点", id);
                return null;
            }
            if (temp.getId() == id) {

                return temp;
            }
            temp = temp.next;
        }
    }

    /**
     * 删除节点
     *
     * @param id
     */
    public void delete(int id) {
        if (head == null) {
            System.out.println("链表为空！");

            return;
        }
        int headId = head.getId();
        if (headId == id) {
            //头节点的下一个几点做为头节点
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
            System.out.println("第" + i + "条链表为空");
            return;
        }
        Emp temp = head;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.printf(" 第%d条链表%s", i, temp.toString());
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * <<<<<<< Updated upstream:DataStruct_EX/src/main/java/com/atguigu/hashtable/EmpLinkedList.java
     * 新增
     * =======
     * ����
     * >>>>>>> Stashed changes:DataStruct_EX/src/main/java/atguigu/hashtable/EmpLinkedList.java
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
