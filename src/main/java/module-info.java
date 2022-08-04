open module MainModule {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.mediaEmpty;
    requires javafx.media;
    exports mainmodule;
    exports mainmodule.View;
    exports mainmodule.model;
    exports mainmodule.View.AvatarTransitions;
    exports mainmodule.LoginMenu;
    exports mainmodule.model.BossBirds;
}