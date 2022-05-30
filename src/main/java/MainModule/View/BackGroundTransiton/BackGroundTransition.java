package MainModule.View.BackGroundTransiton;

import MainModule.Enums.BackGround;
import MainModule.View.GameSceneView;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.ArrayList;

import static javafx.scene.layout.BackgroundRepeat.*;

public class BackGroundTransition extends Transition {
    BackGround backGround;

    public BackGroundTransition(BackGround backGround) {
        setCycleDuration(Duration.millis(2000));
        setCycleCount(-1);
        this.backGround = backGround;
              GameSceneView.anchorPane.setBackground(backGround.getBackgrounds().get(0));
    }

    @Override
    protected void interpolate(double v) {

         int frame = (int) Math.ceil(v * backGround.getBackgrounds().size());
        if(frame == 0 ) frame = 1;
        GameSceneView.anchorPane.setBackground(backGround.getBackgrounds().get(frame - 1));
    }

}
