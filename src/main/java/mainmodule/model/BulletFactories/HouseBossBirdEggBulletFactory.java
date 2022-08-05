package mainmodule.model.BulletFactories;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import mainmodule.Enums.MoveFuncs;
import mainmodule.Main;
import mainmodule.model.Avatar;
import mainmodule.model.BulletFactory;
import mainmodule.model.Imageable;
import mainmodule.util.Constants;
import mainmodule.util.SetConstants;

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
        return List.of(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/images/egg.png").toExternalForm())));
    }
    @Override
    default boolean isFlexible() {
        return false;
    }
}
