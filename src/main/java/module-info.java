open module MainModule {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.mediaEmpty;
    requires javafx.media;
    exports MainModule;
    exports MainModule.View;
    exports MainModule.Model;
    exports MainModule.View.AvatarTransitions;
    exports MainModule.LoginMenu;
}