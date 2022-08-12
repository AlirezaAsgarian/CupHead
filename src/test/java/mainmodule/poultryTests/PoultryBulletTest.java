package mainmodule.poultryTests;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainmodule.Main;
import mainmodule.View.AvatarTransitions.AvatarTransition;
import mainmodule.model.Avatar;
import mainmodule.model.Bullet;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.pluginA.BulletFactories.AvatarBulletFactories.AvatarBulletFactory;
import mainmodule.model.pluginA.Enums.BulletTransitionFactory;
import mainmodule.model.pluginA.util.SetConstants;
import mainmodule.util.Location;
import org.junit.jupiter.api.*;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class PoultryBulletTest extends ApplicationTest implements BulletTransitionFactory {
    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }
    AnchorPane anchorPane;
    Bullet poultryBullet;
    @BeforeEach
    public void setUp() throws Exception {
        ApplicationTest.launch(PoultryBulletStageTest.class);
        anchorPane = find("#mainAnchorPane");
        poultryBullet = (Bullet) anchorPane.getChildren().get(0);
    }

    @org.junit.jupiter.api.Test
    public void do_Not_kill_poultry_with_one_Avatar_bullet() throws InterruptedException {
        shootNewAvatarBulletToPoultry(new AvatarBulletFactory() {}, 360);
        Thread.sleep(6000);
        // assert if poultry exists or not
        Assertions.assertNotNull(anchorPane.getChildren().get(0));
        Platform.runLater(() -> anchorPane.getChildren().remove(poultryBullet));
    }

    private void shootNewAvatarBulletToPoultry(AvatarBulletFactory avatarBulletFactory,int... xLocations) {
        for (int x:
             xLocations) {
             applyChangeOnJavaFXApplication(() -> createBulletTransition(avatarBulletFactory, new Location(x, SetConstants.BOSS_BIRD_POULTRY_Y)).play());
        }
    }

    @org.junit.jupiter.api.Test
    public void kill_poultry_with_two_Avatar_bullet() throws InterruptedException {
        AvatarBulletFactory avatarBulletFactory = new AvatarBulletFactory() {};
        shootNewAvatarBulletToPoultry(avatarBulletFactory,360,0);
        Thread.sleep(5000);
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> anchorPane.getChildren().get(0));
    }
    @org.junit.jupiter.api.Test
    public void poultry_hit_with_Avatar() throws InterruptedException {
        applyChangeOnJavaFXApplication(this::initializeAvatar);
        Thread.sleep(4000);
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> anchorPane.getChildren().get(1));
        applyChangeOnJavaFXApplication(() -> AvatarTransition.getInstance().stop());
    }


    private void initializeAvatar() {
        Avatar avatar = new Avatar(300, 0, 109.0, 95.0);
        Avatar.setInstance(avatar);
        anchorPane.getChildren().add(avatar);
        poultryBullet.setEnemies(List.of(Avatar.getInstance()));
        AvatarTransition.getInstance().play();
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

