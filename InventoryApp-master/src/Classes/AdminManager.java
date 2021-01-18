package Classes;

import DB.ConnectionDB;
import org.bson.Document;
import org.bson.conversions.Bson;

import static Classes.UserManager.getString;

//interfata este utilizata pentru a clarifica atributiile unui Admin si pentru a le separa de cele ale unui User
interface IAdmin {
    void addItem(Item item);

    void deleteItem(Item item);

    boolean findItem(Item item);

    String displayItem(Item item);

    void updateItem(Item item, Item item_up);

    void deleteUser(User user);

    void addUser(User user);

    void updateUser(User user, User user_up);

    boolean findUser(User user);

    String displayUser(User user);
}

public class AdminManager implements IAdmin {

    //variabila pentru a folosi functii care sunt la comun pentruAdmin si User
    ManagerDuplicate managerDuplicate = new ManagerDuplicate();

    //adaugare item in baza de date
    @Override
    public void addItem(Item item) {
        Document d = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        ConnectionDB.collectionItem.insertOne(d);
    }

    //stergere item din baza de date
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

    //functie pentru a gasi un item in baza de date
    @Override
    public boolean findItem(Item item) {
        boolean flag = true;
        Document d = new Document("Name", item.name);
        if (ConnectionDB.collectionItem.find(d).first() == null)
            flag = false;
        return flag;
    }

    //afisare item intr-un frame
    @Override
    public String displayItem(Item item) {
        return getString(item);
    }

    //functii comuna cu User, se gasesc in clasa ManagerDuplicate
    @Override
    public void updateItem(Item item, Item itemUp) {
        managerDuplicate.updateItem(item, itemUp);
    }

    @Override
    public void deleteUser(User user) {
        managerDuplicate.deleteUser(user);
    }

    //adaugare User in baza de date
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

    //Modificare User in baza de date
    @Override
    public void updateUser(User user, User userUp) {
        Document query = new Document("Username", user.username);
        Document found = (Document) ConnectionDB.collectionLogin.find(query).first();

        if (found != null) {
            Bson updatedvalue = new Document("First Name", userUp.getFirstName())
                    .append("Last Name", userUp.getLastName())
                    .append("Age", userUp.getAge())
                    .append("Username", userUp.username)
                    .append("Password", userUp.password)
                    .append("Mail adress", userUp.getMailAddressUser());
            Bson updateoperation = new Document("$set", updatedvalue);
            ConnectionDB.collectionLogin.updateOne(found, updateoperation);
        }
    }

    @Override
    public boolean findUser(User user) {
        boolean flag = false;
        Document d = new Document("Username", user.username);
        if (ConnectionDB.collectionLogin.find(d).first() != null)
            flag = true;
        return flag;
    }

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

//toate functiile de adaugare, modificare si stergere sunt scrise pentru specificul bazei de date folosite, MongoDB
