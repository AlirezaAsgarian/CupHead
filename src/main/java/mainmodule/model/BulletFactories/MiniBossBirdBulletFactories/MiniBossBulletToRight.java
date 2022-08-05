package mainmodule.model.BulletFactories.MiniBossBirdBulletFactories;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import mainmodule.Enums.MoveFuncs;
import mainmodule.Main;
import mainmodule.model.Avatar;
import mainmodule.model.BulletFactory;
import mainmodule.model.Imageable;
import mainmodule.util.Constants;
import mainmodule.util.SetConstants;

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
        return new ArrayList<>(){{
            for (int i = 1; i <= 6 ; i++) {
                add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Bullet/to_right/egghead_bullet_000" + i + ".png").toExternalForm())));
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
}
