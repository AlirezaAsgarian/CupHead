package MainModule.Model;

import MainModule.Controllers.AvatarControllers.KeyHandlingAvatar;
import MainModule.Enums.AvatarShootingKeySettings;
import MainModule.Enums.AvatarStates;
import MainModule.Enums.Bullets;
import MainModule.Model.BossBirds.BossBird;
import MainModule.Util.Constants;
import MainModule.Util.SetConstants;
import MainModule.View.AvatarTransitions.AvatarTransition;
import MainModule.View.Menus.MenuStack;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
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
    HealthBar healthBar;

    AvatarShootingKeySettings keyShootings;
    AvatarStates avatarStates = AvatarStates.NORMAL;
    ImageView iconOfShootingSetting = new ImageView();

    public Bullets getBullet() {
        return bullet;
    }

    public HashMap<KeyCode, Bullets> getBullets() {
        return avatarStates.getShootingKeySettings().getBullets();
    }

    public Avatar(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
        iconOfShootingSetting.setY(10);
        iconOfShootingSetting.setX(10);
        health = Constants.AVATAR_HEALTH;
        this.healthBar = new HealthBar(this);
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


    public void moveRight(int speed) {
        if (this.getX() + this.getWidth() + speed >= Constants.Max_Width) return;
        this.setX(this.getX() + speed);
    }

    public void moveLeft(int speed) {
        if (this.getX() - speed <= 0) return;
        this.setX(this.getX() - speed);
    }

    public void checkForColllisonWithBossBird() {
        if (this.avatarStates != AvatarStates.BLINK && intersects(BossBird.getInstance().getLayoutBounds())) {
            this.decreaseHealth(SetConstants.MINI_BOSS_BULLET_DAMAGE_RATIO);
            this.avatarStates = AvatarStates.BLINK;
        }
    }

    private boolean haveCollision(Bounds layoutBounds) {
        if (Math.sqrt(Math.pow(this.getxCenter() - layoutBounds.getCenterX(), 2) + Math.pow(this.getyCenter() - layoutBounds.getCenterY(), 2)) <
                Math.sqrt(Math.pow(this.getWidth() / 2 + layoutBounds.getWidth() / 2 - BossBird.getInstance().getDistance_collision_x(), 2) + Math.pow(this.getHeight() / 2 + layoutBounds.getHeight() / 2 - BossBird.getInstance().getDistance_collision_y(), 2))) {
            return true;
        }
        return false;
    }


    public void setIconOfShootingSetting(ImageView iconOfShootingSetting) {
        this.iconOfShootingSetting = iconOfShootingSetting;
    }

    public void resetState() {
        for (Map.Entry<KeyCode, Boolean> key :
                this.avatarStates.getMoveKeySettings().getMoveKeyEvents().entrySet()) {
            this.avatarStates.getMoveKeySettings().getMoveKeyEvents().put(key.getKey(), false);
        }
        ;
    }

    public boolean canGetDamage() {
        return (avatarStates == AvatarStates.NORMAL || avatarStates == AvatarStates.NORMAL_BOMBING);
    }

    //getter and setters


    public void changeAvatarStates(AvatarStates avatarStates) {
        this.avatarStates = avatarStates;
        startUpdatedAvatarTransition();
    }

    private void startUpdatedAvatarTransition() {
        AvatarTransition.getInstance().stop();
        AvatarTransition.setInstance(new AvatarTransition());
        AvatarTransition.getInstance().play();
    }

    public HashMap<KeyCode, ArrayList<ImagePattern>> getKeyMoves() {
        return avatarStates.getMoveKeySettings().getKeyMoves();
    }

    public void decreaseHealth(int value) {
        this.health -= value;
    }


    public HashMap<KeyCode, Boolean> getMoveKeyCodes() {
        return avatarStates.getMoveKeySettings().getMoveKeyEvents();
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

    public HashMap<KeyCode, Boolean> getShootingKeyCodes() {
        return avatarStates.getShootingKeySettings().getShootingKeyEvents();
    }

    public AvatarStates getState() {
        return avatarStates;
    }

    public void updateState() {
        if (this.getHealth() < 0) {
            return;
        }
        this.avatarStates = updateAvatarStateAndReturnsIt(this.avatarStates);
        startUpdatedAvatarTransition();
    }

    private AvatarStates updateAvatarStateAndReturnsIt(AvatarStates avatarStates) {
        if (avatarStates == AvatarStates.BLINK) {
            return AvatarStates.NORMAL;
        } else if (this.avatarStates == AvatarStates.MISSILE) {
            return AvatarStates.NORMAL;
        }
        return avatarStates;
    }


    private void setFill(Image image) {
        this.setFill(image);
    }

    public int getHealth() {
        return health;
    }

    public void setKeyOnPressedAndReleasedAvatar(Avatar this) {
        KeyHandlingAvatar keyHandlingAvatar = new KeyHandlingAvatar();
        keyHandlingAvatar.setKeyPressedSettings();
        keyHandlingAvatar.setKeyReleasedSettings();
    }

    public void AvatarRequestFocus() {
        int index = MenuStack.getInstance().getTopMenu().getRoot().getChildren().indexOf(this);
        if (index != -1)
            MenuStack.getInstance().getTopMenu().getRoot().getChildren().get(index).requestFocus();
    }


    public static void setInstance(Avatar instance) {
        Avatar.instance = instance;
    }

    public boolean hasHealth() {
        return health >= 0;
    }
}
