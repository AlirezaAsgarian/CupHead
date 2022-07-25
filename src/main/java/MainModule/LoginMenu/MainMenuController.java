package MainModule.LoginMenu;

import javafx.event.ActionEvent;

import java.io.IOException;

public class MainMenuController {
    public void StartGame(ActionEvent actionEvent) throws IOException {
        LoginMenu.changeRoot("FXML/test.fxml");
    }
}
