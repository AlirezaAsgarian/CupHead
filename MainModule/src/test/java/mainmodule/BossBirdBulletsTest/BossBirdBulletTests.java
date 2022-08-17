package mainmodule.BossBirdBulletsTest;

import BossBirdsTypeA.BulletFactories.BedBossBirdBulletFactories.BedBossBirdBulletFactoryCreator;
import BossBirdsTypeA.BulletFactories.DoctorBossBirdBulletFactories.DoctorBossBirdBulletFactoryCreator;
import BossBirdsTypeA.BulletFactories.HouseBossBirdBulletFactories.HouseBossBirdBulletFactoryCreator;
import BossBirdsTypeA.BulletFactories.MiniBossBirdBulletFactories.MiniBossBirdBulletFactoryCreator;
import ModuleAbstractClasses.ModuleAbstractClasses.Enums.BulletTransitionFactory;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.Location;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.Avatar;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.BulletFactory;

import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

public class BossBirdBulletTests extends ApplicationTest implements BulletTransitionFactory {

    AnchorPane anchorPane;

    private static Stream<Arguments> bossBirdBulletFactories() {
        return Stream.of(Arguments.of(new HouseBossBirdBulletFactoryCreator().getNewBossBirdBulletFactory(1), 1280, 360),
                Arguments.of(new MiniBossBirdBulletFactoryCreator().getNewBossBirdBulletFactory(Constants.MINI_BOSS_BIRD_BIRD_BULLET_TO_LEFT_INDEX), 1280, 360),
                Arguments.of(new MiniBossBirdBulletFactoryCreator().getNewBossBirdBulletFactory(Constants.MINI_BOSS_BIRD_BIRD_BULLET_TO_RIGHT_INDEX), 0, 360),
                Arguments.of(new DoctorBossBirdBulletFactoryCreator().getNewBossBirdBulletFactory(0), 640, 720),
                Arguments.of(new DoctorBossBirdBulletFactoryCreator().getNewBossBirdBulletFactory(1), 640, 720),
                Arguments.of(new BedBossBirdBulletFactoryCreator().getNewBossBirdBulletFactory(0), 640, 720),
                Arguments.of(new BedBossBirdBulletFactoryCreator().getNewBossBirdBulletFactory(1), 640, 720),
                Arguments.of(new BedBossBirdBulletFactoryCreator().getNewBossBirdBulletFactory(2), 640, 720),
                Arguments.of(new BedBossBirdBulletFactoryCreator().getNewBossBirdBulletFactory(3), 640, 720));
    }

    @BeforeEach
    public void setUp() throws Exception {
        ApplicationTest.launch(BossBirdBulletStageTest.class);
        anchorPane = find("#mainPageAnchorPane");
    }

    public <T extends Node> T find(String query) {
        return (T) lookup(query).queryAll().iterator().next();
    }

    @ParameterizedTest
    @MethodSource("bossBirdBulletFactories")
    public void hit_Avatar_Bullets(BulletFactory bulletFactory, int x, int y) throws InterruptedException {
        applyChangeOnJavaFXApplication(() -> createBulletTransition(bulletFactory, new Location(x, y)).play());
        Thread.sleep(3000);
        Assertions.assertEquals(1, anchorPane.getChildren().size());
        Avatar.getInstance().decreaseHealth(-10);
    }

    private void applyChangeOnJavaFXApplication(Runnable a) {
        Platform.runLater(a);
    }

    @AfterEach
    public void afterEachTest() throws TimeoutException {
        FxToolkit.cleanupStages();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

}
