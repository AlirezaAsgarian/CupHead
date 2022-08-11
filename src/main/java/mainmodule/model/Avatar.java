package mainmodule.model;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import mainmodule.View.AvatarTransitions.AvatarTransition;
import mainmodule.View.Menus.MenuStack;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.pluginA.Controllers.AvatarControllers.KeyHandlingAvatar;
import mainmodule.model.pluginA.Enums.AvatarShootingKeySettings;
import mainmodule.model.pluginA.Enums.AvatarStates;
import mainmodule.model.pluginA.Enums.Bullets;
import mainmodule.model.pluginA.util.Constants;
import mainmodule.model.pluginA.util.SetConstants;
import mainmodule.model.pluginA.Controllers.CollisionController.CollisionController;
import mainmodule.util.Location;

import java.util.ArrayList;
import java.util.Map;

public  class Avatar extends Rectangle implements Imageable {
    /***
     * avatar has one instance one hashmap keyEvents contains moving key codes and boolean for activation , one hashmap with moving key codes and ArrayList of images for that key code
     * on hashmap shootingKeyEvents contains attack key codes and boolean for activation furthermore one hashmap shootingTimeLine for assert the rate of firing ,
     * one transition in purpose of when hits the bullet one function for moving and this implements with singleton design
     */
    static Avatar instance;
    int health;
    Image avatarImage;
    Bullets bullet;
    HealthBar healthBar;
    AvatarShootingKeySettings keyShootings;
    AvatarStates avatarStates = AvatarStates.NORMAL;
    ImageView iconOfShootingSetting = new ImageView();

    public Avatar(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
        health = Constants.AVATAR_HEALTH;
        this.healthBar = new HealthBar(this);
    }

    public static Avatar getInstance() {
        if (instance == null) {
            instance = new Avatar(20.0, 20.0, 109.0, 95.0);
        }
        return instance;
    }

    public static void setInstance(Avatar instance) {
        Avatar.instance = instance;
    }

    public Bullets getBullet() {
        return bullet;
    }

    public Map<KeyCode, BulletFactory> getBulletFactory() {
        return avatarStates.getShootingKeySettings().getBullets();
    }

    public Location getAvatarBulletLocation(Map.Entry bulletEntry){
       return new Location( (int) (this.getxCenter() + this.getStartCoordinateBullet().get(bulletEntry.getKey()).getKey()), (int) (this.getyCenter() + this.getStartCoordinateBullet().get(bulletEntry.getKey()).getValue()));
    }

    public void move(KeyCode keyCode) {
        switch (keyCode) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
    }

    public void checkForColllisonWithBossBird() {
        if (this.avatarStates != AvatarStates.BLINK && CollisionController.haveCollision(this.avatarImage, BossBird.getInstance().getCurrentImage(), this.getBoundsInParent(), BossBird.getInstance().getBoundsInParent())) {
            this.decreaseHealth(SetConstants.MINI_BOSS_BULLET_DAMAGE_RATIO);
            changeAvatarStates(AvatarStates.BLINK);
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

    public void setIconOfShootingSetting(ImageView iconOfShootingSetting) {
        this.iconOfShootingSetting = iconOfShootingSetting;
    }

    public void resetState() {
        for (Map.Entry<KeyCode, Boolean> key :
                this.avatarStates.getMoveKeySettings().getMoveKeyEvents().entrySet()) {
            this.avatarStates.getMoveKeySettings().getMoveKeyEvents().put(key.getKey(), false);
        }
    }

    public boolean canGetDamage() {
        return (avatarStates == AvatarStates.NORMAL || avatarStates == AvatarStates.NORMAL_BOMBING);
    }

    public void changeAvatarStates(AvatarStates avatarStates) {
        this.avatarStates = avatarStates;
        startUpdatedAvatarTransition();
    }

    protected void startUpdatedAvatarTransition() {
        AvatarTransition.getInstance().stop();
        AvatarTransition.setInstance(new AvatarTransition());
        AvatarTransition.getInstance().play();
    }

    public Map<KeyCode, ArrayList<ImagePattern>> getKeyMoves() {
        return avatarStates.getMoveKeySettings().getKeyMoves();
    }

    public void decreaseHealth(int value) {
        this.health -= value;
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

    public ImageView getIconOfShootingSetting() {
        return avatarStates.getShootingKeySettings().getImageView();
    }

    public AvatarStates getState() {
        return avatarStates;
    }

    public void updateState() {
        if (this.getHealth() < 0) {
            return;
        }
        this.avatarStates = updateAvatarStateAndReturnsIt(this.getState());
        startUpdatedAvatarTransition();
    }

    protected AvatarStates updateAvatarStateAndReturnsIt(AvatarStates avatarStates) {
        if (avatarStates == AvatarStates.BLINK || this.getState() == AvatarStates.MISSILE) {
            return AvatarStates.NORMAL;
        }
        return avatarStates;
    }

    public int getHealth() {
        return health;
    }

    public void setKeyOnPressedAndReleasedAvatar() {
        KeyHandlingAvatar keyHandlingAvatar = new KeyHandlingAvatar();
        keyHandlingAvatar.setKeyPressedSettings();
        keyHandlingAvatar.setKeyReleasedSettings();
    }

    public void avatarRequestFocus() {
        int index = MenuStack.getInstance().getTopMenu().getRoot().getChildren().indexOf(this);
        if (index != -1)
            MenuStack.getInstance().getTopMenu().getRoot().getChildren().get(index).requestFocus();
    }

    public Map<KeyCode, Boolean> getMoveKeyCodes() {
        return avatarStates.getMoveKeySettings().getMoveKeyEvents();
    }

    public Map<KeyCode, Long> getShootingTimeLine() {
        return avatarStates.getShootingKeySettings().getShootingTimeLine();
    }

    public Map<KeyCode, Pair<Integer, Integer>> getStartCoordinateBullet() {
        return avatarStates.getShootingKeySettings().getStartCoordinateBullet();
    }

    public String getKeySettingName() {
        return avatarStates.getShootingKeySettings().getName();
    }

    public Map<KeyCode, Boolean> getShootingKeyCodes() {
        return avatarStates.getShootingKeySettings().getShootingKeyEvents();
    }



    public boolean hasHealth() {
        return health >= 0;
    }

    @Override
    public void setImage(ImagePattern avatarImagePattern) {
        this.avatarImage = avatarImagePattern.getImage();
        this.setFill(avatarImagePattern);
    }

    @Override
    public Image getCurrentImage() {
        return avatarImage;
    }

    @Override
    public Bounds getBound() {
        return this.getBoundsInParent();
    }
}
