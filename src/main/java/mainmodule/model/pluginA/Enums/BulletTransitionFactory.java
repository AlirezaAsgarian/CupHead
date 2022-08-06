package mainmodule.model.pluginA.Enums;

import mainmodule.util.Location;
import mainmodule.model.Bullet;
import mainmodule.View.BossBirdTransitions.BulletTransition;
import mainmodule.model.BulletFactory;

public interface BulletTransitionFactory {


    default public  BulletTransition createBulletTransition(BulletFactory bf, Location location){
        Bullet bullet = new Bullet(location.getX(), location.getY(), bf);
        BulletTransition bt = new BulletTransition(bullet, bf.isFlexible());
        bullet.setBulletTransition(bt);
        return bt;
    }


}
