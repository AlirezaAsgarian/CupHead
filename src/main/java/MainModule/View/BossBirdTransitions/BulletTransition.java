package MainModule.View.BossBirdTransitions;

import MainModule.Enums.TransitionType;
import MainModule.Model.Bullet;
import MainModule.Model.TransitionManger;
import MainModule.View.GameSceneView;
import MainModule.View.Menus.MenuStack;
import javafx.animation.Transition;
import javafx.util.Duration;

import java.util.ArrayList;

public class BulletTransition extends Transition {
    Bullet bullet;
    boolean isFlexibleSize;

    public BulletTransition(Bullet bullet, boolean isFlexibleSize) {
        setCycleDuration(Duration.millis(bullet.getDuration()));
        setCycleCount(bullet.getCycleCount());
        MenuStack.getInstance().getCurrentGame().getBulletTransitions().add(this);
        this.bullet = bullet;
        this.isFlexibleSize = isFlexibleSize;
        TransitionManger.addTransition(TransitionType.BULLET_TRANSITION,this);
        this.setOnFinished(actionEvent -> {TransitionManger.removeTransition(TransitionType.BULLET_TRANSITION,this);});
    }

    public Bullet getBullet() {
        return bullet;
    }

    @Override
    protected void interpolate(double v) {
        bullet.checkForCollision();
        int length = bullet.getAnimation().size();
        int frame = (int) Math.ceil(v * length);
        if (frame == 0) frame = 1;
        if (isFlexibleSize) {
            bullet.setWidth(bullet.getAnimation().get(frame - 1).getImage().getWidth());
            bullet.setHeight(bullet.getAnimation().get(frame - 1).getImage().getHeight());
        }
        bullet.setFill(bullet.getAnimation().get(frame - 1));
        bullet.getMoveFuncs().getMoving().move(bullet);
        setOnFinished(actionEvent -> {
            bullet.getExplosion(event -> {}).play();
            MenuStack.getInstance().getTopMenu().getRoot().getChildren().remove(bullet);
            MenuStack.getInstance().getCurrentGame().getBulletTransitions().remove(this);
        });
    }
}



