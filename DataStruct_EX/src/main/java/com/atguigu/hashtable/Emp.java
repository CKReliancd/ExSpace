package com.atguigu.hashtable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Emp {
    private int id;
    private String name;
    public Emp next;

    @Override
    public String toString() {
        return "员工{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
