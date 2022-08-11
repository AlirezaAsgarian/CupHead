package mainmodule.View.BackGroundTransiton;

import mainmodule.model.TransitionType;
import mainmodule.model.pluginA.Enums.BackGround;
import mainmodule.model.TransitionManger;
import mainmodule.model.pluginA.util.Constants;
import mainmodule.View.Menus.MenuStack;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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
        TransitionManger.addTransition(TransitionType.BACKGROUND_TRANSITION,this);
        setOnFinished((actionEvent) -> TransitionManger.removeTransition(TransitionType.BACKGROUND_TRANSITION,this));
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
