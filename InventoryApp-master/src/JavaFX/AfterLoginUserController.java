package JavaFX;

import Classes.*;
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

public class AfterLoginUserController implements Initializable {

    //NECESSARY
    private User user = new User();
    private Item item = new Item();
    private Item itemToUP = new Item();
    private UserManager userManager = new UserManager();
    private AdminManager adminManager = new AdminManager();
    private DuplicateFunc duplicateFunc = new DuplicateFunc();

    //TEXT
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


    //Update
    @FXML
    private TextField nameInputUP = new TextField();
    @FXML
    private TextField codeInputUP = new TextField();
    @FXML
    private TextField amountInputUP = new TextField();
    @FXML
    private TextField priceInputUP = new TextField();
    @FXML
    private TextField codeInputToUP = new TextField();


    @FXML
    private TextField searchInput = new TextField();
    @FXML
    private TextArea text = new TextArea();
    @FXML
    private TextArea text2 = new TextArea();

    //TableView
    @FXML
    private TableView<Item> tableView;

    static void userAction(User user, TextField usernameInput, TextField passwordInput, TextField mailInput, TextField firstnameInput, TextField lastnameInput, TextField ageInput) {
        user.username = usernameInput.getText().trim();
        user.password = passwordInput.getText().trim();
        user.setMailAddressUser(mailInput.getText().trim());
        user.setFirstName(firstnameInput.getText().trim());
        user.setLastName(lastnameInput.getText().trim());
        user.setAge((Integer.parseInt(ageInput.getText().trim())));
    }

    //CLEAR
    @FXML
    private void clearButtonAction() {
        nameInputUP.clear();
        amountInputUP.clear();
        codeInputUP.clear();
        priceInputUP.clear();
    }

    //UPDATE
    @FXML
    private void updateItemButtonAction() {
        if (codeInputToUP.getText().equals(""))
            AlertBox.display("Alert", "You must complete the code to update!");
        else {
            try {
                itemToUP.code = Integer.parseInt(codeInputToUP.getText().trim());
                if (!userManager.findItemByCode(itemToUP)) AlertBox.display("Alert", "Item not found!");
                else {
                    if (nameInputUP.getText().equals("") || codeInputUP.getText().equals("") || amountInputUP.getText().equals("") || priceInputUP.getText().equals(""))
                        AlertBox.display("Alert", "You must complete all fields!");
                    else {
                        item.name = nameInputUP.getText().trim();
                        item.code = Integer.parseInt(codeInputUP.getText().trim());
                        item.amount = Integer.parseInt(amountInputUP.getText().trim());
                        item.price = Integer.parseInt(priceInputUP.getText().trim());
                        userManager.updateItem(itemToUP, item);
                        text2.setText("Item updated!");
                        tableView.getItems().clear();
                        tableView.setItems(duplicateFunc.getItems(ConnectionDB.collectionItem));
                    }
                }
            } catch (NumberFormatException ex) {
                AlertBox.display("Alert", "Error: "
                        + codeInputToUP.getText().trim().toUpperCase() + " \n or \n"
                        + codeInputUP.getText().trim().toUpperCase() + " \n or \n"
                        + amountInputUP.getText().trim().toUpperCase()
                        + "\n or \n" + priceInputUP.getText().trim().toUpperCase()
                        + " is not a number!");
            }
        }
    }

    //SEARCH
    @FXML
    private void search() {
        if (searchInput.getText().equals("")) AlertBox.display("Alert", "You must complete all fields!");
        else {
            item.name = searchInput.getText().trim();
            if (!userManager.findItem(item)) text.setText("Item not found!");
            else text.setText("Your item is: \n" + userManager.displayItem(item));
        }
    }

    //EXIT
    @FXML
    private void singOutButton() throws IOException {
        if (ConfirmBox.display("Alert!", " Are you sure you want to sing out?")) {
            Parent pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Main.window.getScene().setRoot(pane);
        }
    }

    //DELETE
    @FXML
    private void deleteAccountButton() throws IOException {
        if (usernameInput.getText().equals("") || passwordInput.getText().equals("") || mailInput.getText().equals("") || firstnameInput.getText().equals("")
                || lastnameInput.getText().equals("") || ageInput.getText().equals(""))
            AlertBox.display("Alert", "To delete account you must complete all fields!");
        else {
            try {
                userAction(user, usernameInput, passwordInput, mailInput, firstnameInput, lastnameInput, ageInput);
                if (DuplicateFunc.isValidMail(mailInput.getText().trim())) {
                    if (adminManager.findUser(user)) {
                        if (ConfirmBox.display("Alert", "Are you sure you want to delete your account?")) {
                            userManager.deleteUser(user);
                            AlertBox.display("Alert", "Account deleted!");
                            Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
                            Main.window.getScene().setRoot(LoginAdminParent);
                        }
                    } else {
                        AlertBox.display("Alert", "Account doesn'duplicateFunc exist!");
                    }
                } else AlertBox.display("Alert", " Wrong mail address!");
            } catch (NumberFormatException ex) {
                AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setItems(duplicateFunc.getItems(ConnectionDB.collectionItem));
        tableView.refresh();
    }
}
