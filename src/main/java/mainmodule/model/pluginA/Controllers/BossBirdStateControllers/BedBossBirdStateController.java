package mainmodule.model.pluginA.Controllers.BossBirdStateControllers;

import mainmodule.model.pluginA.Controllers.gameControllers.GameController;
import mainmodule.model.ModuleAbstractClasses.BossBirdManger;
import mainmodule.model.pluginA.BossBirds.bossBirdStateEnums.BossBirdStates;
import mainmodule.model.pluginA.BossBirds.BedBossBird;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.View.Menus.MenuStack;
import mainmodule.util.ChangeableState;

public class BedBossBirdStateController implements ChangeableState {
    private final BedBossBird bedBossBird;

    public BedBossBirdStateController(BedBossBird bedBossBird) {
        this.bedBossBird = bedBossBird;
    }

    @Override
    public BossBirdStates updateBossBirdState(BossBirdStates bossBirdState) {
        if (bossBirdState == BossBirdStates.Death) {
            //todo end game
            GameController.endGame();
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
