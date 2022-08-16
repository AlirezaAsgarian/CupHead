package mainmodule.View.BossBirdTransitions;

import mainmodule.model.ModuleAbstractClasses.Bullet;
import mainmodule.model.MiniBossBirdBullet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MiniBossBirdSpecialBulletTransition extends BulletTransition {
    int direction ;
    static Logger logger = LoggerFactory.getLogger(MiniBossBirdSpecialBulletTransition.class);
    public MiniBossBirdSpecialBulletTransition(Bullet bullet, boolean isFlexibleSize)
    {
        super(bullet, isFlexibleSize);
        direction = 1;
    }

    @Override
    protected void interpolate(double v) {
        if (bullet instanceof MiniBossBirdBullet mb) {
            mb.checkForCollision();
            updateBulletFrame(v);
            rotateBullet(mb);
        }
    }

    private void rotateBullet(MiniBossBirdBullet mb) {
        mb.getBulletRotate().setAngle(mb.getBulletRotate().getAngle() + 1);
    }


}
