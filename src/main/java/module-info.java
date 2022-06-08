open module MainModule {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    exports MainModule;
    exports MainModule.View;
    exports MainModule.Model;
    exports MainModule.View.AvatarTransitions;
}