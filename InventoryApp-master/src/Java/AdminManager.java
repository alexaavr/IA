package Java;

import DB.ConnectionDB;
import Interfaces.IAdmin;
import org.bson.Document;
import org.bson.conversions.Bson;

import static Java.UserManager.getString;

/**
 * Clasa AdminManager inglobeaza tote functiile pe care un Admin le va folosi
 * pe parcursul activitatii sale cu aplicatia.
 * Toate functiile de adaugare, modificare si stergere sunt scrise pentru specificul bazei de date folosite, MongoDB.
 */

public class AdminManager implements IAdmin {

    /**
     * Variabila pentru a folosi functii care sunt la duplicat (pentru Admin si User).
     */

    ManagerDuplicate managerDuplicate;
    public AdminManager() { 	managerDuplicate = new ManagerDuplicate();}

    /**
     * Adaugare item in baza de date.
     *
     * @param  item   obiectul care va fi adaugat
     */

    @Override
    public void addItem(Item item) {
        Document d = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        ConnectionDB.collectionItem.insertOne(d);
    }

    /**
     * Stergere item din baza de date.
     *
     * @param  item   obiectul care va fi sters
     */

    @Override
    public void deleteItem(Item item) {
        Document d = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        Document found = (Document) ConnectionDB.collectionItem.find(d).first();
        if (found != null) {
            ConnectionDB.collectionItem.deleteOne(d);
        }
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

    /**
     * Adaugare user in baza de date.
     *
     * @param  user   obiectul care va fi adaugat.
     */

    @Override
    public void addUser(User user) {
        Document d = new Document("First Name", user.getFirstName())
                .append("Last Name", user.getLastName())
                .append("Age", user.getAge())
                .append("Username", user.username)
                .append("Password", user.password)
                .append("Mail adress", user.getMailAddressUser());
        ConnectionDB.collectionLogin.insertOne(d);
    }

    /**
     * Functie pt update.
     *
     * @param  user   obiectul care va fi modificat
     * @param  userUp   obiectul cu care se va modifica.
     */

    @Override
    public void updateUser(User user, User userUp) {
        Document query = new Document("Username", user.username);
        Document found = (Document) ConnectionDB.collectionLogin.find(query).first();

        if (found != null) {
            Bson updatedValue = new Document("First Name", userUp.getFirstName())
                    .append("Last Name", userUp.getLastName())
                    .append("Age", userUp.getAge())
                    .append("Username", userUp.username)
                    .append("Password", userUp.password)
                    .append("Mail adress", userUp.getMailAddressUser());
            Bson updateOperation = new Document("$set", updatedValue);
            ConnectionDB.collectionLogin.updateOne(found, updateOperation);
        }
    }

    /**
     * Gasire user in baza de date.
     *
     * @param  user   obiectul care va fi cautat
     * @return        un flag care arata valoarea booleana, daca s a gasit sau nu obiectul
     */

    @Override
    public boolean findUser(User user) {
        boolean flag = false;
        Document d = new Document("Username", user.username);
        if (ConnectionDB.collectionLogin.find(d).first() != null)
            flag = true;
        return flag;
    }

    /**
     * Afisare date user intr-un frame.
     *
     * @param  user   obiectul care va fi afisat
     */

    @Override
    public String displayUser(User user) {
        Document d = new Document("Username", user.username);
        Document found = (Document) ConnectionDB.collectionLogin.find(d).first();
        if (found != null) {
            user.username = found.get("Username").toString();
            user.password = found.get("Password").toString();
            user.setMailAddressUser(found.get("Mail adress").toString());
            user.setFirstName(found.get("First Name").toString());
            user.setLastName(found.get("Last Name").toString());
            user.setAge(Integer.parseInt(found.get("Age").toString()));
        }
        return user.toString();
    }
}