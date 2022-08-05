package mainmodule.model;

import mainmodule.Enums.Bullets;
import mainmodule.Enums.TransitionType;
import mainmodule.model.BossBirds.BossBird;
import mainmodule.model.BossBirds.MiniBossBird;
import mainmodule.model.BulletFactories.MiniBossBirdBulletFactories.MiniBossBulletEggFactory;
import mainmodule.util.Constants;
import javafx.animation.Transition;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class MiniBossBirdBullet extends Bullet {
    public MiniBossBirdBullet(double v, double v1, ArrayList<Imageable> enemy, Bullets bullet, MiniBossBird ownBossBird) {
        super(v, v1, new MiniBossBulletEggFactory() {});
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
