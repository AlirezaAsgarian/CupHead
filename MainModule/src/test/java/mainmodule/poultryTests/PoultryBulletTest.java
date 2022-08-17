package mainmodule.poultryTests;

import BossBirdsTypeA.BulletFactories.PoultryBirdBulletsFactories.PoultryBulletFactoryCreator;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.AvatarBulletFactories.AvatarBulletFactory;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.SetConstants;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ModuleAbstractClasses.ModuleAbstractClasses.Enums.BulletTransitionFactory;
import ModuleAbstractClasses.ModuleAbstractClasses.Transitions.AvatarTransitions.AvatarTransitions.AvatarTransition;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.Avatar;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Bullet;
import ModuleAbstractClasses.ModuleAbstractClasses.Managers.TransitionManager;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.BossBird.BossBird;

import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.Location;
import org.junit.jupiter.api.*;
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

public class PoultryBulletTest extends ApplicationTest implements BulletTransitionFactory {
    static Logger logger = LoggerFactory.getLogger(PoultryBulletTest.class);
    AnchorPane anchorPane;
    Bullet poultryBullet;

    private static Stream<Arguments> yellowAndPurplePoultryArguments() {
        return Stream.of(Arguments.of(1), Arguments.of(2));
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

    @BeforeEach
    public void setUp() throws Exception {
        ApplicationTest.launch(PoultryBulletStageTest.class);
    }

    @ParameterizedTest
    @MethodSource("yellowAndPurplePoultryArguments")
    public void do_Not_kill_poultry_with_one_Avatar_bullet(int selectionNumber) throws InterruptedException {
        initializePoultryBullet(selectionNumber);
        shootNewAvatarBulletToPoultry(new AvatarBulletFactory() {
        }, 360);
        Thread.sleep(3000);
        // assert if poultry exists or not
        Assertions.assertNotNull(anchorPane.getChildren().get(0));
        Platform.runLater(() -> anchorPane.getChildren().remove(poultryBullet));
    }

    private void initializePoultryBullet(int selectionNumber) {
        applyChangeOnJavaFXApplication(() -> createPoultryTransitionAndPlayIt(selectionNumber, 0));
        anchorPane = find("#mainAnchorPane");
    }

    private void createPoultryTransitionAndPlayIt(int selectionNumber, int... positions) {
        PoultryBulletFactoryCreator poultryBulletFactory = new PoultryBulletFactoryCreator();
        Arrays.stream(positions).forEach((i) -> {
                    SetConstants.setBossBirdPoultryY(i * 100);
                    createBulletTransition(poultryBulletFactory.getNewBossBirdBulletFactory(selectionNumber), new Location(600, SetConstants.BOSS_BIRD_POULTRY_Y)).play();
                }
        );
    }

    private void applyChangeOnJavaFXApplication(Runnable a) {
        Platform.runLater(a);
    }

    public <T extends Node> T find(String query) {
        return (T) lookup(query).queryAll().iterator().next();
    }

    private void shootNewAvatarBulletToPoultry(AvatarBulletFactory avatarBulletFactory, int... xLocations) {
        for (int x :
                xLocations) {
            applyChangeOnJavaFXApplication(() -> createBulletTransition(avatarBulletFactory, new Location(x, SetConstants.BOSS_BIRD_POULTRY_Y)).play());
        }
    }

    @ParameterizedTest
    @MethodSource("yellowAndPurplePoultryArguments")
    public void kill_poultry_with_two_Avatar_bullet(int selectionNumber) throws InterruptedException {
        initializePoultryBullet(selectionNumber);
        AvatarBulletFactory avatarBulletFactory = new AvatarBulletFactory() {
        };
        shootNewAvatarBulletToPoultry(avatarBulletFactory, 360, 0);
        Thread.sleep(2000);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> anchorPane.getChildren().get(0));
    }

    @ParameterizedTest
    @MethodSource("yellowAndPurplePoultryArguments")
    public void poultry_hit_with_Avatar(int selectionNumber) throws InterruptedException {
        initializePoultryBullet(selectionNumber);
        applyChangeOnJavaFXApplication(this::initializeAvatar);
        Thread.sleep(2000);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> anchorPane.getChildren().get(1));
        applyChangeOnJavaFXApplication(() -> AvatarTransition.getInstance().stop());
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

    @ParameterizedTest
    @MethodSource("yellowAndPurplePoultryArguments")
    public void seePoultryPositions(int selectionNumber) throws InterruptedException {
        initializeAllPoultryBullet(selectionNumber);
        Thread.sleep(1000);
    }

    private void initializeAllPoultryBullet(int selectionNumber) {
        applyChangeOnJavaFXApplication(() -> createPoultryTransitionAndPlayIt(selectionNumber, 0, 1, 2, 3, 4, 5, 6));
        anchorPane = find("#mainAnchorPane");
    }

    @AfterEach
    public void afterEachTest() throws TimeoutException {
        TransitionManager.stopTransitions();
        FxToolkit.cleanupStages();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    public void clearAnchorPaneFromAvatarAndBossBird(AnchorPane anchorPane) {
        for (Node node :
                anchorPane.getChildren()) {
            if (node instanceof Avatar || node instanceof BossBird) {
                System.out.println("hello");
                anchorPane.getChildren().remove(node);
            }
        }
    }

}

