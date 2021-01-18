package Classes;

/**
 * Clasa folosita pentru a defini obiectele comercializate.
 */

public class Item {

    /**
     * Numele obiectului de tip String.
     */

    public String name;

    /**
     * Codul cu care va fi trecut in baza de date.
     */

    public Integer code;

    /**
     * Cantitatea in care se comercializeaza.
     */

    public Integer amount;

    /**
     * Pretul la care se vinde obiectul.
     */

    public Integer price;

    /**
     * Constructor cu toate atributele.
     */

    public Item(String name, Integer code, Integer amount, Integer price) {
        this.name = name;
        this.code = code;
        this.amount = amount;
        this.price = price;
    }

    /**
     * Cnstructor doar pentru nume.
     */

    public Item(String name) {
        this.name = name;
    }

    /**
     * Contructor doar pentru cod.
     */

    public Item(Integer code) {
        this.code = code;
    }

    /**
     * Contructor gol.
     */

    public Item() {
    }

    /**
     * Tipul de afisare a datelor obiectului sub forma de sir de caractere
     */

    @Override
    public String toString() {
        return  "Name: " + name + '\n' +
                "Code: " + code + '\n' +
                "Amount: " + amount + '\n' +
                "Price: " + price + '\n';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}