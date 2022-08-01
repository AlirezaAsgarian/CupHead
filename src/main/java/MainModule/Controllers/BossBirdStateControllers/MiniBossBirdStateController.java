package MainModule.Controllers.BossBirdStateControllers;

import MainModule.Enums.BulletCollisionType;
import MainModule.Model.*;
import MainModule.Model.BossBirds.BossBird;
import MainModule.Model.BossBirds.MiniBossBird;
import MainModule.Util.SetConstants;
import MainModule.View.Menus.MenuStack;

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
