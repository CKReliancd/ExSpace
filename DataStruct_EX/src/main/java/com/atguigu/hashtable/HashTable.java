package com.atguigu.hashtable;

public class HashTable {

    EmpLinkedList[] empLinkedListArray;

    public HashTable(int size) {
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < empLinkedListArray.length; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 通过ID查找对应Emp
     *
     * @param id
     * @return
     */
    public Emp findEmpById(int id) {
        int no = hashFun(id);
        Emp emp = empLinkedListArray[no].findEmpById(id);
        return emp;
    }

    public void list() {
        for (int i = 0; i < empLinkedListArray.length; i++) {
            empLinkedListArray[i].showLinkedList(i);
        }
    }

    /**
     * HashTable新增
     *
     * @param emp
     */
    public void add(Emp emp) {
        int id = emp.getId();
        int empLikedListNo = hashFun(id);
        empLinkedListArray[empLikedListNo].add(emp);
    }

    public int hashFun(int id) {
        return id % empLinkedListArray.length;
    }


}
