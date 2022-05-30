package MainModule.View.AvatarTransitions;

import MainModule.Enums.MoveFuncs;
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
    EventHandler<KeyEvent> keyEventEventHandler;
    static MoveTheAvatar instance;
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

    public MoveTheAvatar(Avatar avatar) {
        setCycleDuration(Duration.millis(avatar.getState().getDuration()));
        setCycleCount(1);
        this.avatar = avatar;
        keyEventEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (avatar.getKeyEvents().containsKey(keyEvent.getCode())) {
                    avatar.getKeyEvents().put(keyEvent.getCode(), true);
                }
            }
        };
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.ceil(v * 4);
        if(avatar.getState().isMove()) {
            boolean isPressedKey = false;
            for (Map.Entry<KeyCode, Boolean> entry :
                    avatar.getKeyEvents().entrySet()) {
                if (entry.getValue()) {
                    updateFrame(entry.getKey(), frame);
                    avatar.move(entry.getKey());
                    isPressedKey = true;
                }
            }
            if (!isPressedKey) {
                updateFrame(KeyCode.RIGHT, frame);
            }
        }
        if(avatar.getState().isShoot()) {
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
        setOnFinished(actionEvent -> {
            avatar.updateState();
        });
    }


    public void updateFrame(KeyCode keyCode, int frame) {
        if (frame == 0) frame = 1;
        avatar.setFill(avatar.getKeyMoves().get(keyCode).get(frame - 1));
    }

}

