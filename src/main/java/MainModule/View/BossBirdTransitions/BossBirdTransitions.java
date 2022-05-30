package MainModule.View.BossBirdTransitions;

import MainModule.Enums.Bullets;
import MainModule.Model.*;
import MainModule.Util.Constants;
import MainModule.Util.SetConstants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BossBirdTransitions extends javafx.animation.Transition {
    BossBird bossBird;

    BulletTransition bossBirdBulletTransition;
    static Random random;


    public BossBirdTransitions(BossBird bossBird) {
        this.bossBird = bossBird;
        setCycleDuration(Duration.millis(1500));
        setCycleCount(this.bossBird.getBossBirdState().getCycleCount().returnCycleNumber(this.bossBird.getBossBirdState().getBound()));
    }

    @Override
    protected void interpolate(double v) {
        this.bossBird.moveBossBird();
        playAnimation(v);
        if (getRandomNumber(Constants.BOSS_BIRD_POULTRY_PROBABILITY) == 3) {
            sendChickenBossBird();
        }
        setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boolean isEnd = true;
                if (BossBirdTransitions.this.bossBird.getBossBirdState() == BossBirdStates.Death) isEnd = false;
                BossBirdTransitions.this.bossBird.changeState();
                BossBirdTransitions.this.setCycleCount(BossBirdTransitions.this.bossBird.getBossBirdState().getCycleCount().returnCycleNumber(BossBirdTransitions.this.bossBird.getBossBirdState().getBound()));
                if (isEnd) BossBirdTransitions.this.play();
            }
        });
    }

    private void sendChickenBossBird() {
        int y = (getRandomNumber(7) - 1) * 100;
        SetConstants.setBossBirdPoultryY(y);
        for (int i = 1; i <= 3; i++) {

            if (getRandomNumber(2) == 1) {
                Bullet bullet = new Bullet(Constants.BOSS_BIRD_POULTRY_X + i * Constants.BOSS_BIRD_POULTRY_DISTANCE, SetConstants.BOSS_BIRD_POULTRY_Y, Bullets.BOSS_BIRD_POULTRY_PURPLE, new ArrayList<>(List.of(Avatar.getInstance())));
                bullet.setShoot(new BulletTransition(bullet, true));
                bullet.getShoot().play();
            } else {
                Bullet bullet = new Bullet(Constants.BOSS_BIRD_POULTRY_X + i * Constants.BOSS_BIRD_POULTRY_DISTANCE, SetConstants.BOSS_BIRD_POULTRY_Y, Bullets.BOSS_BIRD_POULTRY_YELLOW, new ArrayList<>(List.of(Avatar.getInstance())));
                bullet.setShoot(new BulletTransition(bullet, true));
                bullet.getShoot().play();
            }
        }
    }

    public void playAnimation(double v) {
        ArrayList<ImagePattern> bossBirdImagePattern = this.bossBird.getBossBirdAnimations().get(this.bossBird.getBossBirdState());
        int length = bossBirdImagePattern.size();
        int frame = (int) Math.ceil(v * length);
        if (frame == 0) {
            frame = 1;
        }
        if (frame == 1) {
            bossBirdBulletTransition = null;
        }
        if (this.bossBird.isReadyForShooting(frame) && bossBirdBulletTransition == null) {
            boolean isFlexible = false;
            if (this.bossBird instanceof DoctorBossBird) isFlexible = true;
            Bullet bossBirdBullet = this.bossBird.getBullet();
            bossBirdBulletTransition = new BulletTransition(bossBirdBullet, isFlexible);
            bossBirdBullet.setShoot(bossBirdBulletTransition);
            bossBirdBulletTransition.play();
        }
        this.bossBird.setFill(bossBirdImagePattern.get(frame - 1));
    }

    public static int getRandomNumber() {
        if (random == null) random = new Random();
        return random.nextInt(2) + 1;
    }

    public static int getRandomNumber(int bound) {
        if (random == null) random = new Random();
        return random.nextInt(bound) + 1;
    }
}
