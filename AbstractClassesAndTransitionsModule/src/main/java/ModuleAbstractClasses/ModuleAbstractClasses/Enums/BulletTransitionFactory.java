package ModuleAbstractClasses.ModuleAbstractClasses.Enums;


import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.BulletFactory;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.Location;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Bullet;
import ModuleAbstractClasses.ModuleAbstractClasses.Transitions.BossBirdTransitions.BulletTransition;

public interface BulletTransitionFactory {


    default BulletTransition createBulletTransition(BulletFactory bf, Location location) {
        Bullet bullet = new Bullet(location.getX(), location.getY(), bf);
        BulletTransition bt = new BulletTransition(bullet, bf.isFlexible());
        bullet.setBulletTransition(bt);
        return bt;
    }


}
