package mainmodule.model.pluginA.BulletFactories.MiniBossBirdBulletFactories;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import mainmodule.model.pluginA.Enums.MoveFuncs;
import mainmodule.Main;
import mainmodule.model.Avatar;
import mainmodule.model.BulletFactory;
import mainmodule.model.Imageable;
import mainmodule.model.pluginA.util.Constants;
import mainmodule.model.pluginA.util.SetConstants;

import java.util.ArrayList;
import java.util.List;

public interface MiniBossBulletEggFactory extends BulletFactory {
    @Override
    default int getBulletHeight() {
        return Constants.MINI_BOSS_EGG_HEIGHT;
    }

    @Override
    default int getBulletWidth() {
        return Constants.MINI_BOSS_EGG_WIDTH;
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
        return MoveFuncs.MINI_BOSS_BULLET_EGG;
    }

    @Override
    default List<ImagePattern> getBulletAnimationImagePatterns() {
        return new ArrayList<>(){{
            for (int i = 1; i <= 32 ; i++) {
                add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Egg/00" + i + ".png").toExternalForm())));
            }
        }};
    }

    @Override
    default int getTransitionCycleCount() {
        // it means infinity
        return -1;
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
    default int getHealth(){
        return 5;
    }
}
