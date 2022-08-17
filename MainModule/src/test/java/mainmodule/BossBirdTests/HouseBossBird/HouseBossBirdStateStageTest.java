package mainmodule.BossBirdTests.HouseBossBird;

import BossBirdsTypeA.BossBirdStack.BossBirdStack;
import ModuleAbstractClasses.ModuleAbstractClasses.Enums.BulletTransitionFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ModuleAbstractClasses.ModuleAbstractClasses.Transitions.AvatarTransitions.AvatarTransitions.AvatarTransition;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.Menu;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.MenuStack;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.ViewController;
import ModuleAbstractClasses.ModuleAbstractClasses.Game;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.BossBird.BossBird;

public class HouseBossBirdStateStageTest extends Application implements BulletTransitionFactory {
    AnchorPane anchorPane;

    @Override
    public void start(Stage stage) throws Exception {
        initializeStageComponents();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    private void initializeStageComponents() {
        initializeMainPageAnchorPane();
        initializeMenuStack();
        initializeEnemy();
        AvatarTransition.getInstance().play();
    }

    private void initializeEnemy() {
        anchorPane.getChildren().add(BossBird.getInstance());
        BossBird.getInstance().initializeNewBossBirdAndItsTransitions();
        BossBird.getInstance().setId("bossBird");
    }

    private void initializeMainPageAnchorPane() {
        anchorPane = new AnchorPane();
        anchorPane.setPrefSize(1280 + 680, 720);
        anchorPane.setId("mainPageAnchorPane");
    }

    private void initializeMenuStack() {
        MenuStack.getInstance().pushMenu(new Menu(anchorPane, new ViewController() {
        }));
        MenuStack.getInstance().setCurrentGame(new Game(new ProgressBar(), BossBirdStack.getFullBossBirdStack()));
    }

}
