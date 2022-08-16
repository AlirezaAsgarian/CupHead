package mainmodule.model.pluginA.BossBirds;

import mainmodule.util.Location;
import mainmodule.model.pluginA.Controllers.BossBirdStateControllers.DoctorBossBirdStateController;
import mainmodule.model.pluginA.BossBirds.bossBirdStateEnums.BossBirdStates;
import mainmodule.model.pluginA.BulletFactories.DoctorBossBirdBulletFactories.DoctorBossBirdBulletFactoryCreator;
import mainmodule.model.ModuleAbstractClasses.BulletFactory;
import mainmodule.model.pluginA.util.Constants;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorBossBird extends BossBird {
    ArrayList<BulletFactory> bullets;

    public DoctorBossBird(double v, double v1, double v2, double v3, HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations, ArrayList<BulletFactory> bullets, int distance_collision_x, int distance_collision_y) {
        super(v, v1, v2, v3, bossBirdAnimations, distance_collision_x, distance_collision_y,new DoctorBossBirdBulletFactoryCreator());
        this.bossBirdState = BossBirdStates.FLYING;
        this.bullets = bullets;
        this.setFill(this.getBossBirdAnimations().get(this.bossBirdState).get(0));
        this.controller = new DoctorBossBirdStateController();
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
    public Location getBulletLocation() {
        return new Location(this.getX() + Constants.DOCTOR_BIRD_BULLET_X, this.getY() + Constants.DOCTOR_BIRD_BULLET_Y);
    }
    @Override
    public BulletFactory getBulletFactory() {
        return this.bullets.get(0);
    }

    @Override
    public boolean hasHealth() {
        return health < 0;
    }

}
