package mainmodule.model.pluginA.Controllers.CollisionController.test;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import mainmodule.View.GameSceneView;
import org.junit.jupiter.api.*;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.TimeoutException;


public class CollisionTest extends ApplicationTest {


    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

    @BeforeEach
    public void setUpStageBeforeEachTest() throws Exception {
        ApplicationTest.launch(TestStage.class," --add-exports=javafx.graphics/com.sun.javafx.application=ALL-UNNAMED");
    }
    @Test
    public void test(){

    }
    @AfterEach
    public void afterEachTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }



    public <T extends Node> T find(String query){
        return (T) lookup(query).queryAll().iterator().next();
    }




}
