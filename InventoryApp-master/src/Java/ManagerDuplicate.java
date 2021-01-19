package Java;

import DB.ConnectionDB;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * Interfata folosita pentru functiile duplicate
 */

interface IDuplicate {
    void updateItem(Item item, Item item_up);

    void deleteUser(User user);
}

/**
 * Functii folosite atat de User cat si de Admin
 * specifice bazei de date.
 */

public class ManagerDuplicate implements IDuplicate {

    /**
     * Functie pt update.
     *
     * @param  item   obiectul care va fi modificat
     * @param  itemUp   obiectul cu care se va modifica.
     */

    @Override
    public void updateItem(Item item, Item itemUp) {
        Document query = new Document("Code", item.code);
        Document found = (Document) ConnectionDB.collectionItem.find(query).first();

        if (found != null) {
            Bson updatedvalue = new Document("Name", itemUp.name)
                    .append("Code", itemUp.code)
                    .append("Amount", itemUp.amount)
                    .append("Price", itemUp.price);
            Bson updateoperation = new Document("$set", updatedvalue);
            ConnectionDB.collectionItem.updateMany(found, updateoperation);
        }
    }

    /**
     * Stergere user din baza de date.
     *
     * @param  user   obiectul care va fi sters
     */

    @Override
    public void deleteUser(User user) {
        Document d = new Document("First Name", user.getFirstName())
                .append("Last Name", user.getLastName())
                .append("Age", user.getAge())
                .append("Username", user.username)
                .append("Password", user.password)
                .append("Mail adress", user.getMailAddressUser());
        Document found = (Document) ConnectionDB.collectionLogin.find(d).first();
        if (found != null) {
            ConnectionDB.collectionLogin.deleteOne(d);
        }
    }
}