package BossBirdsTypeA.BulletFactories.HouseBossBirdBulletFactories;

import javafx.scene.paint.ImagePattern;
import ModuleAbstractClasses.ModuleAbstractClasses.Enums.MoveFuncs;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.Avatar;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.BulletFactory;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.ImageHandler;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.Imageable;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.Constants;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.SetConstants;

import java.util.List;

public interface HouseBossBirdEggBulletFactory extends BulletFactory {

    @Override
    default int getBulletHeight() {
        return Constants.EGG_BULLET_HEIGHT;
    }

    @Override
    default int getBulletWidth() {
        return Constants.EGG_BULLET_WIDTH;
    }

    @Override
    default int getBulletSpeed() {
        return Constants.EGG_BULLET_SPEED;
    }

    @Override
    default int getDamageRatio() {
        return SetConstants.EGG_BULLET_DAMAGE_RATIO;
    }

    @Override
    default MoveFuncs getBulletMoveFunction() {
        return MoveFuncs.EGG_BULLET;
    }

    @Override
    default List<ImagePattern> getBulletAnimationImagePatterns() {
        return List.of(new ImagePattern(ImageHandler.imageFactory("cuphead_frames/frames/images/egg.png")));
    }

    @Override
    default int getTransitionCycleCount() {
        return 1;
    }

    @Override
    default int getAnimationDuration() {
        return Constants.EGG_BULLET_DURATION;
    }

    @Override
    default List<Imageable> getBulletEnemies() {
        return List.of(Avatar.getInstance());
    }

    @Override
    default boolean isFlexible() {
        return false;
    }

    @Override
    default int getHealth() {
        return 0;
    }
}
