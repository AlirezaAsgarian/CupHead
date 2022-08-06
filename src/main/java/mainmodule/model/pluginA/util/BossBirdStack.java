package mainmodule.model.pluginA.util;

import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.pluginA.BossBirds.BossBirdEnums;

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
