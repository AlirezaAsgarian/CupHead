package MainModule.Util;

import MainModule.Model.BossBird;
import MainModule.Model.BossBirdEnums;

import java.util.Stack;

public class BossBirdStack {
    public static Stack<BossBird> bossBirdStack = new Stack<BossBird>() {{
        push(BossBirdEnums.THIRD_BOSS_BIRD.getBossBird());
        push(BossBirdEnums.SECOND_BOSS_BIRD.getBossBird());
        push(BossBirdEnums.FIRST_BOSS_BIRD.getBossBird());
    }};

    public static void setBossBirdStack(Stack<BossBird> bossBirdStack) {
        BossBirdStack.bossBirdStack = bossBirdStack;
    }

    public static Stack<BossBird> getBossBirdStack() {
        return bossBirdStack;
    }
}
