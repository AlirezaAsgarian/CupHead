package MainModule.Util;

import MainModule.Model.BossBirds.BossBird;
import MainModule.Model.BossBirdEnums;

import java.util.Stack;

public class BossBirdStack {

    public static Stack<BossBird> getFullBossBirdStack() {
        return new Stack<BossBird>() {{
            push(BossBirdEnums.THIRD_BOSS_BIRD.getBossBird());
            push(BossBirdEnums.SECOND_BOSS_BIRD.getBossBird());
            push(BossBirdEnums.FIRST_BOSS_BIRD.getBossBird());
        }};
    }


}
