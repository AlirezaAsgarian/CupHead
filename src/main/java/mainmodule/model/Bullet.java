package mainmodule.model;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import mainmodule.Controllers.CollisionController;
import mainmodule.Enums.AvatarStates;
import mainmodule.Enums.BulletCollisionType;
import mainmodule.Enums.Bullets;
import mainmodule.Enums.MoveFuncs;
import mainmodule.model.BossBirds.BossBird;
import mainmodule.View.Menus.MenuStack;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Bullet extends Rectangle implements Imageable{
    /***
     * bullet has can be used for all bullets and has one movefuncs enum and or enemy(avatar or boss bird) and one transition for explosion
     */
    int speed;
    final int cycleCount;
    final int damageRatio;
    int health;
    final int duration;
    final ArrayList<Imageable> enemies;
    MoveFuncs moveFuncs;
    Transition explosion;
    ArrayList<ImagePattern> animation;
    Transition bulletTransition;
    Image currentImage;

    /***
     * @param v : left up x coordination
     * @param v1 : left up y coordination
     * @param bullet : an enum that contains speed and animation of bullet and function that gets cycleCount and have a movefuncs enum
     * @param enemy : an arraylist which contains the enemies of this bullet
     */
    public Bullet(double v, double v1, Bullets bullet, ArrayList<Imageable> enemy) {
        super(v, v1, bullet.getWidth(), bullet.getHeight());
        this.damageRatio = bullet.getDamageRatio();
        this.duration = bullet.getCycleDuration();
        this.enemies = enemy;
        MenuStack.getInstance().getTopMenu().getRoot().getChildren().add(this);
        this.moveFuncs = bullet.getMoveFuncs();
        this.speed = bullet.getSpeed();
        this.animation = bullet.getImagePattern();
        this.cycleCount = bullet.getCycleCount();
        this.setFill(bullet.getImagePattern().get(0));
    }


    /***
     * if this bullet has collision with its enemy or enemy bullet set isCollision of bullet true
     */
    public void checkForCollision() {
        checkForCollisionWithEnemy();
        checkForCollisionWithBullets();
    }

    public void decreaseHealth(int value) {
        this.health -= value;
    }

    public boolean hasHealth() {
        return this.health >= 0;
    }

    /***
     * if this bullet has collision with its enemy
     */
    public void checkForCollisionWithEnemy() {
        for (Imageable enemy : enemies) {
            if (CollisionController.haveCollision(this.getCurrentImage(),enemy.getCurrentImage(),this.getBoundsInParent(),enemy.getBound())) {
                if (enemy instanceof Avatar avatar && !avatar.canGetDamage()) return;
                startExplosionTransitionAndStopNormalTransition(BulletCollisionType.HIT_ENEMY);
                return;
            }
        }
    }

    private EventHandler createAfterExplosionAction(BulletCollisionType type) {
        switch (type) {
            case HIT_ENEMY -> {
                return event -> {
                    affectBulletDamageToEnemy();
                    removeBulletFromScreen();
                };
            }
            case HIT_BULLET -> {
                return event -> removeBulletFromScreen();
            }
            case NONE -> {
                return event -> {};
            }
        }
        return null;
    }

    private void affectBulletDamageToEnemy() {
        if (enemies.get(0) instanceof Avatar avatar) {
            avatar.changeAvatarStates(AvatarStates.BLINK);
            avatar.decreaseHealth(damageRatio);
        } else if (enemies.get(0) instanceof BossBird bossBird) {
            bossBird.decreaseHealth(damageRatio);
        }
    }

    private void removeBulletFromScreen() {
        MenuStack.getInstance().getTopMenu().getRoot().getChildren().remove(Bullet.this);
    }

    /***
     * if this bullet has collision with enemy bullet set isCollision of bullet true
     */
    public void checkForCollisionWithBullets() {
        for (Node node :
                MenuStack.getInstance().getTopMenu().getRoot().getChildren()) {
            if (node instanceof Bullet bullet && bullet != this && !bullet.getEnemies().equals(this.enemies)) {
                if (CollisionController.haveCollision(this.currentImage,bullet.getCurrentImage(),this.getBound(),bullet.getBound())) {
                    this.decreaseHealth(bullet.damageRatio);
                    if (this.hasHealth()) continue;
                    startExplosionTransitionAndStopNormalTransition(BulletCollisionType.HIT_BULLET);
                    return;
                }
            }
        }
    }

    private boolean haveCollisionWithForeignBullet(Bullet bullet) {
        return bullet.getBoundsInParent().intersects(this.getBoundsInParent());
    }

    private void startExplosionTransitionAndStopNormalTransition(BulletCollisionType type) {
        this.getBulletTransition().stop();
        this.getExplosion(type).play();
    }

    //getter and setters
    public MoveFuncs getMoveFuncs() {
        return moveFuncs;
    }


    /***
     * @Param : type of collision hit with enemy or bullet ?
     * @return : explosion transition of bullet
     */
    public Transition getExplosion(BulletCollisionType type) {
        explosion = new Transition() {
            {
                setCycleDuration(Duration.millis(Bullet.this.getMoveFuncs().getExplosionTime()));
                setCycleCount(1);
                setImage(Bullet.this.moveFuncs.getExplosion().get(0));
                setWidth(Bullet.this.moveFuncs.getExplosionSize());
                setHeight(Bullet.this.moveFuncs.getExplosionSize());
                setOnFinished(createAfterExplosionAction(type));
            }

            @Override
            protected void interpolate(double v) {
                int length = (int) Math.ceil(Bullet.this.moveFuncs.getExplosion().size() * v);
                if (length == 0) length = 1;
                Bullet.this.setFill(Bullet.this.getMoveFuncs().getExplosion().get(length - 1));
            }
        };
        return explosion;
    }

    public ArrayList<Imageable> getEnemies() {
        return enemies;
    }

    public ArrayList<ImagePattern> getAnimation() {
        return animation;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDuration() {
        return duration;
    }

    public int getCycleCount() {
        return cycleCount;
    }

    public void setBulletTransition(Transition bulletTransition) {
        this.bulletTransition = bulletTransition;
    }

    public Transition getBulletTransition() {
        return bulletTransition;
    }
    @Override
    public void setImage(ImagePattern imagePattern) {
        this.currentImage = imagePattern.getImage();
        this.setFill(imagePattern);
    }
    @Override
    public Image getCurrentImage() {
        return currentImage;
    }

    @Override
    public Bounds getBound() {
        return this.getBoundsInParent();
    }
}
