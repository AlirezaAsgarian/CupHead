package mainmodule.View.UIControllers;

import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.Constants;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar.Avatar;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.BossBird.BossBird;
import ModuleAbstractClasses.ModuleAbstractClasses.Game;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.MenuStack;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import BossBirdsTypeA.BossBirdStack.BossBirdStack;

public class GameViewController implements ViewController {
    public ProgressBar bossBirdProgressBar;
    public AnchorPane gamePage;
    private ImageView iconOfShooting;

    public void initialize() {
        Game game = new Game(bossBirdProgressBar, BossBirdStack.getFullBossBirdStack());
        MenuStack.getInstance().setCurrentGame(game);
        game.startNewGame();
        addGameComponentsToGamePage();
    }

    private void addGameComponentsToGamePage() {
        gamePage.getChildren().add(BossBird.getInstance());
        gamePage.getChildren().add(Avatar.getInstance());
        initializeIconOfShootingAvatar();
    }

    private void initializeIconOfShootingAvatar() {
        iconOfShooting = Avatar.getInstance().getIconOfShootingSetting();
        bindIconOfShootingToAvatarIconOfShooting();
        gamePage.getChildren().add(iconOfShooting);
    }

    private void bindIconOfShootingToAvatarIconOfShooting() {
        Avatar.getInstance().getIconOfShootingSetting().imageProperty().addListener((observableValue, image, t1) -> {
                    iconOfShooting.setImage(t1);
                }
        );
    }

    public void makeGameBlackAndWhite(ActionEvent actionEvent) {
        if (gamePage.getEffect() == null) {
            makeAnchorPaneBlackAndWhite();
        } else {
            mackAnchorPaneColorFull();
        }
    }

    private void mackAnchorPaneColorFull() {
        Constants.BOSS_BIRD_POULTRY_DURATION = 8200;
        gamePage.setEffect(null);
    }

    private void makeAnchorPaneBlackAndWhite() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-1);
        gamePage.setEffect(colorAdjust);
        Constants.BOSS_BIRD_POULTRY_DURATION = 16000;
    }

}
