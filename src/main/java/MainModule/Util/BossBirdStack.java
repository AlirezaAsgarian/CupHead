package MainModule.Util;

import MainModule.Model.BossBird;
import MainModule.Model.BossBirdEnums;

import java.util.Stack;

public class BossBirdStack {
    public final static Stack<BossBird> bossBirdStack = new Stack<BossBird>() {{
        push(BossBirdEnums.SECOND_BOSS_BIRD.getBossBird());
        push(BossBirdEnums.FIRST_BOSS_BIRD.getBossBird());
    }};
}
