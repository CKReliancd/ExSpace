package guava;

public class Person implements Comparable<Person> {

    private String lastName;
    private String firstName;
    private int zipCode;


    @Override
    public int compareTo(Person other) {
        int i = lastName.compareTo(other.lastName);



        return 0;
    }
}
