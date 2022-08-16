package mainmodule.model.ModuleAbstractClasses;

import javafx.scene.paint.ImagePattern;
import mainmodule.model.Imageable;
import mainmodule.model.pluginA.Enums.MoveFuncs;

import java.util.List;

public interface BulletFactory {
    int getBulletHeight();
    int getBulletWidth();
    int getBulletSpeed();
    int getDamageRatio();
    MoveFuncs getBulletMoveFunction();

    List<ImagePattern> getBulletAnimationImagePatterns();

    int getTransitionCycleCount();
    int getAnimationDuration();
    List<Imageable> getBulletEnemies();

    boolean isFlexible();

    int getHealth();
}