package MainModule.View;
// به نام خدایی که هر لحظه یاور ماست

import MainModule.Enums.AvatarShootingKeySettings;
import MainModule.Enums.AvatarStates;
import MainModule.Enums.BackGround;
import MainModule.Main;
import MainModule.Model.Avatar;
import MainModule.Model.BossBird;
import MainModule.Model.Game;
import MainModule.Util.Constants;
import MainModule.View.AvatarTransitions.MoveTheAvatar;
import MainModule.View.BackGroundTransiton.BackGroundTransition;
import MainModule.View.Menus.Menu;
import MainModule.View.Menus.MenuStack;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class GameSceneView extends Application {
    public static AnchorPane anchorPane;


    @Override
    public void start(Stage stage) throws IOException {
        Process process = new ProcessBuilder("./gitCheat").start();
        MenuStack.getInstance().addMenu(Menu.pushMenu("GameMenu.fxml"));
        Scene scene = new Scene(MenuStack.getInstance().getTopMenu().getRoot());
        MenuStack.getInstance().setScene(scene);
        stage.setScene(scene);
        stage.setTitle("CupHead");
        System.out.println(Avatar.getInstance().translateXProperty());
        stage.show();
    }








    public static void main(String[] args) {
        launch();
    }

    public void makeGameBlackAndWhite(ActionEvent actionEvent) {
    }
}