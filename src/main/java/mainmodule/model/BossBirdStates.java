package mainmodule.model;

import mainmodule.util.Constants;
import javafx.util.Duration;
import java.util.Random;

public enum BossBirdStates {
    /***
     * 0 in bound represents that cycle count is constant
     */
    SHOOTING(Constants.BOSS_BIRD_STATE_SHOOTING, BossBirdStates::getRandomNumber,Constants.BOSS_BIRD_STATE_SHOOTING_BOUND),
    FLYING(Constants.BOSS_BIRD_STATE_FLYING, BossBirdStates::getRandomNumber,Constants.BOSS_BIRD_STATE_FLYING_BOUND),
    HITTED(Constants.BOSS_BIRD_STATE_HITTED, BossBirdStates::getRandomNumber,Constants.BOSS_BIRD_STATE_HITTED),
    TURN_TO_LEFT(Constants.BOSS_BIRD_STATE_TURN_TO_LEFT, (bound) -> Constants.BOSS_BIRD_STATE_TURN_TO_LEFT_CYCLE_COUNT,0),
    TURN_TO_RIGHT(Constants.BOSS_BIRD_STATE_TURN_TO_RIGHT,(bound) -> Constants.BOSS_BIRD_STATE_TURN_TO_RIGHT_CYCLE_COUNT,0),
    RIGHT(Constants.BOSS_BIRD_STATE_RIGHT, (bound) -> Constants.BOSS_BIRD_STATE_RIGHT_CYCLE_COUNT,0),
    LEFT(Constants.BOSS_BIRD_STATE_LEFT, (bound) -> Constants.BOSS_BIRD_STATE_LEFT_CYCLE_COUNT,0),
    SHOOTING_TO_LEFT(Constants.BOSS_BIRD_STATE_SHOOTING_LEFT, (bound) -> Constants.BOSS_BIRD_STATE_SHOOTING_TO_LEFT_CYCLE_COUNT,0),
    SHOOTING_TO_RIGHT(Constants.BOSS_BIRD_STATE_SHOOTING_RIGHT, (bound) -> Constants.BOSS_BIRD_STATE_SHOOTING_TO_RIGHT_CYCLE_COUNT,0),
    SHOOTING_GARBAGE(Constants.BOSS_BIRD_STATE_SHOOTING_RIGHT, BossBirdStates::getRandomNumber,6),

    Death(Constants.BOSS_BIRD_STATE_DEATH, (bound) -> Constants.BOSS_BIRD_STATE_DEATH_CYCLE_COUNT,0);
    final Duration animation;
    final CycleCount cycleCount;
    final int bound;

    BossBirdStates(int animation,CycleCount cycleCount,int bound) {
        this.animation = Duration.millis(animation);
        this.cycleCount = cycleCount;
        this.bound = bound;
    }

    public Duration getAnimation() {
        return animation;
    }
    public interface CycleCount{
       public int returnCycleNumber(int bound);
    }
    public static int getRandomNumber(int bound){
        Random random = new Random();
        return random.nextInt(bound) + 1;
    }

    public CycleCount getCycleCount() {
        return cycleCount;
    }

    public int getBound() {
        return bound;
    }
}
