package MainModule.Controllers.BossBirdStateControllers;

import MainModule.Model.BossBirdManger;
import MainModule.Model.BossBirdStates;
import MainModule.Model.BossBirds.BedBossBird;
import MainModule.Model.BossBirds.BossBird;
import MainModule.Model.BossBirds.DoctorBossBird;
import MainModule.View.Menus.MenuStack;

public class BedBossBirdStateController implements ChangeableState{
    private final BedBossBird bedBossBird;

    public BedBossBirdStateController(BedBossBird bedBossBird) {
        this.bedBossBird = bedBossBird;
    }

    @Override
    public BossBirdStates updateBossBirdState(BossBirdStates bossBirdState) {
        if (bossBirdState == BossBirdStates.Death) {
            //todo end game
            System.exit(0);
        }
        if (!bedBossBird.hasHealth()) {
          //  removeBedBossBirdAndDoctorBossBirdsFromScreen();
            stopBossBirdTransitions();
            return BossBirdStates.Death;
        }
        return getBossBirdStatesAfterNormalAction(bossBirdState);
    }

    private BossBirdStates getBossBirdStatesAfterNormalAction(BossBirdStates bossBirdState) {
        if (bossBirdState == BossBirdStates.FLYING) {
            return BossBirdStates.SHOOTING_GARBAGE;
        } else if (bossBirdState == BossBirdStates.SHOOTING_GARBAGE) {
            return BossBirdStates.FLYING;
        }
        return null;
    }

    private void removeBedBossBirdAndDoctorBossBirdsFromScreen() {
        bedBossBird.getDoctorBossBirds().forEach(this::removeBossBirdFromScreen);
        removeBossBirdFromScreen(bedBossBird);
    }

    private void stopBossBirdTransitions() {
        BossBirdManger.getInstance().removeBossBirdTransitionByBossBird(bedBossBird);
    }

    private void removeBossBirdFromScreen(BossBird bossBird) {
        MenuStack.getInstance().getTopMenu().getRoot().getChildren().remove(bossBird);
    }
}
