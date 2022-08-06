package mainmodule.View.BossBirdTransitions;

import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.Bullet;
import mainmodule.model.MiniBossBirdBullet;

public class MiniBossBirdSpecialBulletTransition extends BulletTransition {
    public MiniBossBirdSpecialBulletTransition(Bullet bullet, boolean isFlexibleSize) {
        super(bullet, isFlexibleSize);
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
        mb.getBulletRotate().setPivotY(BossBird.getInstance().getyCenter());
        mb.getBulletRotate().setPivotX(BossBird.getInstance().getxCenter());
        mb.getBulletRotate().setAngle(mb.getBulletRotate().getAngle() + 1);
    }
}
