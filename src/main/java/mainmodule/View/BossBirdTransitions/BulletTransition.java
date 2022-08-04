package mainmodule.View.BossBirdTransitions;

import mainmodule.Enums.BulletCollisionType;
import mainmodule.Enums.TransitionType;
import mainmodule.model.Bullet;
import mainmodule.model.TransitionManger;
import mainmodule.View.Menus.MenuStack;
import javafx.animation.Transition;
import javafx.util.Duration;

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
        initializeSetOnFinishAction();
    }

    public Bullet getBullet() {
        return bullet;
    }

    @Override
    protected void interpolate(double v) {
        bullet.checkForCollision();
        updateBulletFrame(v);
        bullet.getMoveFuncs().getMoving().move(bullet);
    }

    private void updateBulletFrame(double v) {
        int length = bullet.getAnimation().size();
        int frame = calculateFrame(v,length);
        if (isFlexibleSize) {
            setNewPrefSizeForBullet(frame);
        }
        bullet.setImage(bullet.getAnimation().get(frame - 1));
    }

    private void setNewPrefSizeForBullet(int frame) {
        bullet.setWidth(bullet.getAnimation().get(frame - 1).getImage().getWidth());
        bullet.setHeight(bullet.getAnimation().get(frame - 1).getImage().getHeight());
    }

    private void initializeSetOnFinishAction() {
        setOnFinished(actionEvent -> {
            bullet.getExplosion(BulletCollisionType.HIT_BULLET).play();
            MenuStack.getInstance().getCurrentGame().getBulletTransitions().remove(this);
        });
    }
    private int calculateFrame(double v,int length){
        int result = (int) Math.ceil(v * length);
        if (result == 0) result = 1;
        return result;
    }
}



