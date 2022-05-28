package MainModule.Model;

import MainModule.Enums.Bullets;
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

    public Transition getMiniBulletTransition() {
        if(miniBulletTransition != null) return miniBulletTransition;
        miniBulletTransition = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
                setCycleCount(-1);
            }
            @Override
            protected void interpolate(double v) {
                MiniBossBirdBullet.this.getBulletRotate().setPivotY(BossBird.getInstance().getyCenter());
                MiniBossBirdBullet.this.getBulletRotate().setPivotX(BossBird.getInstance().getxCenter());
                MiniBossBirdBullet.this.getBulletRotate().setAngle(MiniBossBirdBullet.this.getBulletRotate().getAngle() + 1 );
            }
        };
        return miniBulletTransition;
    }
}
