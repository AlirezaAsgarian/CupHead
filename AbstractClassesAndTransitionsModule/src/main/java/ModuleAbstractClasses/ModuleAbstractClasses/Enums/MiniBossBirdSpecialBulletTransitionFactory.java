package ModuleAbstractClasses.ModuleAbstractClasses.Enums;

import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.BulletFactory;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.Location;
import ModuleAbstractClasses.ModuleAbstractClasses.Transitions.BossBirdTransitions.BulletTransition;

public interface MiniBossBirdSpecialBulletTransitionFactory extends BulletTransitionFactory {


    @Override
    default BulletTransition createBulletTransition(BulletFactory bf, Location location) {
        return null;
    }
}
