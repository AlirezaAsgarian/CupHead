package MainModule.Model;

import MainModule.Enums.Bullets;
import MainModule.Util.BossBirdStack;
import MainModule.Util.Constants;
import MainModule.View.GameSceneView;
import MainModule.View.Menus.MenuStack;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HouseBossBird extends BossBird {
    public HouseBossBird(double v, double v1, double v2, double v3, HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations,int distance_collision_x,int distance_collision_y) {
        super(v, v1, v2, v3, bossBirdAnimations, distance_collision_x, distance_collision_y);
        this.bossBirdState = BossBirdStates.FLYING;
    }

    /***
     * @inheritDoc <p>this function changes shooting to flying and conversely, if boss bird hasn't enough health its state would change to death
     *                and in death state first we set the instance of boss bird null then we pop the above element of boss bird stack and then
     *                remove boss bird from anchor pane and then we initialize new boss bird</p>
     */
    @Override
    public void changeState() {
        if (bossBirdState == BossBirdStates.Death) {
            System.out.println("hello2");
            BossBird.setInstance(null);
            BossBirdStack.bossBirdStack.pop();
            this.getBossBirdTransitions().stop();
            System.out.println("hello3");
            MenuStack.getInstance().getTopMenu().getRoot().getChildren().remove(this);
            BossBird.getInstance().initializeNewBossBird();
            System.out.println("hello4");
            return;
        }
        if (hasHealth()) {
            this.bossBirdState = BossBirdStates.Death;
            return;
        }
        if (bossBirdState == BossBirdStates.FLYING) {
            bossBirdState = BossBirdStates.SHOOTING;
        } else if (bossBirdState == BossBirdStates.SHOOTING) {
            bossBirdState = BossBirdStates.FLYING;
        }
    }

    static int direction = 1;

    public void moveBossBird() {
        if (this.getY() + direction * Constants.BOSSBIRD_SPEED + this.getHeight() >= Constants.Max_Height || this.getY() <= 0) {
            direction *= -1;
        }
        this.setY(this.getY() + direction * Constants.BOSSBIRD_SPEED);
    }

    @Override
    public boolean isReadyForShooting(int frame) {
        return frame == 6 && this.bossBirdState == BossBirdStates.SHOOTING;
    }

    @Override
    public void initializeNewBossBird() {
          MenuStack.getInstance().getTopMenu().getRoot().getChildren().add(instance);
          instance.getBossBirdTransitions().play();
    }

    @Override
    public Bullet getBullet() {
        return new Bullet(BossBird.getInstance().getX() + Constants.BOSS_BIRD_BULLET_X, BossBird.getInstance().getY() + Constants.BOSS_BIRD_BULLET_Y, Bullets.EGG_BULLETS, new ArrayList<>(List.of(Avatar.getInstance())));
    }

    @Override
    protected boolean hasHealth() {
        return health < (Constants.BOSS_BIRD2_HEALTH + Constants.BOSS_BIRD3_HEALTH);
    }
}
