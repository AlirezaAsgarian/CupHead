package MainModule.Enums;

import MainModule.Model.Avatar;
import MainModule.Model.BossBirds.BossBird;
import MainModule.Model.TransitionManger;
import MainModule.Util.Constants;

public enum AvatarStates {
    BLINK(false, AvatarShootingKeySettings.NORMAL_BULLETS, true, AvatarMoveKeySettings.NORMAL, Constants.AVATAR_BLINK_STATE_DURATION, 5, v -> {
        if (v > 0.5) {
            Avatar.getInstance().setOpacity(1.0);
        } else {
            Avatar.getInstance().setOpacity(0.3);
        }
    }),
    NORMAL(true, AvatarShootingKeySettings.NORMAL_BULLETS, true, AvatarMoveKeySettings.NORMAL, Constants.AVATAR_NORMAL_STATE_DURATION, 1, v -> {
    }),
    NORMAL_BOMBING(true, AvatarShootingKeySettings.AVATAR_BOMB, true, AvatarMoveKeySettings.NORMAL, Constants.AVATAR_NORMAL_BOMBING_STATE_DURATION, 1, v -> {
    }),
    //    LITTLE,
    MISSILE(false, AvatarShootingKeySettings.MISSLE, true, AvatarMoveKeySettings.MISSLE, Constants.AVATAR_MISSLE_STATE_DURATION, 1, v -> {
        if (v * 26 > 8 && !TransitionManger.getAvatarTransition().isUniqueActionExecuteOnce()) {
            Thread thread = Thread.currentThread();
            synchronized (thread) {
                try {
                    TransitionManger.getAvatarTransition().setUniqueActionExecuteOnce(true);
                    TransitionManger.getAvatarTransition().pause();
                    thread.wait(2000);
                    TransitionManger.getAvatarTransition().play();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }else if(v * 26 > 8){
            Avatar.getInstance().moveRight(Constants.AVATAR_MISLLE_SPEED);
            if(Avatar.getInstance().getBoundsInParent().intersects(BossBird.getInstance().getBoundsInParent())){
                TransitionManger.getAvatarTransition().stop();
                BossBird.getInstance().decreaseHealth(Constants.AVATAR_MISSLE_DAMAGE_RATIO);
                AvatarShootingKeySettings.boom().play();
            }
        }
        return;
    });


    final boolean isShoot;
    final boolean isMove;
    final AvatarShootingKeySettings shootingKeySettings;
    final AvatarMoveKeySettings moveKeySettings;
    final int duration;
    final int cycleCount;
    final UniqueActions uniqueActions;

    AvatarStates(boolean isShoot, AvatarShootingKeySettings shootingKeySettings, boolean isMove, AvatarMoveKeySettings moveKeySettings, int duration, int count, UniqueActions uniqueActions) {
        this.isShoot = isShoot;
        this.isMove = isMove;
        this.shootingKeySettings = shootingKeySettings;
        this.moveKeySettings = moveKeySettings;
        this.duration = duration;
        this.cycleCount = count;
        this.uniqueActions = uniqueActions;
    }

    public AvatarMoveKeySettings getMoveKeySettings() {
        return moveKeySettings;
    }

    public AvatarShootingKeySettings getShootingKeySettings() {
        return shootingKeySettings;
    }

    public boolean isPossibleToMoveByUser() {
        return isMove;
    }

    public boolean isInShootingState() {
        return isShoot;
    }

    public int getDuration() {
        return duration;
    }

    public int getCycleCount() {
        return cycleCount;
    }

    public UniqueActions getUniqueActions() {
        return uniqueActions;
    }


}
