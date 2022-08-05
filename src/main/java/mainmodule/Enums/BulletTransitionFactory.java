package mainmodule.Enums;

import mainmodule.Controllers.Location;
import mainmodule.model.Avatar;
import mainmodule.model.BossBirds.BossBird;
import mainmodule.model.BossBirdManger;
import mainmodule.model.Bullet;
import mainmodule.View.BossBirdTransitions.BulletTransition;
import mainmodule.model.BulletFactory;

import java.util.ArrayList;
import java.util.List;

public interface BulletTransitionFactory {


    default public BulletTransition createBulletTransition(BulletFactory bulletType, Location location){
        Bullet bullet = new Bullet(location.getX(), location.getY(), bulletType);
        BulletTransition bt = new BulletTransition(bullet, bulletType.isFlexible());
        bullet.setBulletTransition(bt);
        return bt;
    }


}
