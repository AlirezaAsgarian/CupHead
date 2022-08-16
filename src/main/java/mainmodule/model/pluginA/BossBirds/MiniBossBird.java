package mainmodule.model.pluginA.BossBirds;

import mainmodule.model.ModuleAbstractClasses.Avatar;
import mainmodule.model.ModuleAbstractClasses.BossBirdManger;
import mainmodule.model.ModuleAbstractClasses.BulletFactory;
import mainmodule.model.pluginA.BossBirds.bossBirdStateEnums.BossBirdStates;
import mainmodule.util.Location;
import mainmodule.model.pluginA.Enums.BulletTransitionFactory;
import mainmodule.model.pluginA.Controllers.BossBirdStateControllers.MiniBossBirdStateController;
import mainmodule.model.*;
import mainmodule.model.pluginA.BulletFactories.MiniBossBirdBulletFactories.MiniBossBirdBulletFactoryCreator;
import mainmodule.model.pluginA.util.Constants;
import mainmodule.model.pluginA.util.SetConstants;
import mainmodule.View.BossBirdTransitions.BossBirdTransitions;
import javafx.animation.Transition;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.HashMap;

public class MiniBossBird extends BossBird implements BulletTransitionFactory {

    ArrayList<MiniBossBirdBullet> miniBossBirdBullets;


    public MiniBossBird(double v, double v1, double v2, double v3, HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations, int distance_collision_x, int distance_collision_y) {
        super(v, v1, v2, v3, bossBirdAnimations, distance_collision_x, distance_collision_y,new MiniBossBirdBulletFactoryCreator());
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
                add(new MiniBossBirdBullet(MiniBossBird.this.getXCenter()+ Constants.MINI_BOSS_EGG_MIN_DISTANCE + 100, MiniBossBird.this.getYCenter(),  MiniBossBird.this));
                add(new MiniBossBirdBullet(MiniBossBird.this.getXCenter(), MiniBossBird.this.getYCenter() + Constants.MINI_BOSS_EGG_MIN_DISTANCE + 100, MiniBossBird.this));
                add(new MiniBossBirdBullet(MiniBossBird.this.getXCenter() - Constants.MINI_BOSS_EGG_MIN_DISTANCE - 100, MiniBossBird.this.getYCenter(), MiniBossBird.this));
                add(new MiniBossBirdBullet(MiniBossBird.this.getXCenter(), MiniBossBird.this.getYCenter() - Constants.MINI_BOSS_EGG_MIN_DISTANCE - 100, MiniBossBird.this));
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
            //todo check get X and get Y
            startTransitionsOfMiniBossBirdBullets(miniBossBirdBullet,new Location(miniBossBirdBullet.getX(),miniBossBirdBullet.getY()));
        }
        applyListenersForEggBullets();
    }

    private void applyListenersForEggBullets() {
        bindPivotRotationPointsToCenterOfBossBird();
        addListenerForEggBulletsPositionX();
        addListenerForEggBulletsPositionY();
    }

    private void addListenerForEggBulletsPositionY() {
        this.yProperty().addListener((observableValue, number, t1) -> {
            for (MiniBossBirdBullet mb:
                    MiniBossBird.this.getMiniBossBirdBullets()) {
                mb.setY(mb.getY() + (t1.doubleValue() - number.doubleValue()));
            }
        });
    }

    private void addListenerForEggBulletsPositionX() {
        this.xProperty().addListener((observableValue, number, t1) -> {
            for (MiniBossBirdBullet mb:
                 MiniBossBird.this.getMiniBossBirdBullets()) {
                mb.setX(mb.getX() + (t1.doubleValue() - number.doubleValue()));
            }
        });
    }

    private void bindPivotRotationPointsToCenterOfBossBird() {
        for (MiniBossBirdBullet mb:
             MiniBossBird.this.getMiniBossBirdBullets()) {
            mb.getBulletRotate().pivotXProperty().bind(this.xProperty().add(Constants.MINI_BOSS_WIDTH / 2));
            mb.getBulletRotate().pivotYProperty().bind(this.yProperty().add(Constants.MINI_BOSS_HEIGHT / 2));
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

    private void startTransitionsOfMiniBossBirdBullets(MiniBossBirdBullet miniBossBirdBullet,Location miniBossBirdBulletLocation) {
        Transition bulletRotateTran = miniBossBirdBullet.getMiniBulletRotateTransition();
        miniBossBirdBullet.setBulletTransition(bulletRotateTran);
        bulletRotateTran.play();
    }

    @Override
    public Location getBulletLocation() {
        if (this.bossBirdState == BossBirdStates.SHOOTING_TO_LEFT) {
            return new Location(BossBird.getInstance().getX() + Constants.MINI_BOSS_BULLET_X, BossBird.getInstance().getY() + Constants.MINI_BOSS_BULLET_Y);
        } else {
            return new Location(BossBird.getInstance().getX() + Constants.MINI_BOSS_BULLET_X, BossBird.getInstance().getY() + Constants.MINI_BOSS_BULLET_Y);
        }
    }

    @Override
    public BulletFactory getBulletFactory() {
      if(this.bossBirdState == BossBirdStates.SHOOTING_TO_LEFT){
          return bulletFactoryCreator.getNewBossBirdBulletFactory(Constants.MINI_BOSS_BIRD_BIRD_BULLET_TO_LEFT_INDEX);
      } else {
          return bulletFactoryCreator.getNewBossBirdBulletFactory(Constants.MINI_BOSS_BIRD_BIRD_BULLET_TO_RIGHT_INDEX);
      }
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
