package mainmodule.Controllers.AvatarControllers;

import mainmodule.Enums.AvatarShootingKeySettings;
import mainmodule.Enums.AvatarStates;
import mainmodule.Enums.KeyEventType;
import mainmodule.model.Avatar;
import mainmodule.model.ImageHandler;
import mainmodule.util.Constants;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Map;

public class KeyHandlingAvatar {

    public void setKeyPressedSettings() {
        Avatar.getInstance().setOnKeyPressed(keyEvent -> {
            KeyEventType pressedKeyType = returnEventKeyType(keyEvent.getCode());
            switch (pressedKeyType) {
                case MOVING -> updateAvatarKeyEvent(Avatar.getInstance().getMoveKeyCodes(), keyEvent.getCode(), true);
                case SHOOTING ->
                        updateAvatarKeyEvent(Avatar.getInstance().getShootingKeyCodes(), keyEvent.getCode(), true);
                case CHANGE_SHOOTING_STYLE -> {
                    changeAvatarShootingKeyCodes();
                    updateAvatarShootingIcon();
                }
                case SHOT_MISSILE -> {
                    if (isTimeBetweenTwoConsecutiveMissilePassed(keyEvent)) {
                        startMissileShot(keyEvent);
                    }
                }
            }
        });
    }

    public void setKeyReleasedSettings() {
        Avatar.getInstance().setOnKeyReleased(keyEvent -> {
            KeyEventType releasedKeyType = returnEventKeyType(keyEvent.getCode());
            switch (releasedKeyType) {
                case MOVING -> updateAvatarKeyEvent(Avatar.getInstance().getMoveKeyCodes(), keyEvent.getCode(), false);
                case SHOOTING ->
                        updateAvatarKeyEvent(Avatar.getInstance().getShootingKeyCodes(), keyEvent.getCode(), false);
            }
        });
    }

    private void startMissileShot(KeyEvent keyEvent) {
        updateLastMissileShotTime(keyEvent);
        Avatar.getInstance().changeAvatarStates(AvatarStates.MISSILE);
    }

    private KeyEventType returnEventKeyType(KeyCode pressedKeyCode) {
        if (isAvatarMovingKeysPressed(pressedKeyCode)) {
            return KeyEventType.MOVING;
        } else if (isAvatarShootingKeysPressed(pressedKeyCode)) {
            return KeyEventType.SHOOTING;
        } else if (isTabPressed(pressedKeyCode)) {
            return KeyEventType.CHANGE_SHOOTING_STYLE;
        } else if (isSpacePressed(pressedKeyCode)) {
            return KeyEventType.SHOT_MISSILE;
        }
        return KeyEventType.NONE;
    }

    private void updateLastMissileShotTime(KeyEvent keyEvent) {
        AvatarShootingKeySettings.MISSLE.getShootingTimeLine().put(keyEvent.getCode(), Constants.getCurrentTime());
    }

    private boolean isTimeBetweenTwoConsecutiveMissilePassed(KeyEvent keyEvent) {
        return (Constants.getCurrentTime() - AvatarShootingKeySettings.MISSLE.getShootingTimeLine().get(keyEvent.getCode())) >= Constants.AVATAR_MISSLE_STATE_ATTACK_RATE;
    }

    private boolean isAvatarShootingKeysPressed(KeyCode keyCode) {
        return Avatar.getInstance().getShootingKeyCodes().containsKey(keyCode);
    }

    private boolean isAvatarMovingKeysPressed(KeyCode pressedKeyCode) {
        return Avatar.getInstance().getMoveKeyCodes().containsKey(pressedKeyCode);
    }

    private void changeAvatarShootingKeyCodes() {
        if (Avatar.getInstance().getState() == AvatarStates.NORMAL) {
            Avatar.getInstance().changeAvatarStates(AvatarStates.NORMAL_BOMBING);
        } else if (Avatar.getInstance().getState() == AvatarStates.NORMAL_BOMBING) {
            Avatar.getInstance().changeAvatarStates(AvatarStates.NORMAL);
        }
    }

    private void updateAvatarShootingIcon() {
        Avatar.getInstance().getIconOfShootingSetting().setImage(Avatar.getInstance().getState().getShootingKeySettings().getImageView().getImage());
        ImageHandler.updateIconOfShooting(Avatar.getInstance().getIconOfShootingSetting());
    }

    private boolean isSpacePressed(KeyCode pressedKeyCode) {
        return pressedKeyCode == KeyCode.SPACE;
    }

    private boolean isTabPressed(KeyCode pressedKeyCode) {
        return pressedKeyCode == KeyCode.TAB;
    }

    private void updateAvatarKeyEvent(Map<KeyCode, Boolean> KeyEvents, KeyCode pressedKeyCode, boolean pressedOrRelease) {
        KeyEvents.put(pressedKeyCode, pressedOrRelease);
    }
}
