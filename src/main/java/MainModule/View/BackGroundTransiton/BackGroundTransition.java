package MainModule.View.BackGroundTransiton;

import MainModule.Enums.BackGround;
import MainModule.Util.Constants;
import MainModule.View.GameSceneView;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

import static javafx.scene.layout.BackgroundRepeat.*;

public class BackGroundTransition extends Transition {
    Rectangle imageAhead;
    Rectangle imageBack;

    public BackGroundTransition(BackGround backGround) {
        setCycleDuration(Duration.millis(2000));
        setCycleCount(-1);
        imageAhead = backGround.getRectangleAhead();
        imageBack = backGround.getRectangleBack();
    }

    @Override
    protected void interpolate(double v) {
        imageAhead.setX(imageAhead.getX() + Constants.BACKGROUND_IMAGES_SPEED);
        imageBack.setX(imageBack.getX() + Constants.BACKGROUND_IMAGES_SPEED);
        if(imageAhead.getX() >= Constants.Max_Width){
            imageAhead.setX(imageBack.getX() - imageBack.getWidth());
        }
        if(imageBack.getX() >= Constants.Max_Width){
            imageBack.setX(imageAhead.getX() - imageAhead.getWidth());
        }
    }

}
