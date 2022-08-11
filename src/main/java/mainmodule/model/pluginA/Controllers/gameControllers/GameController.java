package mainmodule.model.pluginA.Controllers.gameControllers;

import mainmodule.model.TransitionManager;
import mainmodule.View.EndGameDialog;
import mainmodule.View.Menus.Menu;
import mainmodule.View.Menus.MenuStack;
import javafx.application.Platform;

import java.util.Optional;

public class GameController {


    public static void stopGame() {
        TransitionManager.stopTransitions();
        TransitionManager.clearTransitions();
        MenuStack.getInstance().setCurrentGame(null);
    }
    public static void endGame() {
        if(MenuStack.getInstance().getCurrentGame().isEnd()) return;
        else MenuStack.getInstance().getCurrentGame().setEnd(true);
        stopGame();
        showEndGamePopUpAndProceedItsResult();
    }

    private static void showEndGamePopUpAndProceedItsResult() {
        Platform.runLater(() -> {
            Optional<String> result = new EndGameDialog("win").showAndWait();
            processResultOfEndGamePopUp(result.get());
        });
    }

    public static void processResultOfEndGamePopUp(String result){
        if(result.equals("Retry")){
            MenuStack.getInstance().backToPreviousMenu();
            MenuStack.getInstance().pushMenu(Menu.pushMenu("Game.fxml"));
        } else {
            MenuStack.getInstance().backToPreviousMenu();
        }
    }
}
