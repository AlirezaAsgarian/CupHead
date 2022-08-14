package mainmodule.BossBirdBulletsTest.MiniBossBirdSpecialBullet;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import mainmodule.BossBirdBulletsTest.BossBirdBulletStageTest;
import mainmodule.ComponentFinder;
import mainmodule.JavaFXThreadModifier;
import mainmodule.model.Avatar;
import mainmodule.model.TransitionManager;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.pluginA.BossBirds.BossBirdEnums;
import mainmodule.model.pluginA.BossBirds.MiniBossBird;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.service.finder.NodeFinder;

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

    @Test
    public void test() throws InterruptedException {
        MiniBossBird miniBossBird = (MiniBossBird) BossBirdEnums.SECOND_BOSS_BIRD.createNewBossBird();
        miniBossBird.decreaseHealth(-200);
        BossBird.setInstance(miniBossBird);
        applyChangeOnJavaFXApplication(miniBossBird::initializeNewBossBirdAndItsTransitions);
        Thread.sleep(1000);
    }
    @AfterEach
    public void cleaningPageAfterTest(){
        TransitionManager.stopTransitions();
    }


    @Override
    public <T extends Node> T find(String query) {
        return (T) lookup(query).queryAll().iterator().next();
    }


}
