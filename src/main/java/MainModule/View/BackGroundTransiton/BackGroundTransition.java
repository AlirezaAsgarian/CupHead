package MainModule.View.BackGroundTransiton;

import MainModule.Enums.BackGround;
import MainModule.Util.Constants;
import MainModule.View.GameSceneView;
import MainModule.View.Menus.MenuStack;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.ArrayList;

import static javafx.scene.layout.BackgroundRepeat.*;

public class BackGroundTransition extends Transition {
    ImageView backGround;
    ImageView backGround2;

    public BackGroundTransition(BackGround backGround) {
        setCycleDuration(Duration.millis(2000));
        setCycleCount(-1);
        this.backGround = new ImageView(backGround.getBackgrounds().get(0));
        this.backGround.setY(0);
        this.backGround.setX(0);
        this.backGround2 = new ImageView(backGround.getBackgrounds().get(1));
        this.backGround2.setX(-Constants.Max_Width);
        this.backGround2.setY(0);
    }

    @Override
    protected void interpolate(double v) {
        updateBackGrounds(backGround);
        updateBackGrounds(backGround2);
    }
    private void updateBackGrounds(ImageView backGround) {

        if(backGround.getX() + Constants.BACKGROUND_IMAGES_SPEED >= Constants.Max_Width ){
             resetBackGround(backGround);
        }else {
            moveBackGround(backGround);
        }
    }

    public void moveBackGround(ImageView imageView){
        MenuStack.getInstance().getTopMenu().getRoot().getChildren().remove(imageView);
        imageView.setX(imageView.getX() + Constants.BACKGROUND_IMAGES_SPEED);
        MenuStack.getInstance().getTopMenu().getRoot().getChildren().add(0, imageView);
    }
    public void resetBackGround(ImageView imageView){
        MenuStack.getInstance().getTopMenu().getRoot().getChildren().remove(imageView);
        imageView.setX(-Constants.Max_Width);
        MenuStack.getInstance().getTopMenu().getRoot().getChildren().add(0,imageView);
    }

}
