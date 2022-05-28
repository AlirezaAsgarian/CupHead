package MainModule.Model;

import MainModule.Enums.Bullets;
import MainModule.Util.BossBirdStack;
import MainModule.Util.Constants;
import MainModule.Util.SetConstants;
import MainModule.View.BossBirdTransitions.BulletTransition;
import MainModule.View.GameSceneView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MiniBossBird extends BossBird {

    ArrayList<MiniBossBirdBullet> miniBossBirdBullets;

    public MiniBossBird(double v, double v1, double v2, double v3, HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations) {
        super(v, v1, v2, v3, bossBirdAnimations);
        this.bossBirdState = BossBirdStates.TURN_TO_LEFT;
        this.setFill(this.getBossBirdAnimations().get(this.bossBirdState).get(0));
    }


    public ArrayList<MiniBossBirdBullet> getMiniBossBirdBullet() {
        if (miniBossBirdBullets == null) {
            miniBossBirdBullets = new ArrayList<>() {
                {
                    add(new MiniBossBirdBullet(MiniBossBird.this.getxCenter() + Constants.MINI_BOSS_EGG_MIN_DISTANCE + 100, MiniBossBird.this.getyCenter(), new ArrayList<Rectangle>(List.of(Avatar.getInstance())), Bullets.MINI_BOSS_BULLET_EGG, MiniBossBird.this));
                    add(new MiniBossBirdBullet(MiniBossBird.this.getxCenter(), MiniBossBird.this.getyCenter() + Constants.MINI_BOSS_EGG_MIN_DISTANCE + 100, new ArrayList<Rectangle>(List.of(Avatar.getInstance())), Bullets.MINI_BOSS_BULLET_EGG, MiniBossBird.this));
                    add(new MiniBossBirdBullet(MiniBossBird.this.getxCenter() - Constants.MINI_BOSS_EGG_MIN_DISTANCE - 100, MiniBossBird.this.getyCenter(), new ArrayList<Rectangle>(List.of(Avatar.getInstance())), Bullets.MINI_BOSS_BULLET_EGG, MiniBossBird.this));
                    add(new MiniBossBirdBullet(MiniBossBird.this.getxCenter(), MiniBossBird.this.getyCenter() - Constants.MINI_BOSS_EGG_MIN_DISTANCE - 100, new ArrayList<Rectangle>(List.of(Avatar.getInstance())), Bullets.MINI_BOSS_BULLET_EGG, MiniBossBird.this));
                }
            };
        }
        return miniBossBirdBullets;
    }

    /***
     * @inheritDoc <p>this function first detects if avatar is right side of the boss or the left, if side of avatar matches the state it would be
     *               change from flying to shooting and conversely, else it would turn to the correct side, if boss bird hasn't enough health its state would change to death and explodes the eggs of it
     *                and in death state first we set the instance of boss bird null then we pop the above element of boss bird stack and then
     *                remove boss bird from anchor pane and then we initialize new boss bird</p>
     */
    @Override
    public void changeState() {
        System.out.println(health);
        if (bossBirdState == BossBirdStates.Death) {
            BossBird.setInstance(null);
            BossBirdStack.bossBirdStack.pop();
            this.getBossBirdTransitions().stop();
            GameSceneView.anchorPane.getChildren().remove(this);
            BossBird.getInstance().initializeNewBossBird();
            return;
        }
        if (hasHealth()) {
            this.bossBirdState = BossBirdStates.Death;
            SetConstants.setMiniBossDeathSpeed((int) Math.ceil((this.getX() + this.getWidth()) / 85.0));
            for (MiniBossBirdBullet miniBossBirdBullet :
                    this.getMiniBossBirdBullet()) {
                miniBossBirdBullet.getShoot().stop();
                miniBossBirdBullet.getExplosion(event -> GameSceneView.anchorPane.getChildren().remove(miniBossBirdBullet)).play();
            }
            return;
        }
        if (this.bossBirdState == BossBirdStates.TURN_TO_LEFT) {
            if (isAvatarRightSideOfMiniBoss()) {
                this.bossBirdState = BossBirdStates.TURN_TO_RIGHT;
            } else {
                this.bossBirdState = BossBirdStates.SHOOTING_TO_LEFT;
            }
            return;
        }
        if (this.bossBirdState == BossBirdStates.TURN_TO_RIGHT) {
            if (!isAvatarRightSideOfMiniBoss()) {
                this.bossBirdState = BossBirdStates.TURN_TO_LEFT;
            } else {
                this.bossBirdState = BossBirdStates.SHOOTING_TO_RIGHT;
            }
            return;
        }
        if (this.bossBirdState == BossBirdStates.SHOOTING_TO_LEFT) {
            if (isAvatarRightSideOfMiniBoss()) {
                this.bossBirdState = BossBirdStates.TURN_TO_RIGHT;
            } else {
                this.bossBirdState = BossBirdStates.SHOOTING_TO_LEFT;
            }
            return;
        }
        if (this.bossBirdState == BossBirdStates.SHOOTING_TO_RIGHT) {
            if (!isAvatarRightSideOfMiniBoss()) {
                this.bossBirdState = BossBirdStates.TURN_TO_LEFT;
            } else {
                this.bossBirdState = BossBirdStates.SHOOTING_TO_RIGHT;
            }
        }
    }

    public boolean isAvatarRightSideOfMiniBoss() {
        return this.getX() < Avatar.getInstance().getX();
    }

    static int direction = 1;

    @Override
    public void moveBossBird() {
        if (bossBirdState == BossBirdStates.Death) {
            this.setX(this.getX() - SetConstants.MINI_BOSS_DEATH_SPEED);
        }
        if (Avatar.getInstance().getX() > this.getX()) {
            moveRight();
        } else {
            moveLeft();
        }
        if (Avatar.getInstance().getY() > this.getY()) {
            moveDown();
        } else {
            moveUp();
        }
    }

    @Override
    public boolean isReadyForShooting(int frame) {
        return (this.bossBirdState == BossBirdStates.SHOOTING_TO_LEFT || this.bossBirdState == BossBirdStates.SHOOTING_TO_RIGHT) && frame == 13;
    }

    @Override
    public void initializeNewBossBird() {
        System.out.println(this.bossBirdState);
        this.getBossBirdTransitions().play();
        GameSceneView.anchorPane.getChildren().add(this);
        for (MiniBossBirdBullet miniBossBirdBullet :
                this.getMiniBossBirdBullet()) {
            miniBossBirdBullet.getMiniBulletTransition().play();
            miniBossBirdBullet.setShoot(new BulletTransition(miniBossBirdBullet, true));
            miniBossBirdBullet.getShoot().play();
        }
    }

    @Override
    public Bullet getBullet() {
        if (this.bossBirdState == BossBirdStates.SHOOTING_TO_LEFT) {
            return new Bullet(BossBird.getInstance().getX() + Constants.MINI_BOSS_BULLET_X, BossBird.getInstance().getY() + Constants.MINI_BOSS_BULLET_Y, Bullets.MINI_BOSS_BULLET_TO_LEFT, new ArrayList<Rectangle>(List.of(Avatar.getInstance())));
        } else {
            return new Bullet(BossBird.getInstance().getX() + Constants.MINI_BOSS_BULLET_X, BossBird.getInstance().getY() + Constants.MINI_BOSS_BULLET_Y, Bullets.MINI_BOSS_BULLET_TO_RIGHT, new ArrayList<Rectangle>(List.of(Avatar.getInstance())));
        }
    }

    @Override
    protected boolean hasHealth() {
        System.out.println("Constants.BOSS_BIRD3_HEALTH = " + Constants.BOSS_BIRD3_HEALTH);
        return health < Constants.BOSS_BIRD3_HEALTH;
    }


    public void moveUp() {
        if (this.getY() - Constants.BOSS_MINI_BIRD_SPEED <= 0) return;
        this.setY(this.getY() - Constants.BOSS_MINI_BIRD_SPEED);
    }

    public void moveDown() {
        if (this.getY() + this.getHeight() + Constants.BOSS_MINI_BIRD_SPEED >= Constants.Max_Height) return;
        this.setY(this.getY() + Constants.BOSS_MINI_BIRD_SPEED);
    }

    public void moveRight() {
        if (this.getX() + this.getWidth() + Constants.BOSS_MINI_BIRD_SPEED >= Constants.Max_Width) return;
        this.setX(this.getX() + Constants.BOSS_MINI_BIRD_SPEED);
    }

    public void moveLeft() {
        if (this.getX() - Constants.BOSS_MINI_BIRD_SPEED <= 0) return;
        this.setX(this.getX() - Constants.BOSS_MINI_BIRD_SPEED);
    }

}
