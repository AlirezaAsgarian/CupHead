package BossBirdsTypeA.BulletFactories.PoultryBirdBulletsFactories;

import ModuleAbstractClasses.ModuleAbstractClasses.Enums.MoveFuncs;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.Constants;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.SetConstants;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.BulletFactory;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.Imageable;
import javafx.scene.paint.ImagePattern;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.Avatar;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.ImageHandler;

import java.util.ArrayList;
import java.util.List;

public interface YellowPoultryFactory extends BulletFactory {
    @Override
    default int getBulletHeight() {
        return Constants.BOSS_BIRD_POULTRY_HEIGHT;
    }

    @Override
    default int getBulletWidth() {
        return Constants.MINI_BOSS_EGG_WIDTH;
    }

    @Override
    default int getBulletSpeed() {
        return SetConstants.POULTRY_BOSS_BIRD_SPEED;
    }

    @Override
    default int getDamageRatio() {
        return SetConstants.MINI_BOSS_EGG_DAMAGE_RATIO;
    }

    @Override
    default MoveFuncs getBulletMoveFunction() {
        return MoveFuncs.POULTRY_BOSS_BIRD;
    }

    @Override
    default List<ImagePattern> getBulletAnimationImagePatterns() {
        return new ArrayList<>() {{
            for (int i = 1; i <= 100; i++) {
                add(new ImagePattern(ImageHandler.imageFactory("cuphead_frames/frames/MiniBossFly/yellow/" + i + ".png")));
            }
        }};
    }

    @Override
    default int getTransitionCycleCount() {
        return 1;
    }

    @Override
    default int getAnimationDuration() {
        return Constants.BOSS_BIRD_POULTRY_DURATION;
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
        return 5;
    }
}
