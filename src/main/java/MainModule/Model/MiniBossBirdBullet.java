package MainModule.Model;

import MainModule.Enums.Bullets;
import MainModule.Enums.TransitionType;
import MainModule.Model.BossBirds.BossBird;
import MainModule.Model.BossBirds.MiniBossBird;
import MainModule.Util.Constants;
import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class MiniBossBirdBullet extends Bullet {
    public MiniBossBirdBullet(double v, double v1, ArrayList<Rectangle> enemy, Bullets bullet, MiniBossBird ownBossBird) {
        super(v, v1, bullet, enemy);
        bulletRotate = new Rotate();
        bulletRotate.setPivotX(ownBossBird.getxCenter());
        bulletRotate.setPivotY(ownBossBird.getyCenter());
        this.getTransforms().add(bulletRotate);
        this.health = Constants.MINI_BOSS_EGG_HEALTH;
    }

    Transition miniBulletTransition;

    Rotate bulletRotate;
    int health;

    public Rotate getBulletRotate() {
        return bulletRotate;
    }

    public Transition getMiniBulletRotateTransition() {
        if(miniBulletTransition != null) return miniBulletTransition;
        miniBulletTransition = initializeBulletRotateTransition();
        return miniBulletTransition;
    }

    private Transition initializeBulletRotateTransition() {
        return new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
                setCycleCount(-1);
                TransitionManger.addTransition(TransitionType.BULLET_TRANSITION, this);
                setOnFinished((e) -> TransitionManger.removeTransition(TransitionType.BULLET_TRANSITION, this));
            }

            @Override
            protected void interpolate(double v) {
                MiniBossBirdBullet.this.getBulletRotate().setPivotY(BossBird.getInstance().getyCenter());
                MiniBossBirdBullet.this.getBulletRotate().setPivotX(BossBird.getInstance().getxCenter());
                MiniBossBirdBullet.this.getBulletRotate().setAngle(MiniBossBirdBullet.this.getBulletRotate().getAngle() + 1);
            }
        };
    }
}
