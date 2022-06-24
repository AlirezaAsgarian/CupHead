package MainModule.Model;

import MainModule.Enums.Bullets;
import MainModule.Util.Constants;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DoctorBossBird extends BossBird {
    ArrayList<Bullets> bullets;

    public DoctorBossBird(double v, double v1, double v2, double v3, HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations, ArrayList<Bullets> bullets,int distance_collision_x,int distance_collision_y) {
        super(v, v1, v2, v3, bossBirdAnimations, distance_collision_x, distance_collision_y);
        this.bossBirdState = BossBirdStates.FLYING;
        this.bullets = bullets;
        this.setFill(this.getBossBirdAnimations().get(this.bossBirdState).get(0));
    }

    @Override
    public void changeState() {
        if (this.bossBirdState == BossBirdStates.FLYING) {
            this.bossBirdState = BossBirdStates.SHOOTING;
            return;
        }
        if (this.bossBirdState == BossBirdStates.SHOOTING) {
            this.bossBirdState = BossBirdStates.FLYING;
        }
    }

    @Override
    public void moveBossBird() {
        this.setX(this.getX() + BedBossBird.direction.get() * Constants.BOSS_BIRD3_SPEDD);
    }

    @Override
    public boolean isReadyForShooting(int frame) {
        return frame == 6 && this.bossBirdState == BossBirdStates.SHOOTING;
    }

    @Override
    public void initializeNewBossBird() {

    }

    @Override
    public Bullet getBullet() {
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        return new Bullet(this.getX() + Constants.DOCTOR_BIRD_BULLET_X, this.getY() + Constants.DOCTOR_BIRD_BULLET_Y , this.bullets.get(randomNumber), new ArrayList<>() {{
            add(Avatar.getInstance());
         }});
    }

    @Override
    protected boolean hasHealth() {
        return health < 0;
    }
}
