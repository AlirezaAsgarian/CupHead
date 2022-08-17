package mainmodule.LoginMenu;

import ModuleAbstractClasses.ModuleAbstractClasses.Menus.Menu;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.MenuStack;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class GameMenuController implements ViewController {


    public void checkForResult(MouseEvent mouseEvent) {

    }

    public void startGame(ActionEvent actionEvent) {
        MenuStack.getInstance().pushMenu(Menu.pushMenu("Game.fxml"));
    }
}
