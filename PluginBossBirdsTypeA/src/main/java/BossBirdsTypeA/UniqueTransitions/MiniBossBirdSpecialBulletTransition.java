package BossBirdsTypeA.UniqueTransitions;

import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Bullet;
import ModuleAbstractClasses.ModuleAbstractClasses.Transitions.BossBirdTransitions.BulletTransition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import BossBirdsTypeA.BossBirds.MiniBossBirdBullet;

public class MiniBossBirdSpecialBulletTransition extends BulletTransition {
    static Logger logger = LoggerFactory.getLogger(MiniBossBirdSpecialBulletTransition.class);
    int direction;

    public MiniBossBirdSpecialBulletTransition(Bullet bullet, boolean isFlexibleSize) {
        super(bullet, isFlexibleSize);
        direction = 1;
    }

    @Override
    protected void interpolate(double v) {
        if (getBullet() instanceof MiniBossBirdBullet mb) {
            mb.checkForCollision();
            updateBulletFrame(v);
            rotateBullet(mb);
        }
    }

    private void rotateBullet(MiniBossBirdBullet mb) {
        mb.getBulletRotate().setAngle(mb.getBulletRotate().getAngle() + 1);
    }


}
