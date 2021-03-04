package atguigu.hashtable;

public class HashTable {

    EmpLinkedList[] empLinkedListArray;

    public HashTable(int size) {
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < empLinkedListArray.length; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void list() {
        for (int i = 0; i < empLinkedListArray.length; i++) {
            empLinkedListArray[i].showLinkedList(i);
        }
    }

    /**
     * HashTableÐÂÔö
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
