package guava;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

public class Guava001 {

    public static void main(String[] args) {


    }

    @Test
    private void OptionalAndObjects() {
        Optional<Integer> integerOptional = Optional.of(5);
        Integer integer = integerOptional.get();
        System.out.println(integer);

        Object or = Optional.fromNullable(null);
        System.out.println(or);

        System.out.println(Objects.equal(null, 1));
        System.out.println(Objects.equal(null, "String"));
        System.out.println(Objects.equal("String", "String"));
        System.out.println(Objects.equal(127, 127));
        System.out.println(Objects.equal(127, "127"));
        System.out.println(Objects.equal(131, 131));

        System.out.println(Objects.hashCode(8, "asdf"));

        //
//        Optional<Object> absent = Optional.absent();
//        boolean present = absent.isPresent();
//        System.out.println(present);
//
//        Optional<Object> fromNullable = Optional.fromNullable(null);


    }

}
