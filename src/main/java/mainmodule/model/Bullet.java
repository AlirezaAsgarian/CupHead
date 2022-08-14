package mainmodule.model;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import mainmodule.model.pluginA.Controllers.CollisionController.CollisionController;
import mainmodule.model.pluginA.Enums.AvatarStates;
import mainmodule.model.pluginA.Enums.BulletCollisionType;
import mainmodule.model.pluginA.Enums.MoveFuncs;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.View.Menus.MenuStack;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.slf4j.Logger;

public class Bullet extends Rectangle implements Imageable{
    /***
     * bullet has can be used for all bullets and has one movefuncs enum and or enemy(avatar or boss bird) and one transition for explosion
     */
    static Logger logger = LoggerFactory.getLogger(Bullet.class);
    int speed;
    final int cycleCount;
    final int damageRatio;
    int health;
    final int duration;
    List<Imageable> enemies;
    MoveFuncs moveFuncs;
    boolean isFlexible;
    boolean isExploded;
    Transition explosion;
    List<ImagePattern> animationImagePatterns;
    Transition bulletTransition;
    Image currentImage;

    /***
     * @param v : left up x coordination
     * @param v1 : left up y coordination
     */

    public Bullet(double v,double v1,BulletFactory bulletFactory) {
        super(v, v1, bulletFactory.getBulletWidth(), bulletFactory.getBulletHeight());
        this.damageRatio = bulletFactory.getDamageRatio();
        this.duration = bulletFactory.getAnimationDuration();
        this.enemies = bulletFactory.getBulletEnemies();
        MenuStack.getInstance().addNodeToCurrentMenuChildrens(this);
        this.moveFuncs = bulletFactory.getBulletMoveFunction();
        this.speed = bulletFactory.getBulletSpeed();
        this.animationImagePatterns = bulletFactory.getBulletAnimationImagePatterns();
        this.cycleCount = bulletFactory.getTransitionCycleCount();
        this.isFlexible = bulletFactory.isFlexible();
        this.health = bulletFactory.getHealth();
        this.isExploded = false;
        this.setFill(this.animationImagePatterns.get(0));
        this.setId(v + "_" + v1);
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

    public boolean isExploded() {
        return isExploded;
    }

    /***
     * if this bullet has collision with its enemy
     */
    public void checkForCollisionWithEnemy() {
        for (Imageable enemy : enemies) {
            if (CollisionController.haveCollision(this.getCurrentImage(),enemy.getCurrentImage(),this.getBoundsInParent(),enemy.getBound())) {
                logger.info("{}",enemies.get(0).getClass().getName());
                if (enemy instanceof Avatar avatar && !avatar.canGetDamage()) return;
                logger.info("hit enemy : {} " , enemy);
                startExplosionTransitionAndStopNormalTransition(BulletCollisionType.HIT_ENEMY,this);
                return;
            }
        }
    }

    private EventHandler createAfterExplosionAction(BulletCollisionType type) {
        switch (type) {
            case HIT_ENEMY -> {
                return event -> {
                    logger.debug("{}",this);
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
            if (node instanceof Bullet bullet && bullet != this && !bullet.isExploded() && !bullet.getEnemies().equals(this.enemies)) {
                if (CollisionController.haveCollision(this.currentImage,bullet.getCurrentImage(),this.getBound(),bullet.getBound())) {
                    decreaseBulletHealths(bullet,this);
                    checkHealthForExplosionIfNeeded(bullet,this);
                    return;
                }
            }
        }
    }

    private void checkHealthForExplosionIfNeeded(Bullet... bullets) {
        for (Bullet b: bullets)
            checkBulletHealthAndExplosionIfBulletDoNotHaveHealth(b);
    }

    private void decreaseBulletHealths(Bullet bullet1,Bullet bullet2) {
        bullet1.decreaseHealth(bullet2.damageRatio);
        bullet2.decreaseHealth(bullet1.damageRatio);
    }

    private void checkBulletHealthAndExplosionIfBulletDoNotHaveHealth(Bullet b) {
        if (!b.hasHealth()) {
            startExplosionTransitionAndStopNormalTransition(BulletCollisionType.HIT_BULLET,b);
        }
    }

    private boolean haveCollisionWithForeignBullet(Bullet bullet) {
        return bullet.getBoundsInParent().intersects(this.getBoundsInParent());
    }

    private void startExplosionTransitionAndStopNormalTransition(BulletCollisionType type,Bullet bullet) {
        bullet.getBulletTransition().stop();
        bullet.getExplosion(type).play();
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
                Bullet.this.isExploded = true;
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

    public List<Imageable> getEnemies() {
        return enemies;
    }

    public List<ImagePattern> getAnimationImagePatterns() {
        return animationImagePatterns;
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

    public void setEnemies(List<Imageable> enemies) {
        this.enemies = enemies;
    }
}
