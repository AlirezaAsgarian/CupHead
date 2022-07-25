package MainModule.View;

import MainModule.View.Menus.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.ProgressBar;

public class GameController implements ViewController {
    public ProgressBar bossBirdProgressBar;

    public void initialize(){
        GameSceneView.setBossBirdProgressBar(bossBirdProgressBar);
    }

    public void makeGameBlackAndWhite(ActionEvent actionEvent) {

    }
}
