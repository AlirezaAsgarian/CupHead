open module CupHeadd {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.mediaEmpty;
    requires javafx.media;
    requires java.logging;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;
    requires org.slf4j;
    requires org.junit.jupiter.params;
    requires org.testfx;
    requires org.testfx.junit5;
    requires  java.sql;
    requires java.sql.rowset;
    requires mysql.connector.java;
    requires org.testfx.junit;
    requires mockito.junit.jupiter;
    requires org.mockito;
    exports mainmodule;
    exports mainmodule.View;
    exports mainmodule.model;
    exports mainmodule.View.AvatarTransitions;
    exports mainmodule.LoginMenu;
    exports mainmodule.model.pluginA.BossBirds;
    exports mainmodule.model.pluginA.BossBirds.bossBirdStateEnums;
}