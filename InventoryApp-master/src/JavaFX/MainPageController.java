package JavaFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;

public class MainPageController {
    //GENERAL USE
    @FXML
    private Stage stage = new Stage();

    @FXML
    private void publicButtonAction() throws IOException {
        Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("public.fxml"));
        Main.window.getScene().setRoot(LoginAdminParent);
    }
    @FXML
    private void loginButtonAction() throws IOException {
        Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Main.window.getScene().setRoot(LoginAdminParent);
    }

    @FXML
    private void quitButtonAction() {
        if (ConfirmBox.display("Confirm Quit", "Are you sure you want to quit?")) Main.window.close();
        else stage.getScene().getWindow();
    }
}
