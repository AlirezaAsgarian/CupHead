package BossBirdsTypeA.BossBirds.BossBirdStateControllers;


import BossBirdsTypeA.BossBirds.HouseBossBird;
import ModuleAbstractClasses.ModuleAbstractClasses.Enums.bossBirdStateEnums.BossBirdStates;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.BossBird.BossBird;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.ChangeableState;
import ModuleAbstractClasses.ModuleAbstractClasses.Managers.BossBirdManger;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.MenuStack;

public class HouseBossBirdStateController implements ChangeableState {
    private final HouseBossBird houseBossBird;

    public HouseBossBirdStateController(HouseBossBird houseBossBird) {
        this.houseBossBird = houseBossBird;
    }

    @Override
    public BossBirdStates updateBossBirdState(BossBirdStates bossBirdState) {
        if (bossBirdState == BossBirdStates.Death) {
            removeBossBirdFromGameAndUpdateBossBirdManager();
            return BossBirdStates.Death;
        }
        if (!houseBossBird.hasHealth()) {
            return BossBirdStates.Death;
        }
        return getUpdatedBossBirdStateAfterNormalAction(bossBirdState);
    }

    private BossBirdStates getUpdatedBossBirdStateAfterNormalAction(BossBirdStates bossBirdState) {
        if (bossBirdState == BossBirdStates.FLYING) {
            return BossBirdStates.SHOOTING;
        } else if (bossBirdState == BossBirdStates.SHOOTING) {
            return BossBirdStates.FLYING;
        }
        return null;
    }

    private void removeBossBirdFromGameAndUpdateBossBirdManager() {
        updateBossBirdStack();
        stopBossBirdTransition();
        removeBossBird();
        BossBird.getInstance().initializeNewBossBirdAndItsTransitions();
    }

    private void removeBossBird() {
        BossBird.setInstance(null);
        removeBossBirdFromScreen();
    }

    private void removeBossBirdFromScreen() {
        MenuStack.getInstance().getTopMenu().getRoot().getChildren().remove(houseBossBird);
    }

    private void updateBossBirdStack() {
        BossBirdManger.getInstance().getBossBirdStack().pop();
    }

    private void stopBossBirdTransition() {
        BossBirdManger.getInstance().removeBossBirdTransitionByBossBird(houseBossBird);
    }


}
