package mainmodule.poultryTests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainmodule.View.Menus.Menu;
import mainmodule.View.Menus.MenuStack;
import mainmodule.View.Menus.ViewController;
import mainmodule.model.Bullet;
import mainmodule.model.BulletFactory;
import mainmodule.model.Game;
import mainmodule.model.pluginA.BulletFactories.PoultryBirdBulletsFactories.PoultryBulletFactoryCreator;
import mainmodule.model.pluginA.Enums.BulletTransitionFactory;
import mainmodule.model.pluginA.util.Constants;
import mainmodule.model.pluginA.util.SetConstants;
import mainmodule.util.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import java.util.concurrent.TimeoutException;

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
        anchorPane.setPrefSize(1280,720);
        anchorPane.setId("mainAnchorPane");
        return anchorPane;
    }



    private void initializeMenuStack(AnchorPane anchorPane) {
        MenuStack.getInstance().pushMenu(new Menu(anchorPane, new ViewController() {}));
        MenuStack.getInstance().setCurrentGame(new Game(new ProgressBar()));
    }
}
