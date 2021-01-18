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
 * Controller pentru scena de autentificarea ca Admin.
 */

public class LoginAsAdminController implements Initializable {

    /**
     * Obiecte JavaFX pentru popularea scenei curente.
     */

    //GENERAL USE
    @FXML
    private Stage stage = new Stage();

    @FXML
    private TextField serialInput = new TextField();
    @FXML
    private TextField idInput = new TextField();
    @FXML
    private PasswordField passInput = new PasswordField();
    @FXML
    private TextField passInputVisible = new TextField();
    @FXML
    private Button quitButton = new Button();
    @FXML
    private CheckBox checkBoxLogin = new CheckBox();

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
        serialInput.clear();
        idInput.clear();
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

    private void pass(TextField passInput_visible) throws IOException {
        if (serialInput.getText().equals("") || idInput.getText().equals("") || passInput_visible.getText().equals(""))
            AlertBox.display("Alert", "To login you must complete all fields!");
        else {
            Document d = new Document("Login serial", serialInput.getText().trim()).append("admin ID", idInput.getText().trim()).append("Password", passInput_visible.getText().trim());
            if (DuplicateFunc.verifyLogin(d, ConnectionDB.collectionAdmin, "Wrong Admin ID or Login serial number or Password", "Alert!")) {
                Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("AfterLoginAdmin.fxml"));
                Main.window.getScene().setRoot(LoginAdminParent);
            }
        }
    }

    /**
     * Buton care duce la scena anterioara.
     */

    @FXML
    private void backButtonAction() throws IOException {
        Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Main.window.getScene().setRoot(LoginAdminParent);
    }

    /**
     * Functie care determina daca check box-ul este bifat sau nu
     * pentru a arata sau ascunde parola.
     */

    @FXML
    private void checkBoxLoginAction() {
        checkBox(checkBoxLogin, passInputVisible, passInput);
    }

    public static void checkBox(CheckBox checkBox_login, TextField passInput_visible, PasswordField passInput) {
        if (checkBox_login.isSelected()) {
            passInput_visible.setText(passInput.getText());
            passInput_visible.setVisible(true);
            passInput.setVisible(false);
            return;
        }
        passInput.setText(passInput_visible.getText());
        passInput.setVisible(true);
        passInput_visible.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkBoxLoginAction();
    }
}