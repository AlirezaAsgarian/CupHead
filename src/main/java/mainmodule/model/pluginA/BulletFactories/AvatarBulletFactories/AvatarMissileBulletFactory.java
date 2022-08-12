package mainmodule.model.pluginA.BulletFactories.AvatarBulletFactories;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import mainmodule.model.pluginA.Enums.MoveFuncs;
import mainmodule.Main;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.BulletFactory;
import mainmodule.model.Imageable;
import mainmodule.model.pluginA.util.Constants;
import mainmodule.model.pluginA.util.SetConstants;

import java.util.ArrayList;
import java.util.List;

public interface AvatarMissileBulletFactory extends BulletFactory {
    @Override
    default int getBulletHeight() {
        return Constants.AVATAR_MISSLE_HEIGHT;
    }

    @Override
    default int getBulletWidth() {
        return Constants.AVATAR_MISSLE_WIDTH;
    }

    @Override
    default int getBulletSpeed() {
        return Constants.AVATAR_MISLLE_SPEED;
    }

    @Override
    default int getDamageRatio() {
        return SetConstants.AVATAR_TIR_HAVAII_DAMAGE_RATIO;
    }

    @Override
    default MoveFuncs getBulletMoveFunction() {
        return MoveFuncs.AVATAR_MISSLE;
    }

    @Override
    default List<ImagePattern> getBulletAnimationImagePatterns() {
        return new ArrayList<>(){{
            for (int i = 1; i <= 7; i++) {
                add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/Plane/missle/00" + i + ".png").toExternalForm())));
            }
        }};
    }

    @Override
    default int getTransitionCycleCount() {
        return 1;
    }

    @Override
    default int getAnimationDuration() {
        return Constants.AVATAR_MISSLE_DURATION;
    }

    @Override
    default List<Imageable> getBulletEnemies() {
        return List.of(BossBird.getInstance());
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
