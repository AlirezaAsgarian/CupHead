package mainmodule.AvatarTests.AvatarMovingTest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainmodule.View.AvatarTransitions.AvatarTransition;
import mainmodule.View.Menus.Menu;
import mainmodule.View.Menus.MenuStack;
import mainmodule.View.Menus.ViewController;
import mainmodule.model.ModuleAbstractClasses.Avatar;
import mainmodule.model.Game;
import mainmodule.model.pluginA.Enums.BulletTransitionFactory;

public class AvatarMovingStageTest extends Application implements BulletTransitionFactory {
    AnchorPane anchorPane;
    Avatar avatar;
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
        initializeAvatar();
        AvatarTransition.getInstance().play();
    }

    private void initializeMainPageAnchorPane() {
        anchorPane = new AnchorPane();
        anchorPane.setPrefSize(1280,720);
        anchorPane.setId("mainPageAnchorPane");
    }

    private void initializeAvatar() {
        Avatar.getInstance().setX(640.0 - 54.5);
        Avatar.getInstance().setY(360.0 - 47.5);
        Avatar.getInstance().setKeyOnPressedAndReleasedAvatar();
        Avatar.getInstance().setId("avatar");
        anchorPane.getChildren().add(Avatar.getInstance());
    }
    private void initializeMenuStack() {
        MenuStack.getInstance().pushMenu(new Menu(anchorPane, new ViewController() {}));
        MenuStack.getInstance().setCurrentGame(new Game(new ProgressBar()));
    }

}
