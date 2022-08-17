package BossBirdsTypeA.BossBirds.BossBirdStateControllers;

import ModuleAbstractClasses.ModuleAbstractClasses.Enums.bossBirdStateEnums.BossBirdStates;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.ChangeableState;

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
