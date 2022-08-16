package org.example.ModuleAbstractClasses.Interfaces.BulletFactories.PoultryBirdBulletsFactories;

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
        return new ArrayList<>(){{
            for (int i = 1; i <= 100 ; i++) {
                add(new ImagePattern(ImageHandler.imageFactory("cuphead_frames/frames/MiniBossFly/yellow/"+i+".png")  ));
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
    default int getHealth(){
        return 5;
    }
}
