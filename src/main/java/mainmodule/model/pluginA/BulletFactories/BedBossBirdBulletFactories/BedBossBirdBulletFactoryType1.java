package mainmodule.model.pluginA.BulletFactories.BedBossBirdBulletFactories;

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

public interface BedBossBirdBulletFactoryType1 extends BulletFactory {
    @Override
    default int getBulletHeight() {
        return Constants.BED_BOSS_BIRD_BULLET1_HEIGHT;
    }

    @Override
    default int getBulletWidth() {
        return Constants.BED_BOSS_BIRD_BULLET1_WIDTH;
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
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 3/Attack/shooting/Bullets/garbage0.png").toExternalForm())));
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
