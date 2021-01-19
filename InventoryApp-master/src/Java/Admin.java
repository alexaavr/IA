package Java;

/**
 * Clasa Admin este folosita pentru drepturile de administrare ca Admin
 * adica se pot manipula resursele umane cat si obiectele.
 */

public class Admin extends Person {

    /**
     * Un tip intreg pentru a retine codul numericc de inregistrare al adminului
     */
    public int loginSerial;
    /**
     * Alias-ul cu care administratorul va accesa aplicata
     */
    public String adminID;
    /**
     * Parola administratorului care va fi un sir de caractere (cel putin 8)
     */
    public String password;
    /**
     * Adresa de mail
     */
    private String mailAddress;

    /**
     * Contructor cu toate atributele clasei
     */
    public Admin(String firstName, String lastName, int age, int loginSerial, String adminID, String password, String mail_adress) {
        super(firstName, lastName, age);
        this.loginSerial = loginSerial;
        this.adminID = adminID;
        this.password = password;
        this.mailAddress = mail_adress;
    }

    /**
     * Constructor gol
     */
    public Admin() {

    }

    /**
     * Se retureneaza adresa de mail
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * Se seteaza adresa de mail
     */
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}