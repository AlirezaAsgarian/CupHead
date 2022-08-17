package BossBirdsTypeA.BossBirds;

import javafx.util.Pair;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Bullet;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.BossBird.BossBird;
import BossBirdsTypeA.BulletFactories.MiniBossBirdBulletFactories.MiniBossBulletEggFactory;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.Constants;
import javafx.animation.Transition;
import javafx.scene.transform.Rotate;
import BossBirdsTypeA.UniqueTransitions.MiniBossBirdSpecialBulletTransition;

public class MiniBossBirdBullet extends Bullet {

    Transition miniBulletTransition;
    Rotate bulletRotate;
    int health;

    public MiniBossBirdBullet(double v, double v1, MiniBossBird ownBossBird) {
        super(v, v1, new MiniBossBulletEggFactory() {
        });
        initializeBulletRotationTransform(ownBossBird);
        this.health = Constants.MINI_BOSS_EGG_HEALTH;
    }

    private void initializeBulletRotationTransform(MiniBossBird ownBossBird) {
        bulletRotate = new Rotate();
        bulletRotate.setPivotX(ownBossBird.getXCenter());
        bulletRotate.setPivotY(ownBossBird.getYCenter());
        this.getTransforms().add(bulletRotate);
    }

    public Rotate getBulletRotate() {
        return bulletRotate;
    }

    public Transition getMiniBulletRotateTransition() {
        if (miniBulletTransition != null) return miniBulletTransition;
        miniBulletTransition = new MiniBossBirdSpecialBulletTransition(this, true);
        return miniBulletTransition;
    }


    public double calculateDistanceWithMiniBossBird() {
        double xDistance = BossBird.getInstance().getBoundsInParent().getCenterX() - this.getBoundsInParent().getCenterX();
        double yDistance = BossBird.getInstance().getBoundsInParent().getCenterX() - this.getBoundsInParent().getCenterY();
        return Math.sqrt(Math.pow(xDistance, 2.0) + Math.pow(yDistance, 2.0));
    }

    public Pair<Double, Double> calculateXAndYTranslate(Double distance) {
        double xDistance = BossBird.getInstance().getBoundsInParent().getCenterX() - this.getBoundsInParent().getCenterX();
        double yDistance = BossBird.getInstance().getBoundsInParent().getCenterX() - this.getBoundsInParent().getCenterY();
        return new Pair<>(xDistance / distance, yDistance / distance);
    }
}
