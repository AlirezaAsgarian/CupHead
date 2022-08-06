package mainmodule.model.pluginA.Controllers.BossBirdStateControllers;

import mainmodule.model.pluginA.BossBirds.bossBirdStateEnums.BossBirdStates;
import mainmodule.model.pluginA.Enums.BulletCollisionType;
import mainmodule.model.*;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.pluginA.BossBirds.MiniBossBird;
import mainmodule.util.ChangeableState;
import mainmodule.model.pluginA.util.SetConstants;
import mainmodule.View.Menus.MenuStack;

import java.util.ArrayList;
import java.util.List;

public class MiniBossBirdStateController implements ChangeableState {
    private final MiniBossBird miniBossBird;

    public MiniBossBirdStateController(MiniBossBird miniBossBird) {
        this.miniBossBird = miniBossBird;
    }

   @Override
    public BossBirdStates updateBossBirdState(BossBirdStates bossBirdState) {
        if (bossBirdState == BossBirdStates.Death) {
            removeBossBirdAndUpdateBossBirdManager();
            return BossBirdStates.Death;
        }
        if (!miniBossBird.hasHealth()) {
            SetConstants.setMiniBossDeathSpeed(calculateBossBirdDeathSpeed());
            stopBossBirdEggBulletsTransitions();
            return BossBirdStates.Death;
        }
        return getUpdatedBossBirdState(bossBirdState);
    }

    private void removeBossBirdAndUpdateBossBirdManager() {
        removeBossBirdFromGame();
        BossBird.getInstance().initializeNewBossBirdAndItsTransitions();
    }

    private void removeBossBirdFromGame() {
        BossBird.setInstance(null);
        updateBossBirdStack();
        removeBossBirdTransitionFromBossBirdManager();
        removeBossBirdFromScreen();
    }

    public static void main(String[] args) {
        List list = new ArrayList();

    }
    private void updateBossBirdStack() {
        BossBirdManger.getInstance().getBossBirdStack().pop();
    }

    private void removeBossBirdTransitionFromBossBirdManager() {
        BossBirdManger.getInstance().removeBossBirdTransitionByBossBird(miniBossBird);
    }

    private void removeBossBirdFromScreen() {
        MenuStack.getInstance().getTopMenu().getRoot().getChildren().remove(miniBossBird);
    }

    private int calculateBossBirdDeathSpeed() {
        return (int) Math.ceil((miniBossBird.getX() + miniBossBird.getWidth()) / 85.0);
    }

    private BossBirdStates getUpdatedBossBirdState(BossBirdStates bossBirdState) {
        switch (bossBirdState) {
            case TURN_TO_LEFT, SHOOTING_TO_LEFT -> {
                bossBirdState = getUpdatedStateAfterChangingState(miniBossBird.isAvatarRightSideOfMiniBoss(), BossBirdStates.TURN_TO_RIGHT, BossBirdStates.SHOOTING_TO_LEFT);
                return bossBirdState;
            }
            case TURN_TO_RIGHT, SHOOTING_TO_RIGHT -> {
                bossBirdState = getUpdatedStateAfterChangingState(!miniBossBird.isAvatarRightSideOfMiniBoss(), BossBirdStates.TURN_TO_LEFT, BossBirdStates.SHOOTING_TO_RIGHT);
                return bossBirdState;
            }
            default -> {
                return null;
            }
        }
    }

    private BossBirdStates getUpdatedStateAfterChangingState(boolean AvatarRightSideOfMiniBoss, BossBirdStates turnToRight, BossBirdStates shootingToLeft) {
        BossBirdStates bossBirdState;
        bossBirdState = changeState(AvatarRightSideOfMiniBoss, turnToRight, shootingToLeft);
        return bossBirdState;
    }

    private BossBirdStates changeState(boolean AvatarRightSideOfMiniBoss, BossBirdStates turnToRight, BossBirdStates shootingToLeft) {
        BossBirdStates bossBirdState;
        if (AvatarRightSideOfMiniBoss) {
            bossBirdState = turnToRight;
        } else {
            bossBirdState = shootingToLeft;
        }
        return bossBirdState;
    }

    private void stopBossBirdEggBulletsTransitions() {
        for (MiniBossBirdBullet miniBossBirdBullet :
                miniBossBird.getMiniBossBirdBullets()) {
            miniBossBirdBullet.getBulletTransition().stop();
            miniBossBirdBullet.getExplosion(BulletCollisionType.HIT_BULLET).play();
        }
    }
}
