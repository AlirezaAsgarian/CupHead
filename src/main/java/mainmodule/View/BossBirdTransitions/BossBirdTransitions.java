package mainmodule.View.BossBirdTransitions;

import mainmodule.model.ModuleAbstractClasses.Avatar;
import mainmodule.model.ModuleAbstractClasses.TransitionManager;
import mainmodule.model.ModuleAbstractClasses.TransitionType;
import mainmodule.model.pluginA.BossBirds.MiniBossBird;
import mainmodule.model.pluginA.BossBirds.bossBirdStateEnums.BossBirdStates;
import mainmodule.util.Location;
import mainmodule.model.pluginA.Enums.BulletTransitionFactory;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.ModuleAbstractClasses.BulletFactoryCreator;
import mainmodule.model.pluginA.BulletFactories.PoultryBirdBulletsFactories.PoultryBulletFactoryCreator;
import mainmodule.model.pluginA.util.Constants;
import mainmodule.model.pluginA.util.SetConstants;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;
import org.slf4j.Logger;

public class BossBirdTransitions extends javafx.animation.Transition implements BulletTransitionFactory {
    BossBird bossBird;

    static Logger logger = LoggerFactory.getLogger(BossBirdTransitions.class);

    BulletTransition bossBirdBulletTransition;
    BulletFactoryCreator poultryFactoryCreator;
    static Random random;


    public BossBirdTransitions(BossBird bossBird) {
        this.bossBird = bossBird;
        setCycleDuration(Duration.millis(1500));
        setCycleCount(this.bossBird.getBossBirdState().getCycleCount().returnCycleNumber(this.bossBird.getBossBirdState().getBound()));
        TransitionManager.addTransition(TransitionType.BOSS_BIRD_TRANSITION,this);
        poultryFactoryCreator = new PoultryBulletFactoryCreator();
        initializeSetOnFinishAction();
    }

    private void initializeSetOnFinishAction() {
        setOnFinished(actionEvent -> {
            boolean isEnd = true;
            if (BossBirdTransitions.this.bossBird.getBossBirdState() == BossBirdStates.Death) {
                TransitionManager.removeTransition(TransitionType.BOSS_BIRD_TRANSITION,BossBirdTransitions.this);
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
        int randomNumberForSelectionColorOfPoultry = getRandomNumber(Constants.NUMBERS_OF_TYPES_OF_POULTRY);
        for (int i = 1; i <= 3; i++) {
                createBulletTransition(poultryFactoryCreator.getNewBossBirdBulletFactory(randomNumberForSelectionColorOfPoultry),new Location(Constants.BOSS_BIRD_POULTRY_X + i * Constants.BOSS_BIRD_POULTRY_DISTANCE,SetConstants.BOSS_BIRD_POULTRY_Y)).play();
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


    private MiniBossBird getInstance() {
        return (MiniBossBird) BossBird.getInstance();
    }

    private void startBossBirdNewBulletTransition() {
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
