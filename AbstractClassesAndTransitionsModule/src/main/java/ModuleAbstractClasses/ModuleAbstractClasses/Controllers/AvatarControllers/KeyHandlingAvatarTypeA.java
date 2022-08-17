package ModuleAbstractClasses.ModuleAbstractClasses.Controllers.AvatarControllers;


import ModuleAbstractClasses.ModuleAbstractClasses.Enums.KeyEventType;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.Avatar;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.AvatarEnums.AvatarShootingKeySettings;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.AvatarEnums.AvatarStates;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.Constants;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.ImageHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Map;

public class KeyHandlingAvatarTypeA extends KeyHandlingAvatar {

    @Override
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

    private void startMissileShot(KeyEvent keyEvent) {
        updateLastMissileShotTime(keyEvent);
        Avatar.getInstance().changeAvatarStates(AvatarStates.MISSILE);
    }

    private void updateLastMissileShotTime(KeyEvent keyEvent) {
        AvatarShootingKeySettings.MISSILE.getShootingTimeLine().put(keyEvent.getCode(), Constants.getCurrentTime());
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

    private boolean isAvatarShootingKeysPressed(KeyCode keyCode) {
        return Avatar.getInstance().getShootingKeyCodes().containsKey(keyCode);
    }

    private boolean isAvatarMovingKeysPressed(KeyCode pressedKeyCode) {
        return Avatar.getInstance().getMoveKeyCodes().containsKey(pressedKeyCode);
    }

    private boolean isSpacePressed(KeyCode pressedKeyCode) {
        return pressedKeyCode == KeyCode.SPACE;
    }

    private boolean isTabPressed(KeyCode pressedKeyCode) {
        return pressedKeyCode == KeyCode.TAB;
    }

    private boolean isTimeBetweenTwoConsecutiveMissilePassed(KeyEvent keyEvent) {
        return (Constants.getCurrentTime() - AvatarShootingKeySettings.MISSILE.getShootingTimeLine().get(keyEvent.getCode())) >= Constants.AVATAR_MISSLE_STATE_ATTACK_RATE;
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

    private void updateAvatarKeyEvent(Map<KeyCode, Boolean> KeyEvents, KeyCode pressedKeyCode, boolean pressedOrRelease) {
        KeyEvents.put(pressedKeyCode, pressedOrRelease);
    }

    @Override
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
}
