package MainModule.View;

import MainModule.Model.Avatar;
import MainModule.Model.BossBirds.BossBird;
import MainModule.Model.Game;
import MainModule.Util.Constants;
import MainModule.View.Menus.MenuStack;
import MainModule.View.Menus.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class GameController implements ViewController {
    public ProgressBar bossBirdProgressBar;
    public AnchorPane gamePage;
    private ImageView iconOfShooting;

    public void initialize(){
        Game game = new Game(bossBirdProgressBar);
        MenuStack.getInstance().setCurrentGame(game);
        gamePage.getChildren().add(BossBird.getInstance());
        gamePage.getChildren().add(Avatar.getInstance());
        iconOfShooting = Avatar.getInstance().getIconOfShootingSetting();
        gamePage.getChildren().add(iconOfShooting);
        game.startNewGame();
    }

    public void makeGameBlackAndWhite(ActionEvent actionEvent) {
        if(gamePage.getEffect() == null) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            gamePage.setEffect(colorAdjust);
            Constants.BOSS_BIRD_POULTRY_DURATION = 16000;
        }else {
            Constants.BOSS_BIRD_POULTRY_DURATION = 8200;
            gamePage.setEffect(null);
        }
    }

    public void setIconOfShooting(ImageView iconOfShooting) {
        gamePage.getChildren().remove(this.iconOfShooting);
        this.iconOfShooting = iconOfShooting;
        gamePage.getChildren().add(this.iconOfShooting);

    }
}
