package com.hspedu.bean;

import java.text.MessageFormat;

/**
 * @author Administrator
 * @Description
 * @Date 2021/5/20 17:29
 */
public class Cat {

    private String name = "HSP";

    private Cat() {

    }

    public void hi() {
        System.out.println("Hello " + this.name);
    }

    public void cry() {
        System.out.println(this.name + " ¿Þ£¡£¡£¡£¡£¡");
    }


}
