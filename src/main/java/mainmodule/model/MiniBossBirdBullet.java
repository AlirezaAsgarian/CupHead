package mainmodule.model;

import mainmodule.View.BossBirdTransitions.MiniBossBirdSpecialBulletTransition;
import mainmodule.model.pluginA.BossBirds.MiniBossBird;
import mainmodule.model.pluginA.BulletFactories.MiniBossBirdBulletFactories.MiniBossBulletEggFactory;
import mainmodule.model.pluginA.util.Constants;
import javafx.animation.Transition;
import javafx.scene.transform.Rotate;

public class MiniBossBirdBullet extends Bullet  {
    public MiniBossBirdBullet(double v, double v1, MiniBossBird ownBossBird) {
        super(v, v1, new MiniBossBulletEggFactory() {
        });
        initializeBulletRotationTransform(ownBossBird);
        this.health = Constants.MINI_BOSS_EGG_HEALTH;
    }

    private void initializeBulletRotationTransform(MiniBossBird ownBossBird) {
        bulletRotate = new Rotate();
        bulletRotate.setPivotX(ownBossBird.getxCenter());
        bulletRotate.setPivotY(ownBossBird.getyCenter());
        this.getTransforms().add(bulletRotate);
    }

    Transition miniBulletTransition;

    Rotate bulletRotate;
    int health;

    public Rotate getBulletRotate() {
        return bulletRotate;
    }

    public Transition getMiniBulletRotateTransition() {
        if (miniBulletTransition != null) return miniBulletTransition;
        miniBulletTransition = new MiniBossBirdSpecialBulletTransition(this,true);
        return miniBulletTransition;
    }


}
