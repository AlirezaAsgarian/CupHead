package mainmodule.util;

import mainmodule.model.BossBirds.BossBird;
import mainmodule.model.BossBirdEnums;

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
