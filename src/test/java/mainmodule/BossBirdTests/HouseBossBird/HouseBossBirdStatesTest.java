package mainmodule.BossBirdTests.HouseBossBird;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import mainmodule.model.ModuleAbstractClasses.TransitionManager;
import mainmodule.model.pluginA.BossBirds.BossBird;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;

public class HouseBossBirdStatesTest extends ApplicationTest {


    BossBird houseBossBird;
    AnchorPane anchorPane;
    @BeforeEach
    public void setUpTest() throws Exception {
        ApplicationTest.launch(HouseBossBirdStateStageTest.class);
        houseBossBird = BossBird.getInstance();
        anchorPane = find("#mainPageAnchorPane");
    }

    @Test
    public void death_State() throws InterruptedException {
        houseBossBird.decreaseHealth(70);
        Thread.sleep(6000);
    }
    @AfterEach
    public void cleanStageAfterEndingTest(){
        TransitionManager.stopTransitions();
    }






   public <T extends Node> T find(String query){
        return (T) lookup(query).queryAll().iterator().next();
   }

}
