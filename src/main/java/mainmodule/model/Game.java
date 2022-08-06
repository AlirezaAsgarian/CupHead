package mainmodule.model;

import mainmodule.model.pluginA.Enums.AvatarStates;
import mainmodule.model.pluginA.Enums.BackGround;
import mainmodule.Main;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.pluginA.util.BossBirdStack;
import mainmodule.View.AvatarTransitions.AvatarTransition;
import mainmodule.View.BackGroundTransiton.BackGroundTransition;
import mainmodule.View.BossBirdTransitions.BulletTransition;
import mainmodule.View.Menus.MenuStack;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;

public class Game {

    AvatarBase avatar;
    BossBirdManger bossBirdManger;
    private BackGroundTransition backGroundTransiton;
    private ArrayList<BulletTransition> bulletTransitions = new ArrayList<>();
    private Boolean isEnd;

    public Game(ProgressBar progressBar) {
        bossBirdProgressBar = progressBar;
        this.avatar = new AvatarBase(20.0, 20.0, 109.0, 95.0);
        Avatar.setInstance(this.avatar);
        initializeBossBirdManager();
        isEnd = false;
    }

    private void initializeBossBirdManager() {
        setNullBossBirdInstance();
        this.bossBirdManger = new BossBirdManger(BossBirdStack.getFullBossBirdStack(),new ArrayList<>());
        BossBirdManger.setInstance(bossBirdManger);
    }

    private void setNullBossBirdInstance() {
        BossBird.setInstance(null);
    }


    public void startNewGame() {
        initializeProgressBar();
        initializeBackground();
        System.out.println(MenuStack.getInstance().getTopMenu().getController().getClass());
        initializeAvatar("red", (AnchorPane) MenuStack.getInstance().getTopMenu().getRoot(), Avatar.getInstance());
        BossBird.getInstance().initializeNewBossBirdAndItsTransitions();
        AvatarTransition.getInstance().play();
    }
    public void initializeAvatar(String color, AnchorPane anchorPane, Avatar avatar) {
        Image image = new Image((Main.class.getResource("cuphead_frames/frames/images/" + color + ".png")).toExternalForm());
        avatar.changeAvatarStates(AvatarStates.NORMAL);
        ImagePattern imagePattern = new ImagePattern(image);
        avatar.setImage(imagePattern);
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

    public BackGroundTransition getBackGroundTransition() {
        return backGroundTransiton;
    }

    public ArrayList<BulletTransition> getBulletTransitions() {
        return bulletTransitions;
    }

    public void setEnd(Boolean end) {
        isEnd = end;
    }

    public Boolean isEnd() {
        return isEnd;
    }
}
