package ModuleAbstractClasses.ModuleAbstractClasses;

import ModuleAbstractClasses.ModuleAbstractClasses.Controllers.AvatarControllers.KeyHandlingAvatarTypeA;
import ModuleAbstractClasses.ModuleAbstractClasses.Enums.BackGround;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.Avatar;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.AvatarEnums.AvatarStates;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.BossBird.BossBird;
import ModuleAbstractClasses.ModuleAbstractClasses.Managers.BossBirdManger;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ModuleAbstractClasses.ModuleAbstractClasses.Transitions.AvatarTransitions.AvatarTransitions.AvatarTransition;
import ModuleAbstractClasses.ModuleAbstractClasses.Transitions.BackGroundTransiton.BackGroundTransition;
import ModuleAbstractClasses.ModuleAbstractClasses.Transitions.BossBirdTransitions.BulletTransition;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Stack;

public class Game {

    static
    ProgressBar bossBirdProgressBar;
    private final ArrayList<BulletTransition> bulletTransitions = new ArrayList<>();
    Avatar avatar;
    BossBirdManger bossBirdManger;
    private BackGroundTransition backGroundTransiton;
    private Boolean isEnd;

    public Game(ProgressBar progressBar, Stack<BossBird> bossBirdStack) {
        bossBirdProgressBar = progressBar;
        this.avatar = new Avatar(20.0, 20.0, 109.0, 95.0);
        Avatar.setInstance(this.avatar);
        initializeBossBirdManager(bossBirdStack);
        isEnd = false;
    }

    private void initializeBossBirdManager(Stack<BossBird> bossBirdStack) {
        setNullBossBirdInstance();
        this.bossBirdManger = new BossBirdManger(bossBirdStack, new ArrayList<>());
        BossBirdManger.setInstance(bossBirdManger);
    }

    private void setNullBossBirdInstance() {
        BossBird.setInstance(null);
    }

    public static void setBossBirdProgressBar(ProgressBar progressBar) {
        bossBirdProgressBar = progressBar;
    }

    public static ProgressBar getProgressbar() {
        return bossBirdProgressBar;
    }

    public void startNewGame() {
        initializeProgressBar();
        initializeBackground();
        initializeAvatar("red", getGamePageAnchorPane(), Avatar.getInstance());
        BossBird.getInstance().initializeNewBossBirdAndItsTransitions();
        AvatarTransition.getInstance().play();
    }

    private AnchorPane getGamePageAnchorPane() {
        return (AnchorPane) Stage.getWindows().get(0).getScene().lookup("#gamePage");
    }

    public void initializeAvatar(String color, AnchorPane anchorPane, Avatar avatar) {
        avatar.setImage(avatar.getKeyMoves().get(KeyCode.RIGHT).get(0));
        avatar.changeAvatarStates(AvatarStates.NORMAL);
        avatar.setKeyOnPressedAndReleasedAvatar(new KeyHandlingAvatarTypeA());
    }

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
