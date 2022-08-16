package org.example.ModuleAbstractClasses.Enums;

import org.example.ModuleAbstractClasses.Interfaces.BulletFactory;
import org.example.ModuleAbstractClasses.Interfaces.Location;
import org.example.ModuleAbstractClasses.Transitions.BossBirdTransitions.BulletTransition;

public interface MiniBossBirdSpecialBulletTransitionFactory extends BulletTransitionFactory{


    @Override
    default BulletTransition createBulletTransition(BulletFactory bf, Location location) {
        return null;
    }
}
