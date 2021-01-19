package JavaFX;

import Java.*;
import DB.ConnectionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller pentru scena pentru public.
 */

public class publicController implements Initializable {

    /**
     * Obiecte de tipul claselor create folosite penntru anumite functionalitati.
     */

    //NECESSARY
    @FXML
    private Stage stage = new Stage();
    private Item item = new Item();
    private UserManager userManager = new UserManager();
    private DuplicateFunc duplicateFunc = new DuplicateFunc();
    private Admin admin = new Admin();

    /**
     * Obiecte JavaFX pentru popularea scenei curente.
     */

    @FXML
    private Button re = new Button();
    @FXML
    private TextField searchInput = new TextField();
    @FXML
    private TextArea text = new TextArea();

    //TableView
    @FXML
    private TableView<Item> tableView;

    /**
     * Verificarea stocului.
     */

    @FXML
    private void search() {
        if (searchInput.getText().equals("")){ AlertBox.display("Alert", "You must complete all fields!");}
        else {
            item.name = searchInput.getText().trim();
            if (!userManager.findItem(item)) text.setText("Item not found!");
            else text.setText("Your item is: \n" + userManager.displayItem(item));
        }
    }

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
        if (ConfirmBox.display("Confirm Quit", "Are you sure you want to quit?")) stage.close();
        else stage.getScene().getWindow();
    }

    /**
     * Functie pentru rezervarea unui obiect.
     * Cand butonul s a apsat se si scade cantitatea din stoc.
     */

    @FXML
    public void reserveButton(){
        if(text.getText().equals("Your item is: \n" + userManager.displayItem(item))){
            if(item.amount <= 0){
                text.setText("Sold out!");
            }
            else{
                text.setText("Your item is reserved!");
                Item itemToUP = item;
                itemToUP.amount = item.amount - 1;
                userManager.updateItem(item, itemToUP);
                tableView.getItems().clear();
                tableView.setItems(duplicateFunc.getItems(ConnectionDB.collectionItem));
            }
        }
    }

    /**
     * Functie pentru populare TableView pentru Item.
     */

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setItems(duplicateFunc.getItems(ConnectionDB.collectionItem));
        tableView.refresh();
    }
}