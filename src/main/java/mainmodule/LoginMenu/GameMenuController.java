package mainmodule.LoginMenu;

import mainmodule.View.Menus.Menu;
import mainmodule.View.Menus.MenuStack;
import mainmodule.View.Menus.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class GameMenuController implements ViewController {


    public void checkForResult(MouseEvent mouseEvent) {

    }

    public void startGame(ActionEvent actionEvent) {
        MenuStack.getInstance().pushMenu(Menu.pushMenu("Game.fxml"));
    }
}
