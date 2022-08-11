package mainmodule.View.AvatarTransitions;

import mainmodule.model.pluginA.Controllers.AvatarControllers.AvatarTransitionController;
import mainmodule.model.pluginA.Controllers.gameControllers.GameController;
import mainmodule.model.pluginA.Enums.AvatarStates;
import mainmodule.model.pluginA.Enums.BulletTransitionFactory;
import mainmodule.model.Avatar;
import mainmodule.model.TransitionManager;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class AvatarTransition extends Transition implements BulletTransitionFactory {

    AvatarStates startAvatarState;
    EventHandler<KeyEvent> keyEventEventHandler;
    static AvatarTransition instance;
    boolean isUniqueActionExecuteOnce;

    public static AvatarTransition getInstance() {
        if (instance == null) {
            instance = new AvatarTransition();
        }
        return instance;
    }

    public static void setInstance(AvatarTransition instance) {
        AvatarTransition.instance = instance;
    }



    public void setUniqueActionExecuteOnce(boolean uniqueActionExecuteOnce) {
        isUniqueActionExecuteOnce = uniqueActionExecuteOnce;
    }

    public boolean isUniqueActionExecuteOnce() {
        return isUniqueActionExecuteOnce;
    }

    public AvatarTransition() {
        setCycleDuration(Duration.millis(Avatar.getInstance().getState().getDuration()));
        setCycleCount(Avatar.getInstance().getState().getCycleCount());
        this.startAvatarState = Avatar.getInstance().getState();
        this.isUniqueActionExecuteOnce = false;
        keyEventEventHandler = keyEvent -> {
            if (Avatar.getInstance().getMoveKeyCodes().containsKey(keyEvent.getCode())) {
                Avatar.getInstance().getMoveKeyCodes().put(keyEvent.getCode(), true);
            }
        };
        this.setOnFinished(actionEvent -> {
            Avatar.getInstance().updateState();
        });
        TransitionManager.setAvatarTransition(this);
        AvatarTransitionController.setAvatarTransition(this);
    }

    @Override
    protected void interpolate(double v) {
        Avatar.getInstance().avatarRequestFocus();
        AvatarTransitionController.interpolateAvatar(v);
        if (!Avatar.getInstance().hasHealth()) {
             GameController.endGame();
        }
    }


    public AvatarStates getStartAvatarState() {
        return startAvatarState;
    }

    public void updateFrame(KeyCode keyCode, double v) {
        int frame = (int) Math.ceil(v * Avatar.getInstance().getKeyMoves().get(keyCode).size());
        if (frame == 0) frame = 1;
        Avatar.getInstance().setImage(Avatar.getInstance().getKeyMoves().get(keyCode).get(frame - 1));
    }

}

