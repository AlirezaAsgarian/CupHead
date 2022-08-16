package org.example.ModuleAbstractClasses.Interfaces.BulletFactories.AvatarBulletFactories;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import org.Main;
import org.example.ModuleAbstractClasses.Constants.Constants;
import org.example.ModuleAbstractClasses.Constants.SetConstants;
import org.example.ModuleAbstractClasses.Enums.MoveFuncs;
import org.example.ModuleAbstractClasses.GameComponents.BossBird.BossBird;
import org.example.ModuleAbstractClasses.Interfaces.BulletFactory;
import org.example.ModuleAbstractClasses.Interfaces.ImageHandler;
import org.example.ModuleAbstractClasses.Interfaces.Imageable;

import java.util.ArrayList;
import java.util.List;

public interface AvatarBulletFactory extends BulletFactory {
    @Override
    default int getBulletHeight() {
        return Constants.AVATAR_BULLET_HEIGHT;
    }

    @Override
    default int getBulletWidth() {
        return Constants.AVATAR_BULLET_WIDTH;
    }

    @Override
    default int getBulletSpeed() {
        return Constants.AVATAR_BULLET_SPEED;
    }

    @Override
    default int getDamageRatio() {
        return SetConstants.AVATAR_BULLET_DAMAGE_RATIO;
    }

    @Override
    default MoveFuncs getBulletMoveFunction() {
        return MoveFuncs.AVATAR_BULLET;
    }

    @Override
    default boolean isFlexible() {
        return false;
    }

    @Override
    default List<ImagePattern> getBulletAnimationImagePatterns() {
        return new ArrayList<>(){{
            for (int i = 1; i <= 9; i++) {
                add(new ImagePattern(ImageHandler.imageFactory("cuphead_frames/frames/Plane/Mini/Bullet/bird"+i+".png")  ));
            }
        }};
    }

    @Override
    default int getTransitionCycleCount() {
        return 1;
    }

    @Override
    default int getAnimationDuration() {
        return Constants.AVATAR_BULLET_DURATION;
    }

    @Override
    default List<Imageable> getBulletEnemies() {
        return List.of(BossBird.getInstance());
    }
    @Override
    default int getHealth(){
        return 0;
    }
}
