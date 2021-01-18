package JavaFX;

import Classes.DuplicateFunc;
import DB.ConnectionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller pentru scena de autentificarea ca User.
 */

public class Controller implements Initializable {

    /**
     * Obiecte JavaFX pentru popularea scenei curente.
     */

    @FXML
    private Stage stage = new Stage();

    //LOGIN SCENE
    @FXML
    private TextField usernameInput = new TextField();
    @FXML
    private PasswordField passInput = new PasswordField();
    @FXML
    private TextField passInputVisible = new TextField();
    @FXML
    private Button quitButton = new Button();
    @FXML
    private CheckBox checkBoxLogin = new CheckBox();

    /**
     * Buton care duce la scena anterioara.
     */

    @FXML
    private void backButtonAction() throws IOException {
        Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Main.window.getScene().setRoot(LoginAdminParent);
    }

    /**
     * Buton care permite iesirea din aplicatie.
     */

    @FXML
    private void quitButtonAction() {
        stage = (Stage) quitButton.getScene().getWindow();
        if (ConfirmBox.display("Confirm Quit", "Are you sure you want to quit?")) stage.close();
        else stage.getScene().getWindow();
    }

    /**
     * Butonul Clear va sterge tot textul inserat in campurile de text.
     */

    @FXML
    private void clearButtonAction() {
        usernameInput.clear();
        passInput.clear();
    }

    /**
     * Permite autentificarea.
     */

    @FXML
    private void loginButtonAction() throws IOException {
        if(checkBoxLogin.isSelected()){
            pass(passInputVisible);
        }
        else{
            pass(passInput);
        }
    }

    /**
     * Verifica parola.
     */

    private void pass(TextField passInput) throws IOException {
        if (usernameInput.getText().equals("") || passInput.getText().equals(""))
            AlertBox.display("Alert", "To login you must complete all fields!");
        else {
            Document d = new Document("Username", usernameInput.getText().trim()).append("Password", passInput.getText().trim());
            if (DuplicateFunc.verifyLogin(d, ConnectionDB.collectionLogin, "Wrong Username or Password!", "Alert!")) {
                Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("AfterLoginUser.fxml"));
                Main.window.getScene().setRoot(LoginAdminParent);
            }
        }
    }

    /**
     * Trece in scena de autentificare ca Admin.
     */

    @FXML
    private void loginAdminButtonAction() throws IOException {
        Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("LoginAsAdmin.fxml"));
        Main.window.getScene().setRoot(LoginAdminParent);
    }

    /**
     * Trece in scena de inregistrare.
     */

    @FXML
    private void registerButtonAction() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Main.window.getScene().setRoot(pane);
    }

    /**
     * Functie care determina daca check box-ul este bifat sau nu
     * pentru a arata sau ascunde parola.
     */

    @FXML
    private void checkBoxLoginAction() {
        LoginAsAdminController.checkBox(checkBoxLogin, passInputVisible, passInput);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkBoxLoginAction();
    }
}