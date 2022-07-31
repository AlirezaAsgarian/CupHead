package MainModule.Model.BossBirds;

import MainModule.Enums.Bullets;
import MainModule.Controllers.BossBirdStateControllers.DoctorBossBirdStateController;
import MainModule.Model.Avatar;
import MainModule.Model.BossBirdStates;
import MainModule.Model.Bullet;
import MainModule.Util.Constants;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DoctorBossBird extends BossBird {
    ArrayList<Bullets> bullets;

    public DoctorBossBird(double v, double v1, double v2, double v3, HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations, ArrayList<Bullets> bullets, int distance_collision_x, int distance_collision_y) {
        super(v, v1, v2, v3, bossBirdAnimations, distance_collision_x, distance_collision_y);
        this.bossBirdState = BossBirdStates.FLYING;
        this.bullets = bullets;
        this.setFill(this.getBossBirdAnimations().get(this.bossBirdState).get(0));
    }

    @Override
    public void changeState() {
       bossBirdState =  new DoctorBossBirdStateController().updateBossBirdState(bossBirdState);
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
    public void initializeNewBossBirdAndItsTransitions() {

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
    public Bullets getBulletType() {
        return this.bullets.get(0);
    }

    @Override
    protected boolean hasHealth() {
        return health < 0;
    }

}
