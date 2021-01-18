package JavaFX;

import Classes.AdminManager;
import Classes.DuplicateFunc;
import Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller pentru scena de inregistrare ca User.
 */

public class RegisterController implements Initializable {

    /**
     * Obiecte de tipul claselor create folosite penntru anumite functionalitati.
     */

    //GENERAL USE
    private User user = new User();
    private AdminManager adminManager = new AdminManager();

    /**
     * Obiecte JavaFX pentru popularea scenei curente.
     */

    //LOGIN SCENE
    @FXML
    private Stage stage = new Stage();
    @FXML
    private TextField usernameInput = new TextField();
    @FXML
    private TextField firstnameInput = new TextField();
    @FXML
    private TextField lastnameInput = new TextField();
    @FXML
    private TextField mailInput = new TextField();
    @FXML
    private TextField ageInput = new TextField();
    @FXML
    private PasswordField passInput = new PasswordField();
    @FXML
    private TextField passInputVisible = new TextField();
    @FXML
    private Button quitButton = new Button();
    @FXML
    private CheckBox checkBoxRegister = new CheckBox();

    /**
     * Functie care determina daca check box-ul este bifat sau nu
     * pentru a arata sau ascunde parola.
     */

    @FXML
    private void checkBoxRegisterAction() {
        LoginAsAdminController.checkBox(checkBoxRegister, passInputVisible, passInput);
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
        firstnameInput.clear();
        lastnameInput.clear();
        usernameInput.clear();
        passInput.clear();
        mailInput.clear();
        ageInput.clear();
    }

    /**
     * Buton care duce la scena anterioara.
     */

    @FXML
    private void backButtonAction() throws IOException {
        Parent RegisterParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Main.window.getScene().setRoot(RegisterParent);
    }

    /**
     * Buton de inregistrare.
     */

    @FXML
    private void registerButtonAction() {
        if(checkBoxRegister.isSelected()){
            pass(passInputVisible);
        }
        else{
            pass(passInput);
        }
    }

    /**
     * Verifica parola.
     */

    private void pass(TextField passInput_visible) {
        if (usernameInput.getText().equals("") || passInput_visible.getText().equals("") || mailInput.getText().equals("") || firstnameInput.getText().equals("")
                || lastnameInput.getText().equals("") || ageInput.getText().equals(""))
            AlertBox.display("Alert", "To register you must complete all fields!");
        else {
            if (!DuplicateFunc.isValid(passInput_visible.getText()))
                AlertBox.display("Alert", "Password must contain: \n " +
                        "-at least 8 characters;\n" +
                        "-at least an Uppercase;\n " +
                        "-at least an Lowercase;\n " +
                        "-at least a special character!");
            else if (!DuplicateFunc.isValidMail(mailInput.getText().trim()))
                AlertBox.display("Alert", "Incorrect mail address!");
            else {
                try {
                    AfterLoginUserController.userAction(user, usernameInput, passInput_visible, mailInput, firstnameInput, lastnameInput, ageInput);
                    adminManager.addUser(user);

                    AlertBox.display("Congratulations", "You registered successfully");
                    Parent Parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    Main.window.getScene().setRoot(Parent);
                } catch (NumberFormatException | IOException ex) {
                    AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkBoxRegisterAction();
    }
}