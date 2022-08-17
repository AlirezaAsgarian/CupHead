module AbstractClassesAndTransitionsModule {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;
    requires org.slf4j;
    exports ModuleAbstractClasses.ModuleAbstractClasses;
    exports ModuleAbstractClasses.ModuleAbstractClasses.GameComponents;
    exports ModuleAbstractClasses.ModuleAbstractClasses.Managers;
    exports ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces;
    exports ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar;
    exports ModuleAbstractClasses.ModuleAbstractClasses.Menus;
    exports ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.BossBird;
    exports ModuleAbstractClasses.ModuleAbstractClasses.Transitions.BossBirdTransitions;
    exports ModuleAbstractClasses.ModuleAbstractClasses.Enums;
    exports ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants;
    exports ModuleAbstractClasses.ModuleAbstractClasses.Enums.bossBirdStateEnums;
    exports ModuleAbstractClasses.ModuleAbstractClasses.Controllers.gameControllers;
    exports ModuleAbstractClasses;
    exports ModuleAbstractClasses.ModuleAbstractClasses.Transitions;
    exports ModuleAbstractClasses.ModuleAbstractClasses.Transitions.AvatarTransitions.AvatarTransitions;
    exports ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.AvatarEnums;
    exports ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.AvatarBulletFactories;
    exports ModuleAbstractClasses.ModuleAbstractClasses.Controllers.CollisionController;
    exports ModuleAbstractClasses.ModuleAbstractClasses.Controllers.AvatarControllers;

}