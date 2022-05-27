package MainModule.Model;

import MainModule.Enums.Bullets;
import MainModule.Util.Constants;
import MainModule.View.GameSceneView;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HouseBossBird extends BossBird {
    public HouseBossBird(double v, double v1, double v2, double v3, HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations) {
        super(v, v1, v2, v3, bossBirdAnimations);
        this.bossBirdState = BossBirdStates.FLYING;
    }

    @Override
    public void changeState() {
        if (bossBirdState == BossBirdStates.Death) {
            BossBird.setInstance(null);
            this.getBossBirdTransitions().stop();
            GameSceneView.anchorPane.getChildren().remove(this);
            BossBird.getInstance().initializeNewBossBird();
            return;
        }
        if (hasHealth()) return;
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
          GameSceneView.anchorPane.getChildren().add(instance);
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
