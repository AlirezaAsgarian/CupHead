package mainmodule.View.BossBirdTransitions;

import javafx.util.Pair;
import mainmodule.model.Bullet;
import mainmodule.model.MiniBossBirdBullet;
import mainmodule.model.pluginA.util.Constants;
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
         //   logger.debug("{}  {}",mb.getBulletRotate().pivotXProperty(),mb.getBulletRotate().pivotYProperty());
            double distance = mb.calculateDistanceWithMiniBossBird();
            if (distance >= Constants.MINI_BOSS_EGG_MAX_DISTANCE || distance <= Constants.MINI_BOSS_EGG_MIN_DISTANCE) {
                direction = -direction;
            }
            Pair<Double,Double> xAndYTranslate = mb.calculateXAndYTranslate(distance);
            logger.debug("{}",xAndYTranslate);
            mb.setX(mb.getX() + direction * xAndYTranslate.getKey());
            mb.setY(mb.getY() - direction * xAndYTranslate.getValue());
            rotateBullet(mb);
        }
    }

    private void rotateBullet(MiniBossBirdBullet mb) {
        mb.getBulletRotate().setAngle(mb.getBulletRotate().getAngle() + 1);
    }


}
