package Java;

/**
 * Clasa cu atributele unui User
 */

public class User extends Person {

    /**
     * Sir de caractere pt autentificare.
     */

    public String username;

    /**
     * Parola, sir de caractere pt autentificare (minim 8 caractere).
     */

    public String password;

    /**
     * Adresa de mail a user - ului.
     */

    private String mailAddressUser;

    /**
     * Contructor cu toate atributele clasei
     */

    public User(String firstName, String lastName, int age, String username, String password, String mail_adress) {
        super(firstName, lastName, age);
        this.username = username;
        this.password = password;
        this.mailAddressUser = mail_adress;
    }

    /**
     * Contructor cu username-ul.
     */

    public User(String username) {
        this.username = username;
    }

    /**
     * Contructor gol.
     */

    public User() {
        super();
    }

    public String getMailAddressUser() {
        return mailAddressUser;
    }

    public void setMailAddressUser(String mailAdressU) {
        this.mailAddressUser = mailAdressU;
    }

    /**
     * Tipul de afisare a datelor obiectului sub forma de sir de caractere.
     */

    @Override
    public String toString() {
        return  "Firstname: " + getFirstName() + "\n" +
                "Lastname: " + getLastName() + "\n" +
                "Username: " + username + '\n' +
                "Password: " + password + '\n' +
                "Mail address: " + mailAddressUser + '\n' +
                "Age: " + getAge() + "\n";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}