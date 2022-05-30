package MainModule.View.BossBirdTransitions;

import MainModule.Model.Bullet;
import MainModule.View.GameSceneView;
import javafx.animation.Transition;
import javafx.util.Duration;

public class BulletTransition extends Transition {
    Bullet bullet;
    boolean isFlexibleSize;

    public BulletTransition(Bullet bullet, boolean isFlexibleSize) {
        setCycleDuration(Duration.millis(bullet.getDuration()));
        setCycleCount(bullet.getCycleCount());
        this.bullet = bullet;
        this.isFlexibleSize = isFlexibleSize;
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
            GameSceneView.anchorPane.getChildren().remove(bullet);
        });
    }
}
