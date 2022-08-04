package mainmodule.Controllers.BossBirdStateControllers;

import mainmodule.model.BossBirdStates;

public interface ChangeableState {
    BossBirdStates updateBossBirdState(BossBirdStates bossBirdState);
}
