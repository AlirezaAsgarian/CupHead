package MainModule.View;
// به نام خدایی که هر لحظه یاور ماست

import MainModule.Enums.AvatarShootingKeySettings;
import MainModule.Enums.AvatarStates;
import MainModule.Enums.BackGround;
import MainModule.Main;
import MainModule.Model.Avatar;
import MainModule.Model.BossBird;
import MainModule.Util.Constants;
import MainModule.View.AvatarTransitions.MoveTheAvatar;
import MainModule.View.BackGroundTransiton.BackGroundTransition;
import javafx.application.Application;
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

import java.io.IOException;
import java.net.URL;

public class GameSceneView extends Application {
    public static AnchorPane anchorPane;
    @FXML
    static
    ProgressBar bossBirdProgressBar;

    @Override
    public void start(Stage stage) throws IOException {
        URL url = Main.class.getResource("FXML/Game.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        anchorPane = fxmlLoader.load();
        initializeProgressBar();
        initializeBackground();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        initializeAvatar("red", anchorPane, Avatar.getInstance());
        anchorPane.getChildren().get(anchorPane.getChildren().indexOf(Avatar.getInstance())).requestFocus();
        BossBird.getInstance().initializeNewBossBird();
        MoveTheAvatar.getInstance(Avatar.getInstance()).play();
        stage.show();
    }

    private void initializeBackground() {
        new BackGroundTransition(BackGround.BACK_GROUND).play();
    }

    private void initializeProgressBar() {
        bossBirdProgressBar = (ProgressBar) anchorPane.getChildren().get(0);
        bossBirdProgressBar.setProgress(1);
        bossBirdProgressBar.setPrefHeight(bossBirdProgressBar.getPrefHeight() - 2);
        bossBirdProgressBar.setMinHeight(bossBirdProgressBar.getMaxHeight());
    }


    public void initializeAvatar(String color, AnchorPane anchorPane, Avatar avatar) {
        Image image = new Image((Main.class.getResource("cuphead_frames/frames/images/" + color + ".png")).toExternalForm());
        avatar.setAvatarStates(AvatarStates.NORMAL);
        ImagePattern imagePattern = new ImagePattern(image);
        avatar.setFill(imagePattern);
        avatar.setOnKeyPressed(keyEvent -> {
            if (avatar.getKeyEvents().containsKey(keyEvent.getCode())) {
                avatar.getKeyEvents().put(keyEvent.getCode(), true);
            }
            if (avatar.getShootingKeyEvents().containsKey(keyEvent.getCode())) {
                avatar.getShootingKeyEvents().put(keyEvent.getCode(), true);
            }
            if (keyEvent.getCode() == KeyCode.TAB) {
                if (avatar.getState() == AvatarStates.NORMAL) {
                    avatar.setAvatarStates(AvatarStates.NORMAL_BOMBING);
                } else if (avatar.getState() == AvatarStates.NORMAL_BOMBING) {
                    avatar.setAvatarStates(AvatarStates.NORMAL);
                }
            }
            if (keyEvent.getCode() == KeyCode.SPACE) {
                if ((Constants.getCurrentTime() - AvatarShootingKeySettings.MISSLE.getShootingTimeLine().get(keyEvent.getCode())) >= Constants.AVATAR_MISSLE_STATE_ATTACK_RATE) {
                    AvatarShootingKeySettings.MISSLE.getShootingTimeLine().put(keyEvent.getCode(), Constants.getCurrentTime());
                    avatar.setAvatarStates(AvatarStates.MISSLE);
                }
            }
        });
        avatar.setOnKeyReleased(keyEvent -> {
            if (avatar.getKeyEvents().containsKey(keyEvent.getCode())) {
                avatar.getKeyEvents().put(keyEvent.getCode(), false);
            }
            if (avatar.getShootingKeyEvents().containsKey(keyEvent.getCode())) {
                avatar.getShootingKeyEvents().put(keyEvent.getCode(), false);
            }
        });
        anchorPane.getChildren().add(avatar);
    }

    public static ProgressBar getProgressbar() {
        return bossBirdProgressBar;
    }

    public static void main(String[] args) {
        launch();
    }
}