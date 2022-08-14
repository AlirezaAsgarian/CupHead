package mainmodule.BossBirdTests.HouseBossBird;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainmodule.View.AvatarTransitions.AvatarTransition;
import mainmodule.View.Menus.Menu;
import mainmodule.View.Menus.MenuStack;
import mainmodule.View.Menus.ViewController;
import mainmodule.model.Avatar;
import mainmodule.model.Game;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.pluginA.Enums.BulletTransitionFactory;

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
        anchorPane.setPrefSize(1280 + 680,720);
        anchorPane.setId("mainPageAnchorPane");
    }

    private void initializeMenuStack() {
        MenuStack.getInstance().pushMenu(new Menu(anchorPane, new ViewController() {}));
        MenuStack.getInstance().setCurrentGame(new Game(new ProgressBar()));
    }

}
