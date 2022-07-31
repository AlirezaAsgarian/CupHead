package MainModule.Controllers.BossBirdStateControllers;

import MainModule.Model.BossBirdStates;

public interface ChangeableState {
    BossBirdStates updateBossBirdState(BossBirdStates bossBirdState);
}
