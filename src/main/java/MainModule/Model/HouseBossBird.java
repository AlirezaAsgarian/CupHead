package MainModule.Model;

import MainModule.Util.BossBirdStack;
import MainModule.Util.Constants;
import MainModule.View.BossBirdTransitions.BossBirdTransitions;
import MainModule.View.GameSceneView;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.HashMap;

public class HouseBossBird extends BossBird{
    public HouseBossBird(double v, double v1, double v2, double v3, HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations) {
        super(v, v1, v2, v3, bossBirdAnimations);
        this.bossBirdState = BossBirdStates.FLYING;
    }
    @Override
    public void changeState() {
        if(checkForHealth()) return;
        if(bossBirdState == BossBirdStates.Death){
            BossBird.setInstance(null);
            return;
        }
        if (bossBirdState == BossBirdStates.FLYING) {
            bossBirdState = BossBirdStates.SHOOTING;
        } else if (bossBirdState == BossBirdStates.SHOOTING) {
            bossBirdState = BossBirdStates.FLYING;
        }
    }
    static int direction = 1;
    public void moveBossBird(){
        if(this.getY() + direction * Constants.BOSSBIRD_SPEED + this.getHeight() >= Constants.Max_Height || this.getY()  <= 0){
            direction *= -1;
        }
        this.setY(this.getY() + direction * Constants.BOSSBIRD_SPEED);
    }
}
