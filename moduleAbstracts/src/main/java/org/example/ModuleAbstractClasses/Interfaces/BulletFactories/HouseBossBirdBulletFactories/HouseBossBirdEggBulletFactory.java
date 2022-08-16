package org.example.ModuleAbstractClasses.Interfaces.BulletFactories.HouseBossBirdBulletFactories;

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

import java.util.List;

public interface HouseBossBirdEggBulletFactory extends BulletFactory {

    @Override
    default int getDamageRatio() {
        return SetConstants.EGG_BULLET_DAMAGE_RATIO;
    }



    @Override
    default MoveFuncs getBulletMoveFunction() {
        return MoveFuncs.EGG_BULLET;
    }

    @Override
    default int getBulletSpeed() {
        return Constants.EGG_BULLET_SPEED;
    }

    @Override
    default int getTransitionCycleCount() {
        return 1;
    }

    @Override
    default int getBulletWidth() {
        return Constants.EGG_BULLET_WIDTH;
    }

    @Override
    default int getBulletHeight() {
        return Constants.EGG_BULLET_HEIGHT;
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
    default List<ImagePattern> getBulletAnimationImagePatterns() {
        return List.of(new ImagePattern(ImageHandler.imageFactory("cuphead_frames/frames/images/egg.png") ));
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
