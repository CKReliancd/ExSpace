package com.hsp;

/**
 * @author Administrator
 * @Description
 * @Date 2021/5/21 13:50
 */
public class Cat {

    public String name = "Cat";

    public String brand = "²¨Ë¹Ã¨";

    public String price = "20000";

    public void hi() {
        System.out.println("Hello " + name);
    }

    public void cry() {
        System.out.println(name + " cry!");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
