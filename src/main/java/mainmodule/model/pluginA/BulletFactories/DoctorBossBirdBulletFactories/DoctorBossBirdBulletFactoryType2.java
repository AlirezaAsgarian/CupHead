package mainmodule.model.pluginA.BulletFactories.DoctorBossBirdBulletFactories;

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

public interface DoctorBossBirdBulletFactoryType2 extends BulletFactory {
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
            for (int i = 1; i <= 32 ; i++) {
                add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 3/Birds/Pills/Normal/Whole/00"+i+".png").toExternalForm())));
            }
        }};
    }

    @Override
    default int getTransitionCycleCount() {
        return 1;
    }

    @Override
    default int getAnimationDuration() {
        return Constants.DOCTOR_BIRD_BULLET_DURATION;
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
        return 0;
    }
}

