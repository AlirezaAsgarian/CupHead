package mainmodule.model.pluginA.Enums;

import mainmodule.util.Location;
import mainmodule.View.BossBirdTransitions.BulletTransition;
import mainmodule.model.BulletFactory;

public interface MiniBossBirdSpecialBulletTransitionFactory extends BulletTransitionFactory{


    @Override
    default BulletTransition createBulletTransition(BulletFactory bf, Location location) {
        return null;
    }
}
