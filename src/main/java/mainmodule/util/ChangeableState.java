package mainmodule.util;

import mainmodule.model.pluginA.BossBirds.bossBirdStateEnums.BossBirdStates;

public interface ChangeableState {
    BossBirdStates updateBossBirdState(BossBirdStates bossBirdState);
}
