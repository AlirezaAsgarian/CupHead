package mainmodule.BossBirdBulletsTest.MiniBossBirdSpecialBullet;

import BossBirdsTypeA.BossBirds.BossBirdEnums;
import BossBirdsTypeA.BossBirds.MiniBossBird;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import mainmodule.BossBirdBulletsTest.BossBirdBulletStageTest;
import mainmodule.ComponentFinder;
import mainmodule.JavaFXThreadModifier;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.Avatar;
import ModuleAbstractClasses.ModuleAbstractClasses.Managers.TransitionManager;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.BossBird.BossBird;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;

public class MiniBossBirdSpecialBulletTest extends ApplicationTest implements ComponentFinder, JavaFXThreadModifier {


    AnchorPane anchorPane;

    @BeforeEach
    public void setUp() throws Exception {
        ApplicationTest.launch(BossBirdBulletStageTest.class);
        anchorPane = find("#mainPageAnchorPane");
        applyChangeOnJavaFXApplication(() -> {
            anchorPane.getChildren().remove(Avatar.getInstance());
            Avatar.getInstance().setX(0);
            Avatar.getInstance().setY(0);
        });
    }

    @Override
    public <T extends Node> T find(String query) {
        return (T) lookup(query).queryAll().iterator().next();
    }

    @Test
    public void test() throws InterruptedException {
        MiniBossBird miniBossBird = (MiniBossBird) BossBirdEnums.SECOND_BOSS_BIRD.createNewBossBird();
        miniBossBird.decreaseHealth(-200);
        BossBird.setInstance(miniBossBird);
        applyChangeOnJavaFXApplication(miniBossBird::initializeNewBossBirdAndItsTransitions);
        Thread.sleep(1000);
    }

    @AfterEach
    public void cleaningPageAfterTest() {
        TransitionManager.stopTransitions();
    }


}
