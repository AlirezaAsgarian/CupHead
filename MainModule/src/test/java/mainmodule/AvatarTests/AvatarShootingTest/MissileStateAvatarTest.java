package mainmodule.AvatarTests.AvatarShootingTest;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import ModuleAbstractClasses.ModuleAbstractClasses.Transitions.AvatarTransitions.AvatarTransitions.AvatarTransition;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.Avatar;
import ModuleAbstractClasses.ModuleAbstractClasses.Managers.BossBirdManger;
import ModuleAbstractClasses.ModuleAbstractClasses.Managers.TransitionManager;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.BossBird.BossBird;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.AvatarEnums.AvatarStates;
import org.junit.jupiter.api.*;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Map;
import java.util.concurrent.TimeoutException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MissileStateAvatarTest extends ApplicationTest {
    //todo : fix
    AnchorPane anchorPane;
    BossBird enemy;

    private double primaryX;
    private double primaryY;

    @BeforeEach
    public void setUp() throws Exception {
        ApplicationTest.launch(AvatarShootingStageTest.class);
        anchorPane = find("#mainPageAnchorPane");
        AvatarTransition.getInstance().play();
        primaryX = Avatar.getInstance().getX();
        primaryY = Avatar.getInstance().getY();
        initializeBossBirdEnemy();
    }

    private void initializeBossBirdEnemy() {
        enemy = find("#bossBird");
        enemy.decreaseHealth(-5000);
    }

    public <T extends Node> T find(String query) {
        return (T) lookup(query).queryAll().iterator().next();
    }

    @Test
    @Order(1)
    public void shoot_Missile_Shoot_And_Hit_Enemy() throws InterruptedException {
        setAvatarAndEnemyAtTestPosition(1280 - 400, 120, Avatar.getInstance().getX() - 200, Avatar.getInstance().getY());
        Avatar.getInstance().changeAvatarStates(AvatarStates.MISSILE);
        Thread.sleep(10000);
    }

    private void setAvatarAndEnemyAtTestPosition(double enemyX, double enemyY, double avatarX, double avatarY) {
        enemy.setX(enemyX);
        enemy.setY(enemyY);
        Avatar.getInstance().setX(avatarX);
        Avatar.getInstance().setY(avatarY);
    }

    @Test
    @Order(2)
    public void shoot_Missile_Shoot_And_Do_Not_Hit_Enemy() throws InterruptedException {
        setAvatarAndEnemyAtTestPosition(1280, 120, Avatar.getInstance().getX() - 200, Avatar.getInstance().getY());
        applyChangeOnJavaFXApplication(() -> {
            anchorPane.getChildren().remove(BossBird.getInstance());
            BossBirdManger.getInstance().removeBossBirdTransitionByBossBird(enemy);
        });
        Avatar.getInstance().changeAvatarStates(AvatarStates.MISSILE);
        Thread.sleep(10000);
    }

    private void applyChangeOnJavaFXApplication(Runnable a) {
        Platform.runLater(a);
    }

    @AfterEach
    public void afterEachTest() throws TimeoutException, InterruptedException {
        TransitionManager.stopTransitions();
        resetAvatarShootingKeys();
        FxToolkit.cleanupStages();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    public void resetAvatarShootingKeys() {
        for (Map.Entry<KeyCode, Boolean> sh :
                Avatar.getInstance().getShootingKeyCodes().entrySet()) {
            Avatar.getInstance().getShootingKeyCodes().put(sh.getKey(), false);
        }
    }
}

