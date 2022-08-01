package MainModule.Model.BossBirds;

import MainModule.Controllers.BossBirdStateControllers.HouseBossBirdStateController;
import MainModule.Enums.Bullets;
import MainModule.Model.*;
import MainModule.Util.Constants;
import MainModule.View.BossBirdTransitions.BossBirdTransitions;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HouseBossBird extends BossBird {
    public HouseBossBird(double v, double v1, double v2, double v3, HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations, int distance_collision_x, int distance_collision_y) {
        super(v, v1, v2, v3, bossBirdAnimations, distance_collision_x, distance_collision_y);
        this.bossBirdState = BossBirdStates.FLYING;
        this.controller = new HouseBossBirdStateController(this);
    }

    /***
     * @inheritDoc <p>this function changes shooting to flying and conversely, if boss bird hasn't enough health its state would change to death
     *                and in death state first we set the instance of boss bird null then we pop the above element of boss bird stack and then
     *                remove boss bird from anchor pane and then we initialize new boss bird</p>
     */

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
    public void initializeNewBossBirdAndItsTransitions() {
        BossBirdTransitions bossBirdTransitions = new BossBirdTransitions(this);
        BossBirdManger.getInstance().getBossBirdTransitions().add(bossBirdTransitions);
        bossBirdTransitions.play();
    }

    @Override
    public Bullet getBullet() {
        return new Bullet(BossBird.getInstance().getX() + Constants.BOSS_BIRD_BULLET_X, BossBird.getInstance().getY() + Constants.BOSS_BIRD_BULLET_Y, Bullets.EGG_BULLETS, new ArrayList<>(List.of(Avatar.getInstance())));
    }

    @Override
    public Bullets getBulletType() {
        return Bullets.EGG_BULLETS;
    }

    @Override
    public boolean hasHealth() {
        return health >= (Constants.BOSS_BIRD2_HEALTH + Constants.BOSS_BIRD3_HEALTH);
    }

}
