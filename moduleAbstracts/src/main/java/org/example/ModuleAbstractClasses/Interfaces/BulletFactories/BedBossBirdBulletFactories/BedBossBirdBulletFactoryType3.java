package org.example.ModuleAbstractClasses.Interfaces.BulletFactories.BedBossBirdBulletFactories;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import org.Main;
import org.example.ModuleAbstractClasses.Constants.Constants;
import org.example.ModuleAbstractClasses.Constants.SetConstants;
import org.example.ModuleAbstractClasses.Enums.MoveFuncs;
import org.example.ModuleAbstractClasses.GameComponents.Avatar.Avatar;
import org.example.ModuleAbstractClasses.Interfaces.BulletFactory;
import org.example.ModuleAbstractClasses.Interfaces.ImageHandler;
import org.example.ModuleAbstractClasses.Interfaces.Imageable;

import java.util.ArrayList;
import java.util.List;

public interface BedBossBirdBulletFactoryType3 extends BulletFactory {
    @Override
    default int getBulletHeight() {
        return Constants.BED_BOSS_BIRD_BULLET3_HEIGHT;
    }

    @Override
    default int getBulletWidth() {
        return Constants.BED_BOSS_BIRD_BULLET3_WIDTH;
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
        return new ArrayList<>(){{
            add(new ImagePattern(ImageHandler.imageFactory("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 3/Attack/shooting/Bullets/garbage2.png") ));
        }};
    }

    @Override
    default int getTransitionCycleCount() {
        return 1;
    }

    @Override
    default int getAnimationDuration() {
        return Constants.BED_BOSS_BULLET_DURATION;
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
    default int getHealth(){
        return 0;
    }

}
