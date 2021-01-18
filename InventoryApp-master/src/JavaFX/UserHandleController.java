package JavaFX;

import Classes.AdminManager;
import Classes.DuplicateFunc;
import Classes.User;
import DB.ConnectionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller pentru scena pentru manipularea resurselor umane
 * dupa autentificarea ca Admin.
 */

public class UserHandleController implements Initializable {

    /**
     * Obiecte de tipul claselor create folosite penntru anumite functionalitati.
     */

    //NECESSARY
    private User user = new User();
    private User userUP = new User();
    private AdminManager adminManager = new AdminManager();
    private DuplicateFunc duplicateFunc = new DuplicateFunc();

    /**
     * Obiecte JavaFX pentru popularea scenei curente.
     */

    @FXML
    private TextField usernameInput = new TextField();
    @FXML
    private TextField passwordInput = new TextField();
    @FXML
    private TextField firstnameInput = new TextField();
    @FXML
    private TextField lastnameInput = new TextField();
    @FXML
    private TextField mailInput = new TextField();
    @FXML
    private TextField ageInput = new TextField();

    @FXML
    private TextField usernameInput1 = new TextField();
    @FXML
    private TextField passwordInput1 = new TextField();
    @FXML
    private TextField firstnameInput1 = new TextField();
    @FXML
    private TextField lastnameInput1 = new TextField();
    @FXML
    private TextField mailInput1 = new TextField();
    @FXML
    private TextField ageInput1 = new TextField();

    //search
    @FXML
    private TextField searchInput = new TextField();
    @FXML
    private TextArea text = new TextArea();

    //TABLE VIEW
    @FXML
    private TableView<User> tableView;

    /**
     * Butonul Clear va sterge tot textul inserat in campurile de text.
     */

    @FXML
    private void clearButtonAction() {
        usernameInput.clear();
        passwordInput.clear();
        lastnameInput.clear();
        firstnameInput.clear();
        mailInput.clear();
        ageInput.clear();
    }

    /**
     * Verificarea existentei persoanei.
     */

    @FXML
    private void search() {
        if (searchInput.getText().equals("")) AlertBox.display("Alert", "You must complete all fields!");
        else {
            user.username = searchInput.getText().trim();
            if (adminManager.findUser(user)) {
                text.setText("User found! \n" + adminManager.displayUser(user));
                usernameInput1.setText(user.username);
                passwordInput1.setText(user.password);
                mailInput1.setText(user.getMailAddressUser());
                lastnameInput1.setText(user.getLastName());
                firstnameInput1.setText(user.getFirstName());
                ageInput1.setText(String.valueOf(user.getAge()));
            }
            else text.setText("User not found! \n");
        }
    }

    /**
     * Butonul Update va inlocui toate datele pentru userul selectat cu noile date inserate.
     */

    @FXML
    private void updateUserButtonAction() {
        if (usernameInput1.getText().equals("") || passwordInput1.getText().equals("") || mailInput1.getText().equals("") || firstnameInput1.getText().equals("")
                || lastnameInput1.getText().equals("") || ageInput1.getText().equals("")
                || searchInput.getText().equals(""))
            AlertBox.display("Alert", "To update user you must complete all fields!");
        else {
            if (!DuplicateFunc.isValid(passwordInput1.getText()))
                AlertBox.display("Alert", "Password must contain: \n " +
                        "-at least 8 characters;\n" +
                        "-at least an Uppercase;\n " +
                        "-at least an Lowercase;\n " +
                        "-at least a special character!");
            else if (!DuplicateFunc.isValidMail(mailInput1.getText().trim()))
                AlertBox.display("Alert", "Incorrect mail address!");
            else {
                try {
                    userUP.username = searchInput.getText().trim();
                    AfterLoginUserController.userAction(user, usernameInput1, passwordInput1, mailInput1, firstnameInput1, lastnameInput1, ageInput1);
                    adminManager.updateUser(userUP, user);
                    tableView.getItems().clear();
                    tableView.setItems(duplicateFunc.getUsers(ConnectionDB.collectionLogin));
                    AlertBox.display("Alert", "User updated!");
                } catch (NumberFormatException ex) {
                    AlertBox.display("Alert", "Error: " + ageInput1.getText().trim().toUpperCase() + " is not a number!");
                }
            }
        }
    }

    /**
     * In momentul apasarii butonului de AddUser, user-ul se va adauga in lista.
     */

    @FXML
    private void addUserButtonAction() {
        if (usernameInput.getText().equals("") || passwordInput.getText().equals("") || mailInput.getText().equals("") || firstnameInput.getText().equals("")
                || lastnameInput.getText().equals("") || ageInput.getText().equals(""))
            AlertBox.display("Alert", "To register you must complete all fields!");
        else {
            if (!DuplicateFunc.isValid(passwordInput.getText()))
                AlertBox.display("Alert", "Password must contain: \n " +
                        "-at least 8 characters;\n" +
                        "-at least an Uppercase;\n " +
                        "-at least an Lowercase;\n " +
                        "-at least a special character!");
            else if (!DuplicateFunc.isValidMail(mailInput.getText().trim()))
                AlertBox.display("Alert", "Incorrect mail address!");
            else {
                try {
                    AfterLoginUserController.userAction(user, usernameInput, passwordInput, mailInput, firstnameInput, lastnameInput, ageInput);
                    adminManager.addUser(user);
                    tableView.getItems().clear();
                    tableView.setItems(duplicateFunc.getUsers(ConnectionDB.collectionLogin));
                    AlertBox.display("Alert", "User added!");
                } catch (NumberFormatException ex) {
                    AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
                }
            }
        }
    }

    /**
     * In momentul apasarii butonului de DeleteUser, user-ul se va sterge din lista.
     */

    @FXML
    private void deleteUserButtonAction() {
        if (usernameInput.getText().equals("") || passwordInput.getText().equals("") || mailInput.getText().equals("") || firstnameInput.getText().equals("")
                || lastnameInput.getText().equals("") || ageInput.getText().equals(""))
            AlertBox.display("Alert", "To register you must complete all fields!");
        else {
            try {
                AfterLoginUserController.userAction(user, usernameInput, passwordInput, mailInput, firstnameInput, lastnameInput, ageInput);
                adminManager.deleteUser(user);
                tableView.getItems().clear();
                tableView.setItems(duplicateFunc.getUsers(ConnectionDB.collectionLogin));
                AlertBox.display("Alert", "User deleted!");
            } catch (NumberFormatException ex) {
                AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
            }
        }
    }

    /**
     * In momentul apasarii butonului se va trece la scena pentru gestionare Item.
     */

    @FXML
    private void itemHandlingButton() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AfterLoginAdmin.fxml"));
        Parent pane = loader.load();
        Main.window.getScene().setRoot(pane);
    }

    /**
     * Butonul va iesi din aplicatie sau va ramane in aplicatie
     * in functie de ce se alege in fereastra de confirmare.
     */

    @FXML
    private void singOutButton() throws IOException {
        if (ConfirmBox.display("Alert!", " Are you sure you want to sing out?")) {
            Parent pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Main.window.getScene().setRoot(pane);
        }
    }

    /**
     * Functie pentru populare TableView pentru User.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setItems(duplicateFunc.getUsers(ConnectionDB.collectionLogin));
        tableView.refresh();
    }
}