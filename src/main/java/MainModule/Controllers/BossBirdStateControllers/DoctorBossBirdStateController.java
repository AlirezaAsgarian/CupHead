package MainModule.Controllers.BossBirdStateControllers;

import MainModule.Model.BossBirdStates;

public class DoctorBossBirdStateController implements ChangeableState{

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
