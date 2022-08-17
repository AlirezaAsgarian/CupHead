package mainmodule.BossBirdBulletsTest.MiniBossBirdSpecialBullet;

import BossBirdsTypeA.BossBirdStack.BossBirdStack;
import ModuleAbstractClasses.ModuleAbstractClasses.Enums.BulletTransitionFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.Menu;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.MenuStack;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.ViewController;
import ModuleAbstractClasses.ModuleAbstractClasses.Game;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MiniBossBirdSpecialBulletStageTest extends Application implements BulletTransitionFactory {
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
    }

    private void initializeMainPageAnchorPane() {
        anchorPane = new AnchorPane();
        anchorPane.setPrefSize(1280, 720);
        anchorPane.setId("mainPageAnchorPane");
    }


    private void initializeMenuStack() {
        MenuStack.getInstance().pushMenu(new Menu(anchorPane, new ViewController() {
        }));
        MenuStack.getInstance().setCurrentGame(new Game(new ProgressBar(), BossBirdStack.getFullBossBirdStack()));
    }

}
