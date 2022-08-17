package mainmodule.poultryTests;

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
public class PoultryBulletStageTest extends Application implements BulletTransitionFactory {
    AnchorPane anchorPane;

    public void start(Stage stage) throws Exception {
        initializeStageComponents();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    private void initializeStageComponents() {
        anchorPane = initializeMainPageAnchorPage();
        initializeMenuStack(anchorPane);
    }

    private AnchorPane initializeMainPageAnchorPage() {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(1280, 720);
        anchorPane.setId("mainAnchorPane");
        return anchorPane;
    }


    private void initializeMenuStack(AnchorPane anchorPane) {
        MenuStack.getInstance().pushMenu(new Menu(anchorPane, new ViewController() {
        }));
        MenuStack.getInstance().setCurrentGame(new Game(new ProgressBar(), BossBirdStack.getFullBossBirdStack()));
    }
}
