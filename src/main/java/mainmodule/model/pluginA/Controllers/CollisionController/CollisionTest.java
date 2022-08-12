package mainmodule.model.pluginA.Controllers.CollisionController;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.TimeoutException;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CollisionTest extends ApplicationTest {

    static AnchorPane anchorPane ;
    static ImageView avatarIV;
    static ImageView houseBossBirdIV;

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }



    @BeforeAll
    public void setUpStageBeforeAllTest() throws Exception {

    }
    @BeforeEach
    public void launchStage()  {
        try {
            ApplicationTest.launch(CollisionTestStage.class, " --add-exports=javafx.graphics/com.sun.javafx.application=ALL-UNNAMED");
            anchorPane = find("#mainAnchorPane");
            avatarIV = find("#avatarIV");
            houseBossBirdIV = find("#houseBossBirdIV");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void twoImagesHaveIntersectsButTheyImagesDonNotHaveCollision(){
        launchStage();
        Assertions.assertTrue(avatarIV.intersects((houseBossBirdIV.getBoundsInParent())));
        Assertions.assertFalse(CollisionController.haveCollision(avatarIV.getImage(),houseBossBirdIV.getImage(),avatarIV.getBoundsInParent(),houseBossBirdIV.getBoundsInParent()));
    }
    @Test
    public void twoImagesHaveIntersectsAndTheyImagesHaveCollision(){
        setAvatarIVAtTestingPositionOfAnchorPane(anchorPane, avatarIV,anchorPane.getPrefWidth() / 2,anchorPane.getPrefHeight() / 2);
        Assertions.assertTrue(avatarIV.intersects((houseBossBirdIV.getBoundsInParent())));
        Assertions.assertTrue(CollisionController.haveCollision(avatarIV.getImage(),houseBossBirdIV.getImage(),avatarIV.getBoundsInParent(),houseBossBirdIV.getBoundsInParent()));
    }
    @Test
    public void twoImagesHaveIntersectsAndTheyImagesHaveCollision2(){
        setAvatarIVAtTestingPositionOfAnchorPane(anchorPane, avatarIV,(anchorPane.getPrefWidth() / 2)  - 300 ,anchorPane.getPrefHeight() / 2 + 100);
        Assertions.assertTrue(avatarIV.intersects((houseBossBirdIV.getBoundsInParent())));
        Assertions.assertTrue(CollisionController.haveCollision(avatarIV.getImage(),houseBossBirdIV.getImage(),avatarIV.getBoundsInParent(),houseBossBirdIV.getBoundsInParent()));
    }

    private void setAvatarIVAtTestingPositionOfAnchorPane(AnchorPane anchorPane, ImageView avatarIV, double x, double y) {
        avatarIV.setX(x);
        avatarIV.setY(y);
        Platform.runLater(() -> avatarIV.toFront());
    }

    @AfterEach
    public void afterEachTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    private void waitForWatchingTestCase(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }





    public <T extends Node> T find(String query){
        return (T) lookup(query).queryAll().iterator().next();
    }




}
