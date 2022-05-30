package MainModule.Model;

import MainModule.Enums.AvatarShootingKeySettings;
import MainModule.Enums.AvatarStates;
import MainModule.Enums.Bullets;
import MainModule.Util.Constants;
import MainModule.View.AvatarTransitions.MoveTheAvatar;
import MainModule.View.GameSceneView;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.*;

public class Avatar extends Rectangle {
    /***
     * avatar has one instance one hashmap keyEvents contains moving key codes and boolean for activation , one hashmap with moving key codes and ArrayList of images for that key code
     * on hashmap shootingKeyEvents contains attack key codes and boolean for activation furthermore one hashmap shootingTimeLine for assert the rate of firing ,
     * one transition in purpose of when hits the bullet one function for moving and this implements with singleton design
     */
    private static Avatar instance;
    int health;
    Bullets bullet;

    AvatarShootingKeySettings keyShootings;
    AvatarStates avatarStates = AvatarStates.NORMAL;
    ImageView iconOfShootingSetting = new ImageView();
    Transition hittTheBullet;

    public Bullets getBullet() {
        return bullet;
    }

    public HashMap<KeyCode, Bullets> getBullets() {
        return avatarStates.getShootingKeySettings().getBullets();
    }

    public Avatar(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
        GameSceneView.anchorPane.getChildren().add(iconOfShootingSetting);
        iconOfShootingSetting.setY(10); iconOfShootingSetting.setX(10);
        hittTheBullet = new Transition() {
            {
                setCycleDuration(Duration.millis(200));
                setCycleCount(5);
            }

            @Override
            protected void interpolate(double v) {
                if (v > 0.5) {
                    Avatar.this.setOpacity(1.0);
                } else {
                    Avatar.this.setOpacity(0.3);
                }
            }
        };
        health = Constants.AVATAR_HEALTH;
    }

    public static Avatar getInstance() {
        if (instance == null) {
            instance = new Avatar(20.0, 20.0, 109.0, 95.0);
        }
        return instance;
    }

    public void move(KeyCode keyCode) {
        switch (keyCode) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
    }

    public void moveUp() {
        if (this.getY() - Constants.AVATAR_SPEED <= 0) return;
        this.setY(this.getY() - Constants.AVATAR_SPEED);
    }

    public void moveDown() {
        if (this.getY() + this.getHeight() + Constants.AVATAR_SPEED >= Constants.Max_Height) return;
        this.setY(this.getY() + Constants.AVATAR_SPEED);
    }

    public void moveRight() {
        if (this.getX() + this.getWidth() + Constants.AVATAR_SPEED >= Constants.Max_Width) return;
        this.setX(this.getX() + Constants.AVATAR_SPEED);
    }

    public void moveLeft() {
        if (this.getX() - Constants.AVATAR_SPEED <= 0) return;
        this.setX(this.getX() - Constants.AVATAR_SPEED);
    }


    public void setIconOfShootingSetting(ImageView iconOfShootingSetting) {
        this.iconOfShootingSetting = iconOfShootingSetting;
    }


    //getter and setters
    public Transition getHittTheBullet() {
        return hittTheBullet;
    }


    public void setAvatarStates(AvatarStates avatarStates) {
        this.avatarStates = avatarStates;
        System.out.println(avatarStates.toString());
        iconOfShootingSetting.setImage(avatarStates.getShootingKeySettings().getImageView().getImage());
    }

    public HashMap<KeyCode, ArrayList<ImagePattern>> getKeyMoves() {
        return avatarStates.getMoveKeySettings().getKeyMoves();
    }

    public void decreaseHealth(int value) {
        this.health -= value;
    }


    public HashMap<KeyCode, Boolean> getKeyEvents() {
        return avatarStates.getMoveKeySettings().getKeyEvents();
    }

    public HashMap<KeyCode, Long> getShootingTimeLine() {
        return avatarStates.getShootingKeySettings().getShootingTimeLine();
    }

    /***
     * @return returns center x of this rectangle
     */
    public double getxCenter() {
        return this.getX() + this.getWidth() / 2;
    }

    /***
     * @return returns center y of this rectangle
     */
    public double getyCenter() {
        return (this.getY() + this.getHeight() / 2);
    }

    public HashMap<KeyCode, Pair<Integer, Integer>> getStartCoordinateBullet() {
        return avatarStates.getShootingKeySettings().getStartCoordinateBullet();
    }

    private void swap(AvatarShootingKeySettings[] a, int i, int j) {
        AvatarShootingKeySettings t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    public String getKeySettingName() {
        return avatarStates.getShootingKeySettings().getName();
    }

    public ImageView getIconOfShootingSetting() {
        return avatarStates.getShootingKeySettings().getImageView();
    }

    public HashMap<KeyCode, Boolean> getShootingKeyEvents() {
        return avatarStates.getShootingKeySettings().getShootingKeyEvents();
    }

    public AvatarStates getState() {
        return avatarStates;
    }

    public void updateState() {
        if(this.avatarStates == AvatarStates.NORMAL){
            new MoveTheAvatar(this).play();
        }
        if(this.avatarStates == AvatarStates.NORMAL_BOMBING) {
            new MoveTheAvatar(this).play();
        }
    }
}
