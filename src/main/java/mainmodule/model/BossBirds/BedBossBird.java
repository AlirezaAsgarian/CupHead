package mainmodule.model.BossBirds;

import mainmodule.Controllers.BossBirdStateControllers.BedBossBirdStateController;
import mainmodule.Controllers.Location;
import mainmodule.Enums.Bullets;
import mainmodule.model.*;
import mainmodule.model.BulletFactories.BedBossBirdBulletFactories.BedBossBirdBulletFactoryCreator;
import mainmodule.model.BulletFactories.BedBossBirdBulletFactories.BedBossBirdBulletFactoryType1;
import mainmodule.util.Constants;
import mainmodule.View.BossBirdTransitions.BossBirdTransitions;
import javafx.scene.paint.ImagePattern;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BedBossBird extends BossBird {

    ArrayList<DoctorBossBird> doctorBossBirds;
    ArrayList<BulletFactory> bullets;


    public BedBossBird(double v, double v1, double v2, double v3, HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations, ArrayList<BulletFactory> bullets, int distance_collision_x, int distance_collision_y) {
        super(v, v1, v2, v3, bossBirdAnimations, distance_collision_x, distance_collision_y,new BedBossBirdBulletFactoryCreator());
        this.bossBirdState = BossBirdStates.FLYING;
        this.bullets = bullets;
        doctorBossBirds = new ArrayList<>(List.of((DoctorBossBird) BossBirdEnums.DOCTOR_BOSS_BIRD_LEFT.createNewBossBird(), (DoctorBossBird) BossBirdEnums.DOCTOR_BOSS_BIRD_RIGHT.createNewBossBird()));
        this.setFill(this.getBossBirdAnimations().get(this.bossBirdState).get(0));
        controller = new BedBossBirdStateController(this);
    }
     /***
     * @inheritDoc <p>this function changes shooting to flying and conversely, if boss bird hasn't enough health its state would change to death
     *                and in death state first we set the instance of boss bird null then we pop the above element of boss bird stack and then
     *                remove boss bird from anchor pane and then ends to the game</p>
     */


    static AtomicInteger direction = new AtomicInteger(1);

    @Override
    public void moveBossBird() {
        if (this.getX() + direction.get() * Constants.BOSSBIRD_SPEED + this.getWidth() >= Constants.Max_Width || this.getX() <= 0) {
            direction.set(direction.get() * -1);
        }
        this.setX(this.getX() + direction.get() * Constants.BOSS_BIRD3_SPEDD);
    }

    @Override
    public boolean isReadyForShooting(int frame) {
        return frame == 17 && this.bossBirdState == BossBirdStates.SHOOTING_GARBAGE;
    }

    public ArrayList<DoctorBossBird> getDoctorBossBirds() {
        return doctorBossBirds;
    }

    @Override
    public void initializeNewBossBirdAndItsTransitions() {
        addBossBirdToScreen(this);
        startBossBirdTransition();
        initializeDoctorBossBirdsAndItsTransition();
    }

    private void initializeDoctorBossBirdsAndItsTransition() {
        for (DoctorBossBird doctorBossBird :
                this.doctorBossBirds) {
            initializeDoctorBossBirdAndItsTransition(doctorBossBird);
        }
    }

    private void initializeDoctorBossBirdAndItsTransition(DoctorBossBird doctorBossBird) {
        addBossBirdToScreen(doctorBossBird);
        initializeDoctorBossBirdTransition(doctorBossBird);
    }

    private void initializeDoctorBossBirdTransition(DoctorBossBird doctorBossBird) {
        BossBirdTransitions doctorTran = new BossBirdTransitions(doctorBossBird);
        doctorTran.play();
        BossBirdManger.getInstance().addBossBirdTransitions(doctorTran);
    }

    private void startBossBirdTransition() {
        BossBirdTransitions bossBirdTransitions2 = new BossBirdTransitions(this);
        BossBirdManger.getInstance().addBossBirdTransitions(bossBirdTransitions2);
        bossBirdTransitions2.play();
    }

    @Override
    public Location getBulletLocation() {
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        return new Location(BossBird.getInstance().getX() + Constants.BED_BOSS_BIRD_BULLET_X, BossBird.getInstance().getY() + Constants.BED_BOSS_BIRD_BULLET_Y);
    }

    @Override
    public BulletFactory getBulletFactory() {
        return this.bullets.get(0);
    }

    @Override
    public boolean hasHealth() {
        return health >= 0;
    }

}
