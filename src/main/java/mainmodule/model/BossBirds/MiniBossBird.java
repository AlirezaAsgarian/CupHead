package mainmodule.model.BossBirds;

import mainmodule.Enums.BulletTransitionFactory;
import mainmodule.Enums.Bullets;
import mainmodule.Controllers.BossBirdStateControllers.MiniBossBirdStateController;
import mainmodule.model.*;
import mainmodule.util.Constants;
import mainmodule.util.SetConstants;
import mainmodule.View.BossBirdTransitions.BossBirdTransitions;
import mainmodule.View.BossBirdTransitions.BulletTransition;
import javafx.animation.Transition;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MiniBossBird extends BossBird implements BulletTransitionFactory {

    ArrayList<MiniBossBirdBullet> miniBossBirdBullets;

    public MiniBossBird(double v, double v1, double v2, double v3, HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations, int distance_collision_x, int distance_collision_y) {
        super(v, v1, v2, v3, bossBirdAnimations, distance_collision_x, distance_collision_y);
        this.bossBirdState = BossBirdStates.TURN_TO_LEFT;
        this.setFill(this.getBossBirdAnimations().get(this.bossBirdState).get(0));
        this.controller = new MiniBossBirdStateController(this);
    }


    public ArrayList<MiniBossBirdBullet> getMiniBossBirdBullets() {
        if (miniBossBirdBullets == null) {
            miniBossBirdBullets = createMiniBossBirdEggBullets();
        }
        return miniBossBirdBullets;
    }

    private ArrayList<MiniBossBirdBullet> createMiniBossBirdEggBullets() {
        return new ArrayList<>() {
            {
                add(new MiniBossBirdBullet(MiniBossBird.this.getxCenter() + Constants.MINI_BOSS_EGG_MIN_DISTANCE + 100, MiniBossBird.this.getyCenter(), new ArrayList<Imageable>(List.of(Avatar.getInstance())), Bullets.MINI_BOSS_BULLET_EGG, MiniBossBird.this));
                add(new MiniBossBirdBullet(MiniBossBird.this.getxCenter(), MiniBossBird.this.getyCenter() + Constants.MINI_BOSS_EGG_MIN_DISTANCE + 100, new ArrayList<Imageable>(List.of(Avatar.getInstance())), Bullets.MINI_BOSS_BULLET_EGG, MiniBossBird.this));
                add(new MiniBossBirdBullet(MiniBossBird.this.getxCenter() - Constants.MINI_BOSS_EGG_MIN_DISTANCE - 100, MiniBossBird.this.getyCenter(), new ArrayList<Imageable>(List.of(Avatar.getInstance())), Bullets.MINI_BOSS_BULLET_EGG, MiniBossBird.this));
                add(new MiniBossBirdBullet(MiniBossBird.this.getxCenter(), MiniBossBird.this.getyCenter() - Constants.MINI_BOSS_EGG_MIN_DISTANCE - 100, new ArrayList<Imageable>(List.of(Avatar.getInstance())), Bullets.MINI_BOSS_BULLET_EGG, MiniBossBird.this));
            }
        };
    }

    /***
     * @inheritDoc <p>this function first detects if avatar is right side of the boss or the left, if side of avatar matches the state it would be
     *               change from flying to shooting and conversely, else it would turn to the correct side, if boss bird hasn't enough health its state would change to death and explodes the eggs of it
     *                and in death state first we set the instance of boss bird null then we pop the above element of boss bird stack and then
     *                remove boss bird from anchor pane and then we initialize new boss bird</p>
     */


    public boolean isAvatarRightSideOfMiniBoss() {
        return this.getX() < Avatar.getInstance().getX();
    }

    static int direction = 1;

    @Override
    public void moveBossBird() {
        if (bossBirdState == BossBirdStates.Death) {
            moveBossBirdOutOfScreenFromLeftSide();
            return;
        } else {
            moveBossBirdHorizontally();
            moveBossBirdVertically();
        }
    }

    private void moveBossBirdVertically() {
        if (Avatar.getInstance().getY() > this.getY()) {
            moveDown();
        } else {
            moveUp();
        }
    }

    private void moveBossBirdHorizontally() {
        if (Avatar.getInstance().getX() > this.getX()) {
            moveRight();
        } else {
            moveLeft();
        }
    }

    private void moveBossBirdOutOfScreenFromLeftSide() {
        this.setX(this.getX() - SetConstants.MINI_BOSS_DEATH_SPEED);
    }

    @Override
    public boolean isReadyForShooting(int frame) {
        return (this.bossBirdState == BossBirdStates.SHOOTING_TO_LEFT || this.bossBirdState == BossBirdStates.SHOOTING_TO_RIGHT) && frame == 13;
    }

    @Override
    public void initializeNewBossBirdAndItsTransitions() {
        BossBirdTransitions bossBirdTransitions = createBossBirdTransition();
        bossBirdTransitions.play();
        addBossBirdToScreen(this);
        initializeBossBirdEggBullets();
    }

    private void initializeBossBirdEggBullets() {
        for (MiniBossBirdBullet miniBossBirdBullet :
                this.getMiniBossBirdBullets()) {
            startTransitionsOfMiniBossBirdBullets(miniBossBirdBullet);
        }
    }



    private BossBirdTransitions createBossBirdTransition() {
        BossBirdTransitions bossBirdTransitions = new BossBirdTransitions(this);
        addTransitionToBossBirdManager(bossBirdTransitions);
        return bossBirdTransitions;
    }

    private void addTransitionToBossBirdManager(BossBirdTransitions bossBirdTransitions) {
        BossBirdManger.getInstance().addBossBirdTransitions(bossBirdTransitions);
    }

    private void startTransitionsOfMiniBossBirdBullets(MiniBossBirdBullet miniBossBirdBullet) {
        Transition bulletRotateTran = miniBossBirdBullet.getMiniBulletRotateTransition();
        bulletRotateTran.play();
        BulletTransition buTran = createBulletTransition(Bullets.MINI_BOSS_BULLET_EGG, -1, -1, miniBossBirdBullet);
        miniBossBirdBullet.setBulletTransition(buTran);
        buTran.play();
    }

    @Override
    public Bullet getBullet() {
        if (this.bossBirdState == BossBirdStates.SHOOTING_TO_LEFT) {
            return new Bullet(BossBird.getInstance().getX() + Constants.MINI_BOSS_BULLET_X, BossBird.getInstance().getY() + Constants.MINI_BOSS_BULLET_Y, Bullets.MINI_BOSS_BULLET_TO_LEFT, new ArrayList<Imageable>(List.of(Avatar.getInstance())));
        } else {
            return new Bullet(BossBird.getInstance().getX() + Constants.MINI_BOSS_BULLET_X, BossBird.getInstance().getY() + Constants.MINI_BOSS_BULLET_Y, Bullets.MINI_BOSS_BULLET_TO_RIGHT, new ArrayList<Imageable>(List.of(Avatar.getInstance())));
        }
    }

    @Override
    public Bullets getBulletType() {
        return Bullets.MINI_BOSS_BULLET_TO_LEFT;
    }

    @Override
    public boolean hasHealth() {
        System.out.println("Constants.BOSS_BIRD3_HEALTH = " + Constants.BOSS_BIRD3_HEALTH);
        return health >= Constants.BOSS_BIRD3_HEALTH;
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
