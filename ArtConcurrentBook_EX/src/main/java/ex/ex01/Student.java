package ex.ex01;

import java.util.Date;

/**
 * @author Administrator
 * @Description
 * @Date 2021/4/23 16:41
 */
public class Student implements People {
    @Override
    public People work(String workName) {
        System.out.println("工作内容是" + workName);
        return this;
    }

    @Override
    public String time() {
        return new Date().toString();
    }
}
