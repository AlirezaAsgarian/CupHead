package ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces;


import ModuleAbstractClasses.ModuleAbstractClasses.Enums.bossBirdStateEnums.BossBirdStates;

public interface ChangeableState {
    BossBirdStates updateBossBirdState(BossBirdStates bossBirdState);
}
