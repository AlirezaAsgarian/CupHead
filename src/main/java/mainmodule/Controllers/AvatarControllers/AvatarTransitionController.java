package mainmodule.Controllers.AvatarControllers;

import mainmodule.model.Avatar;
import mainmodule.util.Constants;
import mainmodule.View.AvatarTransitions.AvatarTransition;
import javafx.scene.input.KeyCode;

import java.util.Map;

public class AvatarTransitionController {
    private static AvatarTransition avatarTransition;

    public static void setAvatarTransition(AvatarTransition avatarTransition) {
        AvatarTransitionController.avatarTransition = avatarTransition;
    }

    public synchronized static void interpolateAvatar(double v) {
        Avatar.getInstance().checkForColllisonWithBossBird();
        Avatar.getInstance().getState().getUniqueActions().uniqueAction(v);
        if (Avatar.getInstance().getState().isPossibleToMoveByUser()) {
            moveAvatar(v);
        }
        if (Avatar.getInstance().getState().isInShootingState()) {
            startBulletTransition();
        }
    }

    private static void startBulletTransition() {
        for (Map.Entry<KeyCode, Boolean> bulletEntry :
                Avatar.getInstance().getShootingKeyCodes().entrySet()) {
            if (bulletEntry.getValue() && isTimeBetweenTwoConsecutiveAttackPassed(bulletEntry)) {
                updateLastTimeShooting(bulletEntry);
                avatarTransition.createBulletTransition(Avatar.getInstance().getBulletFactory().get(bulletEntry.getKey()),Avatar.getInstance().getAvatarBulletLocation(bulletEntry)).play();
            }
        }
    }

    private static void updateLastTimeShooting(Map.Entry<KeyCode, Boolean> bulletEntry) {
        Avatar.getInstance().getShootingTimeLine().put(bulletEntry.getKey(), Constants.getCurrentTime());
    }

    private static boolean isTimeBetweenTwoConsecutiveAttackPassed(Map.Entry<KeyCode, Boolean> entry) {
        return (Constants.getCurrentTime() - Avatar.getInstance().getShootingTimeLine().get(entry.getKey())) >= Constants.ATTACK_RATE;
    }

    private static void moveAvatar(double v) {
        boolean isPressedKey = false;
        for (Map.Entry<KeyCode, Boolean> entry :
                Avatar.getInstance().getMoveKeyCodes().entrySet()) {
            if (entry.getValue()) {
                avatarTransition.updateFrame(entry.getKey(), v);
                Avatar.getInstance().move(entry.getKey());
                isPressedKey = true;
            }
        }
        if (!isPressedKey) {
            avatarTransition.updateFrame(KeyCode.RIGHT, v);
        }
    }
}
