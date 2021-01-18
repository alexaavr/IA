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

public class Controller implements Initializable {

    //GENERAL USE
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

    @FXML
    private void backButtonAction() throws IOException {
        Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Main.window.getScene().setRoot(LoginAdminParent);
    }

    //QUIT BUTTON ACTION
    @FXML
    private void quitButtonAction() {
        stage = (Stage) quitButton.getScene().getWindow();
        if (ConfirmBox.display("Confirm Quit", "Are you sure you want to quit?")) stage.close();
        else stage.getScene().getWindow();
    }

    //CLEAR BUTTON ACTION
    @FXML
    private void clearButtonAction() {
        usernameInput.clear();
        passInput.clear();
    }

    //LOGIN BUTTON ACTION

    @FXML
    private void loginButtonAction() throws IOException {
        if(checkBoxLogin.isSelected()){
            pass(passInputVisible);
        }
        else{
            pass(passInput);
        }
    }

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


    //LOGIN AS ADMIN BUTTON
    @FXML
    private void loginAdminButtonAction() throws IOException {
        Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("LoginAsAdmin.fxml"));
        Main.window.getScene().setRoot(LoginAdminParent);
    }

    //REGISTER BUTTON
    @FXML
    private void registerButtonAction() throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Main.window.getScene().setRoot(pane);
    }

    //CHECK BOX ACTION
    @FXML
    private void checkBoxLoginAction() {
        LoginAsAdminController.checkBox(checkBoxLogin, passInputVisible, passInput);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkBoxLoginAction();
    }
}
