package mainmodule;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import mainmodule.View.AvatarTransitions.AvatarTransition;
import mainmodule.model.Avatar;
import mainmodule.model.Bullet;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.pluginA.BulletFactories.PoultryBirdBulletsFactories.PoultryBulletStageTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.List;

public class PoultryTestCollisionWithEnemy extends ApplicationTest {

    AnchorPane anchorPane;
    Bullet poultryBullet;
    @BeforeEach
    public void setUp() throws Exception {
        org.testfx.framework.junit.ApplicationTest.launch(PoultryBulletStageTest.class);
        anchorPane = find("#mainAnchorPane");
        clearAnchorPaneFromAvatarAndBossBird(anchorPane);
        poultryBullet = (Bullet) anchorPane.getChildren().get(0);
        System.out.println(anchorPane.getChildren().size());
    }

    @Test
    public void poultry_hit_with_Avatar() throws InterruptedException {
        Platform.runLater(() -> {
            Avatar avatar = new Avatar(300, 0, 109.0, 95.0);
            Avatar.setInstance(avatar);
            anchorPane.getChildren().add(avatar);
            poultryBullet.setEnemies(List.of(Avatar.getInstance()));
            AvatarTransition.getInstance().play();
        });
        Thread.sleep(4000);
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> anchorPane.getChildren().get(1));
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
    public <T extends Node> T find(String query){
        return (T) lookup(query).queryAll().iterator().next();
    }

}
