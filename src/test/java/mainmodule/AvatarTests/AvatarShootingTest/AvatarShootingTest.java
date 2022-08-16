package mainmodule.AvatarTests.AvatarShootingTest;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import mainmodule.View.AvatarTransitions.AvatarTransition;
import mainmodule.model.ModuleAbstractClasses.Avatar;
import mainmodule.model.ModuleAbstractClasses.TransitionManager;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.pluginA.Enums.AvatarStates;
import org.junit.jupiter.api.*;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Map;
import java.util.concurrent.TimeoutException;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AvatarShootingTest extends ApplicationTest {
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


    @Test
    @Order(1)
    public void shoot_Right_Normal_To_Right() throws InterruptedException {
        setAvatarAndEnemyAtTestPosition(1280,120,Avatar.getInstance().getX(),Avatar.getInstance().getY());
        Avatar.getInstance().getShootingKeyCodes().put(KeyCode.D,true);
        Thread.sleep(3000);
    }

    @Test
    @Order(2)
    public void shoot_Left_Normal_To_Left() throws InterruptedException{
        setAvatarAndEnemyAtTestPosition(100,enemy.getY(),1280,Avatar.getInstance().getY());
        Avatar.getInstance().getShootingKeyCodes().put(KeyCode.A,true);
        Thread.sleep(4000);
    }
    @Test
    @Order(3)
    public void shoot_Left_Normal_To_UP() throws InterruptedException{
        setAvatarAndEnemyAtTestPosition(640,-100,640,630);
        Avatar.getInstance().getShootingKeyCodes().put(KeyCode.W,true);
        Thread.sleep(4000);
    }
    @Test
    @Order(4)
    public void shoot_Left_Normal_To_Down() throws InterruptedException{
        setAvatarAndEnemyAtTestPosition(enemy.getX(),580,780,0);
        Avatar.getInstance().getShootingKeyCodes().put(KeyCode.S,true);
        Thread.sleep(4000);
    }
    @Test
    @Order(5)
    public void shoot_Avatar_Bomb_To_Down() throws InterruptedException{
        setAvatarAndEnemyAtTestPosition(enemy.getX(),580,780,0);
        Avatar.getInstance().changeAvatarStates(AvatarStates.NORMAL_BOMBING);
        Avatar.getInstance().getShootingKeyCodes().put(KeyCode.S,true);
        Thread.sleep(4000);
    }
    @AfterEach
    public void afterEachTest() throws TimeoutException, InterruptedException {
        TransitionManager.stopTransitions();
        resetAvatarShootingKeys();
        FxToolkit.cleanupStages();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    public void resetAvatarShootingKeys(){
        for (Map.Entry<KeyCode,Boolean> sh:
              Avatar.getInstance().getShootingKeyCodes().entrySet()){
            Avatar.getInstance().getShootingKeyCodes().put(sh.getKey(),false);
        }
    }
    private void setAvatarAndEnemyAtTestPosition(double enemyX,double enemyY,double avatarX,double avatarY) {
        enemy.setX(enemyX);
        enemy.setY(enemyY);
        Avatar.getInstance().setX(avatarX);
        Avatar.getInstance().setY(avatarY);
    }

    public <T extends Node> T find(String query) {
        return (T) lookup(query).queryAll().iterator().next();
    }

    private void applyChangeOnJavaFXApplication(Runnable a) {
        Platform.runLater(a);
    }
}
