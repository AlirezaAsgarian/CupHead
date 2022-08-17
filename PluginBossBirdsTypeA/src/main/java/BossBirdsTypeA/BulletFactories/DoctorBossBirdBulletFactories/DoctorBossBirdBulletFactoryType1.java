package BossBirdsTypeA.BulletFactories.DoctorBossBirdBulletFactories;

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

public interface DoctorBossBirdBulletFactoryType1 extends BulletFactory {
    @Override
    default int getBulletHeight() {
        return Constants.BED_BOSS_BIRD_BULLET2_HEIGHT;
    }

    @Override
    default int getBulletWidth() {
        return Constants.BED_BOSS_BIRD_BULLET2_WIDTH;
    }

    @Override
    default int getBulletSpeed() {
        return Constants.MINI_BOSS_EGG_SPEED;
    }

    @Override
    default int getDamageRatio() {
        return SetConstants.MINI_BOSS_EGG_DAMAGE_RATIO;
    }

    @Override
    default MoveFuncs getBulletMoveFunction() {
        return MoveFuncs.BED_BOSS_BIRD_BULLET;
    }

    @Override
    default List<ImagePattern> getBulletAnimationImagePatterns() {
        return new ArrayList<>() {{
            for (int i = 1; i <= 32; i++) {
                add(new ImagePattern(ImageHandler.imageFactory("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 3/Birds/Pills/Pink/Whole/00" + i + ".png")));
            }
        }};
    }

    @Override
    default int getTransitionCycleCount() {
        return 1;
    }

    @Override
    default int getAnimationDuration() {
        return Constants.DOCTOR_BIRD_BULLET_DURATION;
    }

    @Override
    default List<Imageable> getBulletEnemies() {
        return List.of(Avatar.getInstance());
    }

    @Override
    default boolean isFlexible() {
        return true;
    }

    @Override
    default int getHealth() {
        return 0;
    }
}

