package ModuleAbstractClasses.ModuleAbstractClasses.Transitions.BossBirdTransitions;


import java.util.ArrayList;
import java.util.Random;

import ModuleAbstractClasses.ModuleAbstractClasses.Enums.BulletTransitionFactory;
import ModuleAbstractClasses.ModuleAbstractClasses.Enums.bossBirdStateEnums.BossBirdStates;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.Avatar;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.BossBird.BossBird;
import ModuleAbstractClasses.ModuleAbstractClasses.Managers.TransitionManager;
import ModuleAbstractClasses.ModuleAbstractClasses.Transitions.TransitionType;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.Constants;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.Location;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.BulletFactoryCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BossBirdTransitions extends javafx.animation.Transition implements BulletTransitionFactory {
    static Logger logger = LoggerFactory.getLogger(BossBirdTransitions.class);
    static Random random;
    BossBird bossBird;
    BulletTransition bossBirdBulletTransition;
    BulletFactoryCreator poultryFactoryCreator;


    public BossBirdTransitions(BossBird bossBird, BulletFactoryCreator bfc) {
        initializeTransitionFields(bossBird, bfc);
        TransitionManager.addTransition(TransitionType.BOSS_BIRD_TRANSITION, this);
        initializeSetOnFinishAction();
    }

    private void initializeTransitionFields(BossBird bossBird, BulletFactoryCreator bfc) {
        this.bossBird = bossBird;
        setCycleDuration(Duration.millis(1500));
        setCycleCount(this.bossBird.getBossBirdState().getCycleCount().returnCycleNumber(this.bossBird.getBossBirdState().getBound()));
        poultryFactoryCreator = bfc;
    }

    private void initializeSetOnFinishAction() {
        setOnFinished(actionEvent -> {
            boolean isBossBirdDeadAndNewBossNeedsToInitialize = isBossBirdDead();
            BossBirdTransitions.this.bossBird.changeState();
            updateTransitionCycleCount();
            if (!isBossBirdDeadAndNewBossNeedsToInitialize) BossBirdTransitions.this.play();
        });
    }

    private boolean isBossBirdDead() {
        boolean isEnd = false;
        if (BossBirdTransitions.this.bossBird.getBossBirdState() == BossBirdStates.Death) {
            TransitionManager.removeTransition(TransitionType.BOSS_BIRD_TRANSITION, BossBirdTransitions.this);
            isEnd = true;
        }
        return isEnd;
    }

    private void updateTransitionCycleCount() {
        BossBirdTransitions.this.setCycleCount(BossBirdTransitions.this.bossBird.getBossBirdState().getCycleCount().returnCycleNumber(BossBirdTransitions.this.bossBird.getBossBirdState().getBound()));
    }

    public static int getRandomNumber() {
        if (random == null) random = new Random();
        return random.nextInt(2) + 1;
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

    private void sendChickenBossBird() {
        int y = (getRandomNumber(7) - 1) * 100;
        int randomNumberForSelectionColorOfPoultry = getRandomNumber(Constants.NUMBERS_OF_TYPES_OF_POULTRY);
        for (int i = 1; i <= 3; i++) {
            createBulletTransition(poultryFactoryCreator.getNewBossBirdBulletFactory(randomNumberForSelectionColorOfPoultry), new Location(Constants.BOSS_BIRD_POULTRY_X + i * Constants.BOSS_BIRD_POULTRY_DISTANCE, y)).play();
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
        this.bossBirdBulletTransition = createBulletTransition(bossBird.getBulletFactory(), bossBird.getBulletLocation());
        bossBirdBulletTransition.play();
    }

    public static int getRandomNumber(int bound) {
        if (random == null) random = new Random();
        return random.nextInt(bound) + 1;
    }

    public BossBird getBossBird() {
        return bossBird;
    }
}
