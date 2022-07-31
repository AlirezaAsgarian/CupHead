package MainModule.Model;

import MainModule.Enums.AvatarStates;
import MainModule.Enums.BackGround;
import MainModule.Main;
import MainModule.Model.BossBirds.BossBird;
import MainModule.Util.BossBirdStack;
import MainModule.View.AvatarTransitions.AvatarTransition;
import MainModule.View.BackGroundTransiton.BackGroundTransition;
import MainModule.View.BossBirdTransitions.BulletTransition;
import MainModule.View.Menus.MenuStack;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;

public class Game {

    Avatar avatar;
    BossBirdManger bossBirdManger;
    private BackGroundTransition backGroundTransiton;
    private ArrayList<BulletTransition> bulletTransitions = new ArrayList<>();

    public Game(ProgressBar progressBar) {
        bossBirdProgressBar = progressBar;
        this.avatar = new Avatar(20.0, 20.0, 109.0, 95.0);
        Avatar.setInstance(this.avatar);
        this.bossBirdManger = new BossBirdManger(BossBirdStack.bossBirdStack,new ArrayList<>());
        BossBirdManger.setInstance(bossBirdManger);
    }
    public void startNewGame() {
        initializeProgressBar();
        initializeBackground();
        System.out.println(MenuStack.getInstance().getTopMenu().getController().getClass());
        initializeAvatar("red", (AnchorPane) MenuStack.getInstance().getTopMenu().getRoot(), Avatar.getInstance());
        BossBird.getInstance().initializeNewBossBirdAndItsTransitions();
        AvatarTransition.getInstance(Avatar.getInstance()).play();
    }
    public void initializeAvatar(String color, AnchorPane anchorPane, Avatar avatar) {
        Image image = new Image((Main.class.getResource("cuphead_frames/frames/images/" + color + ".png")).toExternalForm());
        avatar.setAvatarStates(AvatarStates.NORMAL);
        ImagePattern imagePattern = new ImagePattern(image);
        avatar.setFill(imagePattern);
        avatar.setKeyOnPressedAndReleasedAvatar();
    }

    static
    ProgressBar bossBirdProgressBar;
    private void initializeProgressBar() {
        bossBirdProgressBar.setProgress(1);
        bossBirdProgressBar.setPrefHeight(bossBirdProgressBar.getPrefHeight() - 2);
        bossBirdProgressBar.setMinHeight(bossBirdProgressBar.getMaxHeight());
    }
    private void initializeBackground() {
        BackGroundTransition backGroundTransition = new BackGroundTransition(BackGround.BACK_GROUND);
        this.backGroundTransiton = backGroundTransition;
        backGroundTransition.play();
    }

    public static void setBossBirdProgressBar(ProgressBar progressBar) {
        bossBirdProgressBar = progressBar;
    }
    public static ProgressBar getProgressbar() {
        return bossBirdProgressBar;
    }

    public BackGroundTransition getBackGroundTransiton() {
        return backGroundTransiton;
    }

    public ArrayList<BulletTransition> getBulletTransitions() {
        return bulletTransitions;
    }

    public void killGame() {
        TransitionManger.stopTransitions();
    }
}
