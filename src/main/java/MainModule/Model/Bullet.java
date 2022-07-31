package MainModule.Model;

import MainModule.Enums.AvatarStates;
import MainModule.Enums.Bullets;
import MainModule.Enums.MoveFuncs;
import MainModule.Model.BossBirds.BossBird;
import MainModule.View.Menus.MenuStack;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Bullet extends Rectangle {
    /***
     * bullet has can be used for all bullets and has one movefuncs enum and or enemy(avatar or boss bird) and one transition for explosion
     */
    int speed;
    final int cycleCount;
    final int damageRatio;
    int health;
    final int duration;
    final ArrayList<Rectangle> enemies;
    MoveFuncs moveFuncs;
    Transition explosion;
    ArrayList<ImagePattern> animation;
    Transition bulletTransition;

    /***
     * @param v : left up x coordination
     * @param v1 : left up y coordination
     * @param bullet : an enum that contains speed and animation of bullet and function that gets cycleCount and have a movefuncs enum
     * @param enemy : an arraylist which contains the enemies of this bullet
     */
    public Bullet(double v, double v1, Bullets bullet, ArrayList<Rectangle> enemy) {
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
        for (Rectangle enemy : enemies) {
            if (this.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                EventHandler eventHandler = event -> {
                    if (enemies.get(0) instanceof Avatar avatar) {

                        avatar.setAvatarStates(AvatarStates.BLINK);
                        avatar.decreaseHealth(damageRatio);
                    } else if (enemies.get(0) instanceof BossBird bossBird) {
                        bossBird.decreaseHealth(damageRatio);
                    }
                    MenuStack.getInstance().getTopMenu().getRoot().getChildren().remove(Bullet.this);
                };
                if(enemy instanceof Avatar avatar && !avatar.canGetDamage()) return;
                this.getBulletTransition().stop();
                this.getExplosion(eventHandler).play();
                return;
            }
        }
        return;
    }

    /***
     * if this bullet has collision with enemy bullet set isCollision of bullet true
     */
    public void checkForCollisionWithBullets() {
        for (Node node :
                MenuStack.getInstance().getTopMenu().getRoot().getChildren()) {
            if (node instanceof Bullet bullet && bullet != this && !bullet.getEnemies().equals(this.enemies)) {
                if (bullet.getBoundsInParent().intersects(this.getBoundsInParent())) {
                    Bullet.this.decreaseHealth(bullet.damageRatio);
                    if (Bullet.this.hasHealth()) continue;
                    else Bullet.this.getBulletTransition().stop();
                    EventHandler eventHandler = event -> {
                        MenuStack.getInstance().getTopMenu().getRoot().getChildren().remove(Bullet.this);
                    };
                    this.getBulletTransition().stop();
                    this.getExplosion(eventHandler).play();
                    return;
                }
            }
        }
    }

    //getter and setters
    public MoveFuncs getMoveFuncs() {
        return moveFuncs;
    }


    /***
     * @param eventHandler : setOnFinished action of bullet
     * @return : explosion transition of bullet
     */
    public Transition getExplosion(EventHandler eventHandler) {
        explosion = new Transition() {
            {
                setCycleDuration(Duration.millis(Bullet.this.getMoveFuncs().getExplosionTime()));
                setCycleCount(1);
                setFill(Bullet.this.moveFuncs.getExplosion().get(0));
                setWidth(Bullet.this.moveFuncs.getExplosionSize());
                setHeight(Bullet.this.moveFuncs.getExplosionSize());
                setOnFinished(eventHandler);
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

    public ArrayList<Rectangle> getEnemies() {
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
}
