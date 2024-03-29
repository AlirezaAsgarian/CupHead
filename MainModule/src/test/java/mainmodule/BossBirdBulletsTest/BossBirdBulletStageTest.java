package mainmodule.BossBirdBulletsTest;

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
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.Avatar;
import ModuleAbstractClasses.ModuleAbstractClasses.Game;

public class BossBirdBulletStageTest extends Application implements BulletTransitionFactory {
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
        anchorPane.setPrefSize(1280, 720);
        anchorPane.setId("mainPageAnchorPane");
    }

    private void initializeAvatar() {
        Avatar.getInstance().setX(640.0 - 54.5);
        Avatar.getInstance().setY(360.0 - 47.5);
        Avatar.getInstance().setId("avatar");
        anchorPane.getChildren().add(Avatar.getInstance());
    }

    private void initializeMenuStack() {
        MenuStack.getInstance().pushMenu(new Menu(anchorPane, new ViewController() {
        }));
        MenuStack.getInstance().setCurrentGame(new Game(new ProgressBar(), BossBirdStack.getFullBossBirdStack()));
    }

}
