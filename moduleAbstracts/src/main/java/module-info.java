module moduleAbstracts {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;
    requires org.slf4j;
    exports org.example.ModuleAbstractClasses;
    exports org.example.ModuleAbstractClasses.GameComponents;
    exports org.example.ModuleAbstractClasses.Managers;
    exports org.example.ModuleAbstractClasses.Interfaces;
    exports org.example.ModuleAbstractClasses.GameComponents.Avatar;
    exports org.example.ModuleAbstractClasses.Menus;
    exports org.example.ModuleAbstractClasses.GameComponents.BossBird;
    exports org.example.ModuleAbstractClasses.Transitions.BossBirdTransitions;
    exports org.example.ModuleAbstractClasses.Enums;
    exports org.example.ModuleAbstractClasses.Constants;
    exports org.example.ModuleAbstractClasses.Enums.bossBirdStateEnums;
    exports org.example.ModuleAbstractClasses.Controllers.gameControllers;
    exports org;
    exports org.example.ModuleAbstractClasses.Interfaces.BulletFactories.MiniBossBirdBulletFactories;

}