package mainmodule.View.BossBirdTransitions;

import mainmodule.Controllers.Location;
import mainmodule.Enums.BulletTransitionFactory;
import mainmodule.Enums.Bullets;
import mainmodule.Enums.TransitionType;
import mainmodule.model.*;
import mainmodule.model.BossBirds.BossBird;
import mainmodule.model.BulletFactories.BulletFactoryCreator;
import mainmodule.model.BulletFactories.PoultryBirdBulletsFactories.PoultryBulletFactoryCreator;
import mainmodule.util.Constants;
import mainmodule.util.SetConstants;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class BossBirdTransitions extends javafx.animation.Transition implements BulletTransitionFactory {
    BossBird bossBird;

    BulletTransition bossBirdBulletTransition;
    BulletFactoryCreator poultryFactoryCreator;
    static Random random;


    public BossBirdTransitions(BossBird bossBird) {
        this.bossBird = bossBird;
        setCycleDuration(Duration.millis(1500));
        setCycleCount(this.bossBird.getBossBirdState().getCycleCount().returnCycleNumber(this.bossBird.getBossBirdState().getBound()));
        TransitionManger.addTransition(TransitionType.BOSS_BIRD_TRANSITION,this);
        poultryFactoryCreator = new PoultryBulletFactoryCreator();
        initializeSetOnFinishAction();
    }

    private void initializeSetOnFinishAction() {
        setOnFinished(actionEvent -> {
            boolean isEnd = true;
            if (BossBirdTransitions.this.bossBird.getBossBirdState() == BossBirdStates.Death) {
                TransitionManger.removeTransition(TransitionType.BOSS_BIRD_TRANSITION,BossBirdTransitions.this);
                isEnd = false;
            }
            BossBirdTransitions.this.bossBird.changeState();
            updateTransitionCycleCount();
            if (isEnd) BossBirdTransitions.this.play();
        });
    }

    @Override
    protected void interpolate(double v) {
        Avatar.getInstance().avatarRequestFocus();
        this.bossBird.moveBossBird();
        playAnimation(v);
        if (getRandomNumber(Constants.BOSS_BIRD_POULTRY_PROBABILITY) == 3) {
            sendChickenBossBird();
        }
    }

    private void updateTransitionCycleCount() {
        BossBirdTransitions.this.setCycleCount(BossBirdTransitions.this.bossBird.getBossBirdState().getCycleCount().returnCycleNumber(BossBirdTransitions.this.bossBird.getBossBirdState().getBound()));
    }

    private void sendChickenBossBird() {
        int y = (getRandomNumber(7) - 1) * 100;
        SetConstants.setBossBirdPoultryY(y);
        for (int i = 1; i <= 3; i++) {
                createBulletTransition(poultryFactoryCreator.getNewBossBirdBulletFactory(new Random().nextInt(Constants.NUMBERS_OF_TYPES_OF_POULTRY)),new Location(Constants.BOSS_BIRD_POULTRY_X + i * Constants.BOSS_BIRD_POULTRY_DISTANCE,SetConstants.BOSS_BIRD_POULTRY_Y)).play();
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
            startBossBirdNewBulletTransition();
        }
        this.bossBird.setImage(bossBirdImagePattern.get(frame - 1));
    }

    private void startBossBirdNewBulletTransition() {
        //bullets types and x and y is not important here
        this.bossBirdBulletTransition = createBulletTransition(bossBird.getBulletFactory(),bossBird.getBulletLocation());
        bossBirdBulletTransition.play();
    }

    public static int getRandomNumber() {
        if (random == null) random = new Random();
        return random.nextInt(2) + 1;
    }

    public static int getRandomNumber(int bound) {
        if (random == null) random = new Random();
        return random.nextInt(bound) + 1;
    }

    public BossBird getBossBird() {
        return bossBird;
    }
}
