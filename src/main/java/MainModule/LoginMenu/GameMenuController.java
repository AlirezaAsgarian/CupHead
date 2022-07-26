package MainModule.LoginMenu;

import MainModule.View.Menus.Menu;
import MainModule.View.Menus.MenuStack;
import MainModule.View.Menus.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class GameMenuController implements ViewController {


    public void checkForResult(MouseEvent mouseEvent) {

    }

    public void startGame(ActionEvent actionEvent) {
        MenuStack.getInstance().addMenu(Menu.pushMenu("Game.fxml"));
    }
}
