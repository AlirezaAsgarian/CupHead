package MainModule.Model;

import MainModule.Util.Constants;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.HashMap;

public class MiniBossBird extends BossBird{

    public MiniBossBird(double v, double v1, double v2, double v3, HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations) {
        super(v, v1, v2, v3, bossBirdAnimations);
        this.bossBirdState = BossBirdStates.TURN_TO_LEFT;
    }
    @Override
    public void changeState() {


    }
    static int direction = 1;
    @Override
    public void moveBossBird() {
         if(Avatar.getInstance().getX() > this.getX()){
             moveRight();
         }else {
             moveLeft();
         }
         if(Avatar.getInstance().getY() > this.getY()){
             moveDown();
         }else {
             moveUp();
         }
    }
    public void moveUp(){
        if(this.getY() - Constants.BOSS_MINI_BIRD_SPEED <= 0 ) return;
        this.setY(this.getY() - Constants.BOSS_MINI_BIRD_SPEED );
    }
    public void moveDown(){
        if(this.getY() + this.getHeight() + Constants.BOSS_MINI_BIRD_SPEED  >= Constants.Max_Height) return;
        this.setY(this.getY() + Constants.BOSS_MINI_BIRD_SPEED );
    }
    public void moveRight(){
        if(this.getX() + this.getWidth() + Constants.BOSS_MINI_BIRD_SPEED  >= Constants.Max_Width) return;
        this.setX(this.getX() + Constants.BOSS_MINI_BIRD_SPEED );
    }
    public void moveLeft(){
        if(this.getX() - Constants.BOSS_MINI_BIRD_SPEED  <= 0 ) return;
        this.setX(this.getX() - Constants.BOSS_MINI_BIRD_SPEED );
    }

}
