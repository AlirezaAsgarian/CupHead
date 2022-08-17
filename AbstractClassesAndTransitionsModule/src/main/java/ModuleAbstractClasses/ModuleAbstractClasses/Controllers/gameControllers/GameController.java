package ModuleAbstractClasses.ModuleAbstractClasses.Controllers.gameControllers;

import ModuleAbstractClasses.ModuleAbstractClasses.Managers.TransitionManager;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.Menu;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.MenuStack;
import javafx.event.ActionEvent;
import javafx.application.Platform;

import java.util.Optional;

public class GameController {


    public static void endGame() {
        if (MenuStack.getInstance().getCurrentGame().isEnd()) return;
        else MenuStack.getInstance().getCurrentGame().setEnd(true);
        stopGame();
        showEndGamePopUpAndProceedItsResult();
    }

    public static void stopGame() {
        TransitionManager.stopTransitions();
        TransitionManager.clearTransitions();
        MenuStack.getInstance().setCurrentGame(null);
    }

    private static void showEndGamePopUpAndProceedItsResult() {
        Platform.runLater(() -> {
            Optional<String> result = new EndGameDialog("win").showAndWait();
            processResultOfEndGamePopUp(result.get());
        });
    }

    public static void processResultOfEndGamePopUp(String result) {
        if (result.equals("Retry")) {
            MenuStack.getInstance().backToPreviousMenu();
            MenuStack.getInstance().pushMenu(Menu.pushMenu("Game.fxml"));
        } else {
            MenuStack.getInstance().backToPreviousMenu();
        }
    }

    public void makeGameBlackAndWhite(ActionEvent actionEvent) {
    }
}
