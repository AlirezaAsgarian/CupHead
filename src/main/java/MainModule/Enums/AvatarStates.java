package MainModule.Enums;

import MainModule.Util.Constants;

public enum AvatarStates {
//    BLIND,
    NORMAL(true, AvatarShootingKeySettings.NORMAL_BULLETS,true,AvatarMoveKeySettings.NORMAL, Constants.AVATAR_NORMAL_STATE_DURATION),
    NORMAL_BOMBING(true,AvatarShootingKeySettings.AVATAR_BOMB,true,AvatarMoveKeySettings.NORMAL, Constants.AVATAR_NORMAL_BOMBING_STATE_DURATION);
//    LITTLE,
//    MISSLE;


    final boolean isShoot;
    final boolean isMove;
    final AvatarShootingKeySettings shootingKeySettings;
    final AvatarMoveKeySettings moveKeySettings;
    final int duration;
    AvatarStates(boolean isShoot, AvatarShootingKeySettings shootingKeySettings, boolean isMove, AvatarMoveKeySettings moveKeySettings, int duration) {
        this.isShoot = isShoot;
        this.isMove = isMove;
        this.shootingKeySettings = shootingKeySettings;
        this.moveKeySettings = moveKeySettings;
        this.duration = duration;
    }

    public AvatarMoveKeySettings getMoveKeySettings() {
        return moveKeySettings;
    }

    public AvatarShootingKeySettings getShootingKeySettings() {
        return shootingKeySettings;
    }

    public boolean isMove() {
        return isMove;
    }

    public boolean isShoot() {
        return isShoot;
    }

    public int getDuration() {
        return duration;
    }
}
