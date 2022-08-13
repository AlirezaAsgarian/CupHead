package mainmodule.poultryTests;

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
import mainmodule.model.pluginA.Enums.BulletTransitionFactory;
import mainmodule.model.pluginA.util.SetConstants;
import mainmodule.util.Location;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

public class PoultryBulletTest extends ApplicationTest implements BulletTransitionFactory {
    static Logger logger = (Logger) LoggerFactory.getLogger(PoultryBulletTest.class);
    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }
    AnchorPane anchorPane;
    Bullet poultryBullet;
    @BeforeEach
    public void setUp() throws Exception {
        ApplicationTest.launch(PoultryBulletStageTest.class);
    }

    @ParameterizedTest
    @MethodSource("yellowAndPurplePoultryArguments")
    public void do_Not_kill_poultry_with_one_Avatar_bullet(int selectionNumber) throws InterruptedException {
        initializePoultryBullet(selectionNumber);
        shootNewAvatarBulletToPoultry(new AvatarBulletFactory() {}, 360);
        Thread.sleep(3000);
        // assert if poultry exists or not
        Assertions.assertNotNull(anchorPane.getChildren().get(0));
        Platform.runLater(() -> anchorPane.getChildren().remove(poultryBullet));
    }

    private  void initializePoultryBullet(int selectionNumber) {
        applyChangeOnJavaFXApplication(() -> createPoultryTransitionAndPlayIt(selectionNumber));
        anchorPane = find("#mainAnchorPane");
    }
    private static Stream<Arguments> yellowAndPurplePoultryArguments(){
        return Stream.of(Arguments.of(1),Arguments.of(2));
    }

    private void shootNewAvatarBulletToPoultry(AvatarBulletFactory avatarBulletFactory,int... xLocations) {
        for (int x:
             xLocations) {
             applyChangeOnJavaFXApplication(() -> createBulletTransition(avatarBulletFactory, new Location(x, SetConstants.BOSS_BIRD_POULTRY_Y)).play());
        }
    }

    @ParameterizedTest
    @MethodSource("yellowAndPurplePoultryArguments")
    public void kill_poultry_with_two_Avatar_bullet(int selectionNumber) throws InterruptedException {
        initializePoultryBullet(selectionNumber);
        AvatarBulletFactory avatarBulletFactory = new AvatarBulletFactory() {};
        shootNewAvatarBulletToPoultry(avatarBulletFactory,360,0);
        Thread.sleep(2000);
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> anchorPane.getChildren().get(0));
    }
    @ParameterizedTest
    @MethodSource("yellowAndPurplePoultryArguments")
    public void poultry_hit_with_Avatar(int selectionNumber) throws InterruptedException {
        initializePoultryBullet(selectionNumber);
        applyChangeOnJavaFXApplication(this::initializeAvatar);
        Thread.sleep(2000);
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> anchorPane.getChildren().get(1));
        applyChangeOnJavaFXApplication(() -> AvatarTransition.getInstance().stop());
    }
    private void createPoultryTransitionAndPlayIt(int selectionNumber) {
        PoultryBulletFactoryCreator poultryBulletFactory = new PoultryBulletFactoryCreator();
        createBulletTransition(poultryBulletFactory.getNewBossBirdBulletFactory(selectionNumber),new Location(600 ,SetConstants.BOSS_BIRD_POULTRY_Y)).play();
    }


    private void initializeAvatar() {
        Avatar avatar = new Avatar(300, 0, 109.0, 95.0);
        Avatar.setInstance(avatar);
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

