package MainModule.Controllers;

import MainModule.Model.Avatar;
import MainModule.Util.Constants;
import MainModule.View.AvatarTransitions.AvatarTransition;
import MainModule.View.Menus.MenuStack;
import javafx.scene.input.KeyCode;

import java.util.Map;

public class AvatarTransitionController {
    private static AvatarTransition avatarTransition;

    public static void setAvatarTransition(AvatarTransition avatarTransition) {
        AvatarTransitionController.avatarTransition = avatarTransition;
    }

    public synchronized static void interpolateAvatar(double v) {
        //check for collision with boss bird
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
        for (Map.Entry<KeyCode, Boolean> entry :
                Avatar.getInstance().getShootingKeyCodes().entrySet()) {
            if (entry.getValue() && (Constants.getCurrentTime() - Avatar.getInstance().getShootingTimeLine().get(entry.getKey())) >= Constants.ATTACK_RATE) {
                Avatar.getInstance().getShootingTimeLine().put(entry.getKey(), Constants.getCurrentTime());
                avatarTransition.createBulletTransition(Avatar.getInstance().getBullets().get(entry.getKey()), (int) (Avatar.getInstance().getxCenter() + Avatar.getInstance().getStartCoordinateBullet().get(entry.getKey()).getKey()), (int) (Avatar.getInstance().getyCenter() + Avatar.getInstance().getStartCoordinateBullet().get(entry.getKey()).getValue()))
                        .play();
            }
        }
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
