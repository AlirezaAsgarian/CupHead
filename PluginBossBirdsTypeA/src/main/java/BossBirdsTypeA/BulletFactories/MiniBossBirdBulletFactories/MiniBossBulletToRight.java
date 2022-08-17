package BossBirdsTypeA.BulletFactories.MiniBossBirdBulletFactories;

import javafx.scene.paint.ImagePattern;
import ModuleAbstractClasses.ModuleAbstractClasses.Enums.MoveFuncs;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.Avatar;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.BulletFactory;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.ImageHandler;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.Imageable;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.Constants;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.SetConstants;

import java.util.ArrayList;
import java.util.List;

public interface MiniBossBulletToRight extends BulletFactory {
    @Override
    default int getBulletHeight() {
        return Constants.MINI_BOSS_BULLET_HEIGHT;
    }

    @Override
    default int getBulletWidth() {
        return Constants.MINI_BOSS_BULLET_WIDTH;
    }

    @Override
    default int getBulletSpeed() {
        return Constants.MINI_BOSS_BULLET_SPEED;
    }

    @Override
    default int getDamageRatio() {
        return SetConstants.MINI_BOSS_BULLET_DAMAGE_RATIO;
    }

    @Override
    default MoveFuncs getBulletMoveFunction() {
        return MoveFuncs.MINI_BOSS_BULLET_TO_RIGHT;
    }


    @Override
    default List<ImagePattern> getBulletAnimationImagePatterns() {
        return new ArrayList<>() {{
            for (int i = 1; i <= 6; i++) {
                add(new ImagePattern(ImageHandler.imageFactory("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Bullet/to_right/egghead_bullet_000" + i + ".png")));
            }
        }};
    }

    @Override
    default int getTransitionCycleCount() {
        return 1;
    }

    @Override
    default int getAnimationDuration() {
        return Constants.MINI_BOSS_BULLET_DURATION;
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
