package mainmodule.AvatarTests.AvatarMovingTest;

import javafx.application.Platform;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import mainmodule.BossBirdBulletsTest.BossBirdBulletStageTest;
import mainmodule.View.AvatarTransitions.AvatarTransition;
import mainmodule.model.Avatar;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.pluginA.BulletFactories.HouseBossBirdBulletFactories.HouseBossBirdBulletFactoryCreator;
import mainmodule.model.pluginA.Enums.AvatarStates;
import mainmodule.model.pluginA.util.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import javax.crypto.spec.PSource;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

public class AvatarMovingTests extends ApplicationTest {
    AnchorPane anchorPane;

    private double primaryX;
    private double primaryY;

    @BeforeEach
    public void setUp() throws Exception {
        ApplicationTest.launch(BossBirdBulletStageTest.class);
        anchorPane = find("#mainPageAnchorPane");
        AvatarTransition.getInstance().play();
        primaryX = Avatar.getInstance().getX();
        primaryY = Avatar.getInstance().getY();
    }


    @Test
    public void move_Avatar_To_Down() throws InterruptedException {
        clickMovingKey(KeyCode.DOWN);
        Assertions.assertNotEquals(0, primaryY - Avatar.getInstance().getY(), 0.001);
        Assertions.assertTrue((primaryY - Avatar.getInstance().getY()) < 0);
        releaseMovingKey(KeyCode.DOWN);
    }

    @Test
    public void move_Avatar_To_Up() throws InterruptedException {
        clickMovingKey(KeyCode.UP);
        Assertions.assertNotEquals(0, primaryY - Avatar.getInstance().getY(), 0.001);
        Assertions.assertTrue((primaryY - Avatar.getInstance().getY()) > 0);
        releaseMovingKey(KeyCode.UP);
    }

    @Test
    public void move_Avatar_To_Right() throws InterruptedException {
        clickMovingKey(KeyCode.RIGHT);
        Assertions.assertNotEquals(0, primaryX - Avatar.getInstance().getX(), 0.001);
        Assertions.assertTrue((primaryX - Avatar.getInstance().getX()) < 0);
        releaseMovingKey(KeyCode.RIGHT);
    }

    @Test
    public void move_Avatar_To_Left() throws InterruptedException {
        clickMovingKey(KeyCode.LEFT);
        Assertions.assertNotEquals(0, primaryX - Avatar.getInstance().getX(), 0.001);
        Assertions.assertTrue((primaryX - Avatar.getInstance().getX()) > 0);
        releaseMovingKey(KeyCode.LEFT);
    }


    private void releaseMovingKey(KeyCode keyCode) {
        Avatar.getInstance().getMoveKeyCodes().put(keyCode, false);
    }

    private void clickMovingKey(KeyCode keyCode) throws InterruptedException, NullPointerException {
        Avatar.getInstance().getMoveKeyCodes().put(keyCode, true);
        Thread.sleep(40);
    }


    @AfterEach
    public void afterEachTest() throws TimeoutException, InterruptedException {
        AvatarTransition.getInstance().stop();
        FxToolkit.cleanupStages();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    public <T extends Node> T find(String query) {
        return (T) lookup(query).queryAll().iterator().next();
    }

    private void applyChangeOnJavaFXApplication(Runnable a) {
        Platform.runLater(a);
    }


    private EventTarget getEventTarget(Scene scene) {
        return (EventTarget) (scene.getFocusOwner() != null ? scene.getFocusOwner() : scene);
    }
}
