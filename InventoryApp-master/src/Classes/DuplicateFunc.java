package Classes;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;
import JavaFX.AlertBox;

import java.util.regex.Pattern;

/**
 * Clasa a fost scrisa pentru a separa functiile statice folosite pe tot parcursul programului,
 * dar si functiile pentru popularea ObservableList care vor fi afisate in TableView.
 */

public class DuplicateFunc {

    /**
     * Obiectele pentru listele din TableView si
     * cursorul folosit pentru a cauta in baza de date.
     */

    public ObservableList<Item> duplicate = FXCollections.observableArrayList();
    public ObservableList<User> duplicateU = FXCollections.observableArrayList();
    public MongoCursor<Document> cursor;

    /**
     * Functie de verificare login. mai exact, compara datele scrise la autentificare
     * cu datele existente in baza de date
     * in cazul in care nu exista o sa se declanseze o fereastra cu mesaj de eroare.
     */

    public static boolean verifyLogin(Document uDB, MongoCollection<Document> coll, String message, String title) {
        Document found = coll.find(uDB).first();
        if (found != null) {
            return true;
        } else {
            return AlertBox.display(title, message);
        }
    }

    /**
     * Verificare daca adresa de mail se incadreaza in standardele specifice (contine @, de ex. yahoo.com).
     */

    public static boolean isValidMail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    /**
     * Verificare la inregistrare daca parola are litera mare, numere, caracter special si cel putin 8 caractere.
     */

    public static boolean isValid(String passwordHere) {

        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        boolean flag = true;
        if (passwordHere.length() < 8 || !(specialCharPatten.matcher(passwordHere).find())
                || !(UpperCasePatten.matcher(passwordHere).find()) || !(lowerCasePatten.matcher(passwordHere).find())
                || !(digitCasePatten.matcher(passwordHere).find()))
            flag = false;

        return flag;
    }

    /**
     * Folosita pentru a afisa in TableView item-ele din baza de date.
     */

    public ObservableList<Item> getItems(MongoCollection coll) {
        try {
            cursor = coll.find().iterator();

            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String name = doc.get("Name").toString();
                int code = Integer.parseInt(doc.get("Code").toString());
                int amount = Integer.parseInt(doc.get("Amount").toString());
                int price = Integer.parseInt(doc.get("Price").toString());
                duplicate.add(new Item(name, code, amount, price));
            }
        } finally {
            cursor.close();
        }

        return duplicate;
    }

    /**
     * Folosita pentru a afisa in TableView User-ii din baza de date.
     */

    public ObservableList<User> getUsers(MongoCollection coll) {

        cursor = coll.find().iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            String username = doc.get("Username").toString();
            String password = doc.get("Password").toString();
            String LastName = doc.get("Last Name").toString();
            String FirstName = doc.get("First Name").toString();
            String Mail_adress = doc.get("Mail adress").toString();
            int Age = Integer.parseInt(doc.get("Age").toString());
            duplicateU.add(new User(FirstName, LastName, Age, username, password, Mail_adress));
        }

        return duplicateU;
    }
}