package mainmodule.model.pluginA.Controllers.BossBirdStateControllers;

import mainmodule.model.pluginA.BossBirds.bossBirdStateEnums.BossBirdStates;
import mainmodule.util.ChangeableState;

public class DoctorBossBirdStateController implements ChangeableState {

    @Override
    public BossBirdStates updateBossBirdState(BossBirdStates bossBirdStates) {
        if (bossBirdStates == BossBirdStates.FLYING) {
            return BossBirdStates.SHOOTING;
        }
        if (bossBirdStates == BossBirdStates.SHOOTING) {
            return BossBirdStates.FLYING;
        }
        return null;
    }
}
