package Classes;

/**
 * Clasa abstracta din care se trag glasele principale care denumesc persoane
 */

public abstract class Person {

    /**
     * Prenumele persoane.
     */

    private String firstName;

    /**
     * Numele persoanei.
     */

    private String lastName;

    /**
     * Varsta.
     */

    private int age;

    /**
     * Contructor cu toate atributele clasei
     */

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * Contructor gol.
     */

    public Person() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}