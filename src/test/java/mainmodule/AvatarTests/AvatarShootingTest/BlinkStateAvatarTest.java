package mainmodule.AvatarTests.AvatarShootingTest;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainmodule.View.AvatarTransitions.AvatarTransition;
import mainmodule.model.Avatar;
import mainmodule.model.Bullet;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.pluginA.BulletFactories.AvatarBulletFactories.AvatarBulletFactory;
import mainmodule.model.pluginA.BulletFactories.PoultryBirdBulletsFactories.PoultryBulletFactoryCreator;
import mainmodule.model.pluginA.Enums.AvatarStates;
import mainmodule.model.pluginA.Enums.BulletTransitionFactory;
import mainmodule.model.pluginA.util.SetConstants;
import mainmodule.poultryTests.PoultryBulletStageTest;
import mainmodule.poultryTests.PoultryBulletTest;
import mainmodule.util.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BlinkStateAvatarTest extends ApplicationTest implements BulletTransitionFactory {
    static Logger logger = (Logger) LoggerFactory.getLogger(PoultryBulletTest.class);

    AnchorPane anchorPane;
    Bullet poultryBullet;
    @BeforeEach
    public void setUp() throws Exception {
        ApplicationTest.launch(PoultryBulletStageTest.class);
    }




    @Test
    public void poultry_hit_with_Avatar() throws InterruptedException {
        initializePoultryBullet(1);
        applyChangeOnJavaFXApplication(this::initializeAvatar);
        Thread.sleep(2000);
        Assertions.assertDoesNotThrow(() -> anchorPane.getChildren().get(1));
        applyChangeOnJavaFXApplication(() -> AvatarTransition.getInstance().stop());
    }

    private  void initializePoultryBullet(int selectionNumber) {
        applyChangeOnJavaFXApplication(() -> createPoultryTransitionAndPlayIt(selectionNumber,0));
        anchorPane = find("#mainAnchorPane");
    }
    private void createPoultryTransitionAndPlayIt(int selectionNumber,int... positions) {
        PoultryBulletFactoryCreator poultryBulletFactory = new PoultryBulletFactoryCreator();
        Arrays.stream(positions).forEach((i) -> {
                    SetConstants.setBossBirdPoultryY(i * 100);
                    createBulletTransition(poultryBulletFactory.getNewBossBirdBulletFactory(selectionNumber),new Location(600 ,SetConstants.BOSS_BIRD_POULTRY_Y)).play();
                }
        );
    }


    private void initializeAvatar() {
        Avatar avatar = new Avatar(330, 0, 109.0, 95.0);
        Avatar.setInstance(avatar);
        Avatar.getInstance().changeAvatarStates(AvatarStates.BLINK);
        anchorPane.getChildren().add(avatar);
        setAvatarAsEnemyForPoultryBullet();
        AvatarTransition.getInstance().play();
    }

    private void setAvatarAsEnemyForPoultryBullet() {
        poultryBullet = (Bullet) anchorPane.getChildren().get(0);
        poultryBullet.setEnemies(List.of(Avatar.getInstance()));
    }


    @AfterEach
    public void afterEachTest() throws TimeoutException {
        FxToolkit.cleanupStages();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    private void applyChangeOnJavaFXApplication(Runnable a){
        Platform.runLater(a);
    }
    public <T extends Node> T find(String query){
        return (T) lookup(query).queryAll().iterator().next();
    }
    public void clearAnchorPaneFromAvatarAndBossBird(AnchorPane anchorPane){
        for (Node node:
                anchorPane.getChildren()) {
            if(node instanceof Avatar || node instanceof BossBird){
                System.out.println("hello");
                anchorPane.getChildren().remove(node);
            }
        }
    }

}
