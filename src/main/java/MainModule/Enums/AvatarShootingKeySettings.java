package MainModule.Enums;

import MainModule.Main;
import MainModule.Model.Avatar;
import MainModule.Util.Constants;
import MainModule.View.GameSceneView;
import MainModule.View.Menus.MenuStack;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public enum AvatarShootingKeySettings {
    NORMAL_BULLETS(new HashMap<>(Map.of(KeyCode.D, false, KeyCode.S, false, KeyCode.W, false, KeyCode.A, false)),
            new HashMap<>(Map.of(KeyCode.D, Bullets.AVATAR_BULLET, KeyCode.S, Bullets.AVATAR_BOMB, KeyCode.W, Bullets.AVATAR_TIR_HAVAII, KeyCode.A, Bullets.AVATAR_BACK)),
            new HashMap<>(Map.of(KeyCode.D, Constants.getCurrentTime(), KeyCode.W, Constants.getCurrentTime(), KeyCode.S, Constants.getCurrentTime(), KeyCode.A, Constants.getCurrentTime())),
            new HashMap<>(Map.of(KeyCode.D, new Pair<>(Constants.AVATAR_BULLET_DISTANCE_FROM_CENTER, 0), KeyCode.W, new Pair<>(0, -Constants.AVATAR_BULLET_DISTANCE_FROM_CENTER),
                    KeyCode.S, new Pair<>(0, Constants.AVATAR_BULLET_DISTANCE_FROM_CENTER), KeyCode.A, new Pair<>(-Constants.AVATAR_BACK_DISTANCE_FROM_CENTER, 0))), "normal", new ImageView(new Image(Main.class.getResource("images/icons/bulletIcon.png").toExternalForm()))),
    AVATAR_BOMB(new HashMap<>(Map.of(KeyCode.S, false)),
            new HashMap<>(Map.of(KeyCode.S, Bullets.AVATAR_MISSLE)),
            new HashMap<>(Map.of(KeyCode.S, Constants.getCurrentTime())),
            new HashMap<>(Map.of(KeyCode.S, new Pair<>(0, Constants.AVATAR_BULLET_DISTANCE_FROM_CENTER))), "missle", new ImageView(new Image(Main.class.getResource("images/icons/missleIcon.png").toExternalForm()))),
    MISSLE(new HashMap<>(Map.of(KeyCode.SPACE, false)),
            new HashMap<>(Map.of(KeyCode.SPACE, Bullets.AVATAR_MISSLE)),
            new HashMap<>(Map.of(KeyCode.SPACE, Constants.getCurrentTime())),
            new HashMap<>(Map.of(KeyCode.SPACE, new Pair<>(0, Constants.AVATAR_BULLET_DISTANCE_FROM_CENTER))), "missle", new ImageView(new Image(Main.class.getResource("images/icons/missleIcon.png").toExternalForm())));


    AvatarShootingKeySettings(HashMap<KeyCode, Boolean> shootingKeyEvents, HashMap<KeyCode, Bullets> bullets, HashMap<KeyCode, Long> shootingTimeLine, HashMap<KeyCode, Pair<Integer, Integer>> startCoordinateBullet, String name, ImageView imageView) {
        this.shootingKeyEvents = shootingKeyEvents;
        this.bullets = bullets;
        this.shootingTimeLine = shootingTimeLine;
        this.startCoordinateBullet = startCoordinateBullet;
        this.name = name;
        this.imageView = imageView;
    }

    static ArrayList<ImageView> explosionOfMissleCupHead = new ArrayList<>() {{
        for (int i = 1; i <= 11; i++) {
            add(new ImageView(new Image(Main.class.getResource("cuphead_frames/frames/Plane/Mini/Super/Bomb/Boom/00" + i + ".png").toExternalForm())));
            this.get(i - 1).setX(Avatar.getInstance().getX());
            this.get(i - 1).setY(Avatar.getInstance().getY());
        }
    }};
    static ImageView boomImageView = new ImageView();

    /***
     * @return this function returns transition of the boom in end of the missle fire
     */
    public static Transition boom() {
        resetBoomImageView();
        Avatar.getInstance().setOpacity(0);
        MenuStack.getInstance().getTopMenu().getRoot().getChildren().add(boomImageView);
        return new Transition() {
            {
                setCycleCount(1);
                setCycleDuration(Duration.millis(2000));
            }
            @Override
            protected void interpolate(double v) {
                int frame = (int) Math.ceil(v * AvatarShootingKeySettings.getExplosionOfMissleCupHead().size());
                if (frame == 0) frame = 1;
                updateImageView(AvatarShootingKeySettings.explosionOfMissleCupHead.get(frame - 1).getImage(),frame);
                setOnFinished(actionEvent -> {
                    Avatar.getInstance().moveLeft(Constants.AVATAR_GET_BACK_AFTER_BOOM);
                    Avatar.getInstance().setOpacity(1);
                    MenuStack.getInstance().getTopMenu().getRoot().getChildren().remove(boomImageView);
                    Avatar.getInstance().setAvatarStates(AvatarStates.BLINK);
                });
            }
        };
    }

    private static void resetBoomImageView() {
        boomImageView.setImage(AvatarShootingKeySettings.getExplosionOfMissleCupHead().get(0).getImage());
        boomImageView.setY(Avatar.getInstance().getY());
        boomImageView.setX(Avatar.getInstance().getX());
    }
    private static void updateImageView(Image image,int frame){
        MenuStack.getInstance().getTopMenu().getRoot().getChildren().remove(boomImageView);
        boomImageView.setImage(AvatarShootingKeySettings.explosionOfMissleCupHead.get(frame - 1).getImage());
        MenuStack.getInstance().getTopMenu().getRoot().getChildren().add(boomImageView);
    }

    HashMap<KeyCode, Boolean> shootingKeyEvents;
    HashMap<KeyCode, Bullets> bullets;
    HashMap<KeyCode, Long> shootingTimeLine;
    HashMap<KeyCode, Pair<Integer, Integer>> startCoordinateBullet;
    final String name;
    final ImageView imageView;


    public HashMap<KeyCode, Boolean> getShootingKeyEvents() {
        return shootingKeyEvents;
    }

    public HashMap<KeyCode, Bullets> getBullets() {
        return bullets;
    }

    public HashMap<KeyCode, Long> getShootingTimeLine() {
        return shootingTimeLine;
    }

    public HashMap<KeyCode, Pair<Integer, Integer>> getStartCoordinateBullet() {
        return startCoordinateBullet;
    }

    public String getName() {
        return name;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public static AvatarShootingKeySettings getNextAvatarKey(String name) {
        for (AvatarShootingKeySettings shootingKey :
                AvatarShootingKeySettings.values()) {
            if (!name.equals(shootingKey.name)) return shootingKey;
        }
        //never
        return null;
    }

    public static ArrayList<ImageView> getExplosionOfMissleCupHead() {
        return explosionOfMissleCupHead;
    }
}
