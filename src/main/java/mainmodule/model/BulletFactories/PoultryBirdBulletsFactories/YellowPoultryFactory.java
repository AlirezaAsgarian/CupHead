package mainmodule.model.BulletFactories.PoultryBirdBulletsFactories;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.BuilderFactory;
import mainmodule.Enums.MoveFuncs;
import mainmodule.Main;
import mainmodule.model.Avatar;
import mainmodule.model.BulletFactory;
import mainmodule.model.Imageable;
import mainmodule.util.Constants;
import mainmodule.util.SetConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
                add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/MiniBossFly/purple/"+i+".png").toExternalForm())));
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
}
