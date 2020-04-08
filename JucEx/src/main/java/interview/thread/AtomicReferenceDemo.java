package interview.thread;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicReference;

@Setter
@Getter
@NoArgsConstructor
class User{
    String userName;
    int age;
    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {

        User z3 = new User("z3", 22);
        User li4 = new User("li4", 25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, li4)+"\t"+atomicReference.get());

        System.out.println(atomicReference.compareAndSet(z3, li4)+"\t"+atomicReference.get());


    }

}
