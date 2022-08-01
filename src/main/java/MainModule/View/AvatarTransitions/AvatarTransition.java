package MainModule.View.AvatarTransitions;

import MainModule.Controllers.AvatarTransitionController;
import MainModule.Enums.AvatarStates;
import MainModule.Enums.BulletTransitionFactory;
import MainModule.Main;
import MainModule.Model.Avatar;
import MainModule.Model.TransitionManger;
import MainModule.View.Menus.MenuStack;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;

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

    ArrayList<ImagePattern> moveUpImages = new ArrayList<>() {
        {
            for (int i = 0; i <= 3; i++) {
                URL s = Main.class.getResource("cuphead_frames/frames/Plane/Idle/mugman_plane_idle_down_0001-00" + i + ".png");
                ImagePattern e = new ImagePattern(new Image(s.toExternalForm()));
                add(e);
            }
        }
    };

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
        TransitionManger.setAvatarTransition(this);
        AvatarTransitionController.setAvatarTransition(this);
    }

    @Override
    protected void interpolate(double v) {
        Avatar.getInstance().AvatarRequestFocus();
        AvatarTransitionController.interpolateAvatar(v);
        if (!Avatar.getInstance().hasHealth()) {
            MenuStack.getInstance().getCurrentGame().killGame();
        }
    }

    public AvatarStates getStartAvatarState() {
        return startAvatarState;
    }

    public void updateFrame(KeyCode keyCode, double v) {
        int frame = (int) Math.ceil(v * Avatar.getInstance().getKeyMoves().get(keyCode).size());
        if (frame == 0) frame = 1;
        Avatar.getInstance().setFill(Avatar.getInstance().getKeyMoves().get(keyCode).get(frame - 1));
    }

}

