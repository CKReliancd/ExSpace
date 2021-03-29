package atguigu.hashtable;

import java.util.Scanner;

/**
 * @author Administrator
 * @Description
 * @Date 2021/3/8 10:34
 */
public class HashTableDemo {

    public static void main(String[] args) {

        HashTable hashTable = new HashTable(7);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:    添加雇员");
            System.out.println("list:   显示雇员");
            System.out.println("find:   查找雇员");
            System.out.println("exit:   退出系统");

            String key = scanner.next();
            switch (key) {
                case "add":
                    tableAddEmp(hashTable, scanner);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入员工ID");
                    int id = scanner.nextInt();
                    Emp emp = hashTable.findEmpById(id);
                    if (emp == null) {
                        System.out.println("找不到该员工");
                        break;
                    }
                    System.out.printf("找到员工:%s", emp.toString());
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * HashTable增加员工
     *
     * @param hashTable
     * @param scanner
     */
    private static void tableAddEmp(HashTable hashTable, Scanner scanner) {

        System.out.println("输入ID");
        int id = scanner.nextInt();
        System.out.println("输入名字");
        String name = scanner.next();

        Emp emp = new Emp();
        emp.setId(id);
        emp.setName(name);

        hashTable.add(emp);
    }

}
