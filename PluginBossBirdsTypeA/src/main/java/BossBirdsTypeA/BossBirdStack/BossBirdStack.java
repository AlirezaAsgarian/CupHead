package BossBirdsTypeA.BossBirdStack;

import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.BossBird.BossBird;
import BossBirdsTypeA.BossBirds.BossBirdEnums;

import java.util.Stack;

public class BossBirdStack {

    public static Stack<BossBird> getFullBossBirdStack() {
        return new Stack<BossBird>() {{
            push(BossBirdEnums.THIRD_BOSS_BIRD.createNewBossBird());
            push(BossBirdEnums.SECOND_BOSS_BIRD.createNewBossBird());
            push(BossBirdEnums.FIRST_BOSS_BIRD.createNewBossBird());
        }};
    }


}
