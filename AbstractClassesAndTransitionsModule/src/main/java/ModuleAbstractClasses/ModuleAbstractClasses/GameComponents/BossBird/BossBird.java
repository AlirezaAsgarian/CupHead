package ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.BossBird;

import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.Constants;
import ModuleAbstractClasses.ModuleAbstractClasses.Enums.bossBirdStateEnums.BossBirdStates;
import ModuleAbstractClasses.ModuleAbstractClasses.Game;
import ModuleAbstractClasses.ModuleAbstractClasses.Managers.BossBirdManger;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.MenuStack;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;
import java.util.HashMap;

public abstract class BossBird extends Rectangle implements Imageable {
    /***
     * boss bird has one state and one instance and hashmap key : states value : ArrayList of picture for moving and a function for changing state
     */
    public static int health;
    static BossBird instance;
    final int distance_collision_x;
    final int distance_collision_y;
    protected ChangeableState controller;
    protected BossBirdStates bossBirdState;
    protected BulletFactoryCreator bulletFactoryCreator;
    DoubleProperty xCenter;
    DoubleProperty yCenter;
    HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations;
    Image currentImage;

    public BossBird(double v, double v1, double v2, double v3, HashMap<BossBirdStates, ArrayList<ImagePattern>> bossBirdAnimations, int distance_collision_x, int distance_collision_y, BulletFactoryCreator bulletFactoryCreator) {
        super(v, v1, v2, v3);
        this.bossBirdAnimations = bossBirdAnimations;
        this.distance_collision_x = distance_collision_x;
        this.distance_collision_y = distance_collision_y;
        this.bulletFactoryCreator = bulletFactoryCreator;
        xCenter = new SimpleDoubleProperty(this.getX() + this.getWidth() / 2);
        yCenter = new SimpleDoubleProperty(this.getY() + this.getHeight() / 2);
    }


    /***
     * <p>this function is used to get the current bossBird from the boss bird stack<p/>
     * @return return instance : the current boss bird and if instance become null it updates the above stack element that is current boss bird stack and add it to the children
     */
    public static BossBird getInstance() {
        if (instance == null) {
            instance = BossBirdManger.getInstance().getBossBirdStack().lastElement();
        }
        return instance;
    }

    public static void setInstance(BossBird instance) {
        BossBird.instance = instance;
    }

    /***
     * @return returns center x of this rectangle
     */
    public Double getXCenter() {
        return this.getBoundsInParent().getCenterX();
    }

    /***
     * @return returns center y of this rectangle
     */
    public double getYCenter() {
        return this.getBoundsInParent().getCenterY();
    }

    public DoubleProperty yCenterProperty() {
        return yCenter;
    }

    public void updateCenterPoint() {
        this.yCenter.set(this.getY() + this.getHeight() / 2);
        this.xCenter.set(this.getX() + this.getWidth() / 2);
    }

    /***
     * <p>it used to update the boss bird state for next action of <code>bossBird</code></p>
     */
    public void changeState() {
        bossBirdState = controller.updateBossBirdState(bossBirdState);
    }

    /***
     * <p>moves boss bird each frame</p>
     */
    public abstract void moveBossBird();

    protected void addBossBirdToScreen(BossBird b) {
        MenuStack.getInstance().getTopMenu().getRoot().getChildren().add(b);
    }

    /***
     * <p>shows that <code>bossBird</code> state is in shooting mode or not</p>
     * @param frame : <code>bossBird</code> should shoot the bullet in the specific frame of shooting state, this gets us the current frame
     * @return assert is <code>bossBird</code> state is in shooting state and check if is in correct frame
     */
    public abstract boolean isReadyForShooting(int frame);

    public abstract void initializeNewBossBirdAndItsTransitions();

    /***
     * @return returns the appropriate bullet according to situation
     */
    public abstract Location getBulletLocation();

    public abstract BulletFactory getBulletFactory();

    /***
     * <p>check if health of <code>bossBird</code> ended or not, if yes it will pop it from the <code>bossBird</code> stack and set death state for <code>bossBird</code><p/>
     * @return if health of <code>bossBird</code> ended true else false
     */
    public abstract boolean hasHealth();

    /***
     * <p>decrease health of <code>bossBird</code> and the updates the progress bar if the boss bird hasn't been killed yet</p>
     * @param value : amount of decreasing
     */
    public void decreaseHealth(int value) {
        if (this.bossBirdState != BossBirdStates.Death) {
            health -= value;
            Game.getProgressbar().setProgress((double) health / Constants.BOSS_BIRDS_HEALTH);
        }
    }

    /***
     * @return hashmap that includes <code>bossBirdState</code> and its animation
     */
    public HashMap<BossBirdStates, ArrayList<ImagePattern>> getBossBirdAnimations() {
        return bossBirdAnimations;
    }

    /***
     * @return current <code>bossBirdState</code>
     */
    public BossBirdStates getBossBirdState() {
        return bossBirdState;
    }

    public int getDistance_collision_x() {
        return distance_collision_x;
    }

    public int getDistance_collision_y() {
        return distance_collision_y;
    }

    @Override
    public void setImage(ImagePattern imagePattern) {
        this.currentImage = imagePattern.getImage();
        this.setFill(imagePattern);
    }

    @Override
    public Image getCurrentImage() {
        return currentImage;
    }

    @Override
    public Bounds getBound() {
        return this.getBoundsInParent();
    }

    public BulletFactoryCreator getBulletFactoryCreator() {
        return bulletFactoryCreator;
    }

    public BulletFactory getBossBirdBulletFactoryByNumber(int selectionNumber) {
        return bulletFactoryCreator.getNewBossBirdBulletFactory(selectionNumber);
    }
}
