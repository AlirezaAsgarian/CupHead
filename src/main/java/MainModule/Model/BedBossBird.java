package MainModule.Model;

import MainModule.Enums.Bullets;
import MainModule.Util.Constants;
import MainModule.View.BossBirdTransitions.BossBirdTransitions;
import MainModule.View.EndGameDialog;
import MainModule.View.GameSceneView;
import MainModule.View.Menus.Menu;
import MainModule.View.Menus.MenuStack;
import javafx.application.Platform;
import javafx.scene.paint.ImagePattern;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BedBossBird extends BossBird {

    ArrayList<DoctorBossBird> doctorBossBirds;
    ArrayList<Bullets> bullets;


    public BedBossBird(double v, double v1, double v2, double v3, HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations, ArrayList<Bullets> bullets,int distance_collision_x,int distance_collision_y) {
        super(v, v1, v2, v3, bossBirdAnimations, distance_collision_x, distance_collision_y);
        this.bossBirdState = BossBirdStates.FLYING;
        this.bullets = bullets;
        doctorBossBirds = new ArrayList<>(List.of((DoctorBossBird) BossBirdEnums.DOCTOR_BOSS_BIRD_LEFT.getBossBird(), (DoctorBossBird) BossBirdEnums.DOCTOR_BOSS_BIRD_RIGHT.getBossBird()));
        this.setFill(this.getBossBirdAnimations().get(this.bossBirdState).get(0));
    }
     /***
     * @inheritDoc <p>this function changes shooting to flying and conversely, if boss bird hasn't enough health its state would change to death
     *                and in death state first we set the instance of boss bird null then we pop the above element of boss bird stack and then
     *                remove boss bird from anchor pane and then ends to the game</p>
     */
    @Override
    public void changeState() {
         if (bossBirdState == BossBirdStates.Death) {
            //todo end game
             Platform.runLater(() -> {
                 MenuStack.getInstance().killGame();
                 Optional<String> result = new EndGameDialog("win").showAndWait();
                 if(result.get().equals("Retry")){
                     MenuStack.getInstance().popMenu();
                     MenuStack.getInstance().addMenu(Menu.pushMenu("Game.fxml"));
                 } else {
                     MenuStack.getInstance().popMenu();
                 }
             });            return;
        }
        if (hasHealth()) {
            this.bossBirdState = BossBirdStates.Death;
            this.doctorBossBirds.forEach(doctorBossBird -> {
                MenuStack.getInstance().getTopMenu().getRoot().getChildren().remove(doctorBossBird);
                BossBirdManger.getInstance().removeBossBirdTransitionByBossBird(this);
            });
            return;
        }
        if (bossBirdState == BossBirdStates.FLYING) {
            bossBirdState = BossBirdStates.SHOOTING_GARBAGE;
        } else if (bossBirdState == BossBirdStates.SHOOTING_GARBAGE) {
            bossBirdState = BossBirdStates.FLYING;
        }
    }

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

    @Override
    public void initializeNewBossBird() {
        MenuStack.getInstance().getTopMenu().getRoot().getChildren().add(this);
        BossBirdTransitions bossBirdTransitions2 = new BossBirdTransitions(this);
        BossBirdManger.getInstance().addBossBirdTransitions(bossBirdTransitions2);
        bossBirdTransitions2.play();
        for (DoctorBossBird doctorBossBird :
                this.doctorBossBirds) {
            MenuStack.getInstance().getTopMenu().getRoot().getChildren().add(doctorBossBird);
            BossBirdTransitions bossBirdTransitions1 = new BossBirdTransitions(doctorBossBird);
            bossBirdTransitions1.play();
            BossBirdManger.getInstance().addBossBirdTransitions(bossBirdTransitions1);
        }
    }

    @Override
    public Bullet getBullet() {
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        System.out.println("BossBird.getInstance().getX() = " + BossBird.getInstance().getX());
        System.out.println("BossBird.getInstance().getY() = " + BossBird.getInstance().getY());
        return new Bullet(BossBird.getInstance().getX() + Constants.BED_BOSS_BIRD_BULLET_X, BossBird.getInstance().getY() + Constants.BED_BOSS_BIRD_BULLET_Y, this.bullets.get(randomNumber), new ArrayList<>(List.of(Avatar.getInstance())));
    }

    @Override
    protected boolean hasHealth() {
        return health < 0;
    }
}
