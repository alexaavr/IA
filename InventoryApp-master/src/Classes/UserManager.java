package Classes;

import DB.ConnectionDB;
import org.bson.Document;

/**
 * Interfata este utilizata pentru a clarifica atributiile unui User
 * si pentru a le separa de cele ale unui Admin.
 */

interface IUser {
    boolean findItem(Item item);

    boolean findItemByCode(Item item);

    String displayItem(Item item);

    void updateItem(Item item, Item item_up);

    void deleteUser(User user);
}

/**
 * Defineste functiile pe care le poate avea un User
 */

public class UserManager implements IUser {

    ManagerDuplicate managerDuplicate = new ManagerDuplicate();

    /**
     * Functie care preia informatiile despre item si le transforma in tipul bazei de date.
     */

    public static String getString(Item item) {
        Document d = new Document("Name", item.name);
        Document found = (Document) ConnectionDB.collectionItem.find(d).first();
        if (found != null) {
            item.name = found.get("Name").toString();
            item.code = Integer.parseInt(found.get("Code").toString());
            item.amount = Integer.parseInt(found.get("Amount").toString());
            item.price = Integer.parseInt(found.get("Price").toString());
        }
        return item.toString();
    }

    /**
     * Gasire item din baza de date.
     *
     * @param  item   obiectul care va fi cautat
     * @return        un flag care arata valoarea booleana, daca s a gasit sau nu obiectul
     */

    @Override
    public boolean findItem(Item item) {
        boolean flag = true;
        Document d = new Document("Name", item.name);
        if (ConnectionDB.collectionItem.find(d).first() == null)
            flag = false;
        return flag;
    }

    /**
     * Gasire item din baza de date dupa codul acestuia.
     *
     * @param  item   obiectul care va fi cautat
     * @return        un flag care arata valoarea booleana, daca s a gasit sau nu obiectul
     */

    @Override
    public boolean findItemByCode(Item item) {
        boolean flag = true;
        Document d = new Document("Code", item.code);
        if (ConnectionDB.collectionItem.find(d).first() == null)
            flag = false;
        return flag;
    }

    /**
     * Afisare date item intr-un frame.
     *
     * @param  item   obiectul care va fi afisat
     */

    @Override
    public String displayItem(Item item) {
        return getString(item);
    }

    /**
     * Functie pt update.
     *
     * @param  item   obiectul care va fi modificat
     * @param  itemUp   obiectul cu care se va modifica.
     */

    @Override
    public void updateItem(Item item, Item itemUp) {
        managerDuplicate.updateItem(item, itemUp);
    }

    /**
     * Stergere user din baza de date.
     *
     * @param  user   obiectul care va fi sters.
     */

    @Override
    public void deleteUser(User user) {
        managerDuplicate.deleteUser(user);
    }
}