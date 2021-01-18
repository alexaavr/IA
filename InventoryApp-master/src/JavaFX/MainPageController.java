package JavaFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller pentru scena de autentificarea ca Admin.
 */

public class MainPageController {

    /**
     * Obiecte JavaFX pentru popularea scenei curente.
     */

    //GENERAL USE
    @FXML
    private Stage stage = new Stage();

    /**
     * Buton care face trecerea la scena pentru public.
     */

    @FXML
    private void publicButtonAction() throws IOException {
        Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("public.fxml"));
        Main.window.getScene().setRoot(LoginAdminParent);
    }

    /**
     * Buton care face trecerea la scena pentru autentificare ca User.
     */

    @FXML
    private void loginButtonAction() throws IOException {
        Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Main.window.getScene().setRoot(LoginAdminParent);
    }

    /**
     * Buton care permite iesirea din aplicatie.
     */

    @FXML
    private void quitButtonAction() {
        if (ConfirmBox.display("Confirm Quit", "Are you sure you want to quit?")) Main.window.close();
        else stage.getScene().getWindow();
    }
}