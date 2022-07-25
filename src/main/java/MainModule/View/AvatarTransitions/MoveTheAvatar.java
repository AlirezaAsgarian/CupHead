package MainModule.View.AvatarTransitions;

import MainModule.Enums.AvatarStates;
import MainModule.Main;
import MainModule.Model.Avatar;
import MainModule.Model.BossBird;
import MainModule.Model.Bullet;
import MainModule.Util.Constants;
import MainModule.View.BossBirdTransitions.BulletTransition;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoveTheAvatar extends Transition {

    Avatar avatar;
    AvatarStates startAvatarState;
    EventHandler<KeyEvent> keyEventEventHandler;
    static MoveTheAvatar instance;
    boolean isUniqueActionExecuteOnce;

    public static MoveTheAvatar getInstance(Avatar avatar) {
        if (instance == null) {
            instance = new MoveTheAvatar(avatar);
        }
        return instance;
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

    public MoveTheAvatar(Avatar avatar) {
        setCycleDuration(Duration.millis(avatar.getState().getDuration()));
        setCycleCount(avatar.getState().getCycleCount());
        this.startAvatarState = avatar.getState();
        this.avatar = avatar;
        this.isUniqueActionExecuteOnce = false;
        keyEventEventHandler = keyEvent -> {
            if (avatar.getKeyEvents().containsKey(keyEvent.getCode())) {
                avatar.getKeyEvents().put(keyEvent.getCode(), true);
            }
        };
    }

    @Override
    protected void interpolate(double v) {
        //check for collision with boss bird
        Avatar.getInstance().AvatarRequestFocus();
        Avatar.getInstance().checkForColllisonWithBossBird();
        if (startAvatarState != avatar.getState()) {
            v = 1;
            this.stop();
            if(avatar.getState() == AvatarStates.BLINK){
                new MoveTheAvatar(Avatar.getInstance()).play();
            }
            return;
        }
            Avatar.getInstance().getState().getUniqueActions().uniqueAction(v);
        if (avatar.getState().isMove()) {
            boolean isPressedKey = false;
            for (Map.Entry<KeyCode, Boolean> entry :
                    avatar.getKeyEvents().entrySet()) {
                if (entry.getValue()) {
                    updateFrame(entry.getKey(), v);
                    avatar.move(entry.getKey());
                    isPressedKey = true;
                }
            }
            if (!isPressedKey) {
                updateFrame(KeyCode.RIGHT, v);
            }
        }
        if (avatar.getState().isShoot()) {
            for (Map.Entry<KeyCode, Boolean> entry :
                    avatar.getShootingKeyEvents().entrySet()) {
                if (entry.getValue() && (Constants.getCurrentTime() - avatar.getShootingTimeLine().get(entry.getKey())) >= Constants.ATTACK_RATE) {
                    avatar.getShootingTimeLine().put(entry.getKey(), Constants.getCurrentTime());
                    Bullet bullet = new Bullet(avatar.getxCenter() + avatar.getStartCoordinateBullet().get(entry.getKey()).getKey(), avatar.getyCenter() + avatar.getStartCoordinateBullet().get(entry.getKey()).getValue(), avatar.getBullets().get(entry.getKey()), new ArrayList<>(List.of(BossBird.getInstance())));
                    bullet.setShoot(new BulletTransition(bullet, false));
                    bullet.getShoot().play();
                }
            }
        }
        if(Avatar.getInstance().getHealth() < 0){
            System.exit(0);
        }
        setOnFinished(actionEvent -> {
            avatar.updateState();
        });
    }


    public void updateFrame(KeyCode keyCode, double v) {
        int frame = (int) Math.ceil(v * avatar.getKeyMoves().get(keyCode).size());
        if (frame == 0) frame = 1;
        avatar.setFill(avatar.getKeyMoves().get(keyCode).get(frame - 1));
    }

}

