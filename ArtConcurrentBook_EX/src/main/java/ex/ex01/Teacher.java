package ex.ex01;

/**
 * @author Administrator
 * @Description
 * @Date 2021/4/23 17:47
 */
public class Teacher implements People {

    static Long deposit;

    public Teacher depositMoney(Long money) {
        deposit += money;
        System.out.println("存入：" + money + "元,余额：" + deposit);
        return this;
    }

    @Override
    public People work(String workName) {
        System.out.println("工作内容是" + workName);
        return this;
    }

    @Override
    public String time() {
        return null;
    }
}
