package org.example.ModuleAbstractClasses.Enums;


import org.example.ModuleAbstractClasses.GameComponents.Bullet;
import org.example.ModuleAbstractClasses.Interfaces.BulletFactory;
import org.example.ModuleAbstractClasses.Interfaces.Location;
import org.example.ModuleAbstractClasses.Transitions.BossBirdTransitions.BulletTransition;

public interface BulletTransitionFactory {


    default public BulletTransition createBulletTransition(BulletFactory bf, Location location){
        Bullet bullet = new Bullet(location.getX(), location.getY(), bf);
        BulletTransition bt = new BulletTransition(bullet, bf.isFlexible());
        bullet.setBulletTransition(bt);
        return bt;
    }


}
