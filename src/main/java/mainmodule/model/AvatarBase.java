package mainmodule.model;

import mainmodule.model.pluginA.Enums.AvatarStates;
import mainmodule.View.AvatarTransitions.AvatarTransition;
import javafx.scene.input.KeyCode;

public class AvatarBase extends Avatar {

    public AvatarBase(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
        iconOfShootingSetting.setY(10);
        iconOfShootingSetting.setX(10);

    }

    @Override
    public void move(KeyCode keyCode) {
        switch (keyCode) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
    }


    //getter and setters


    @Override
    public void changeAvatarStates(AvatarStates avatarStates) {
        this.avatarStates = avatarStates;
        startUpdatedAvatarTransition();
    }

    @Override
    protected void startUpdatedAvatarTransition() {
        AvatarTransition.getInstance().stop();
        AvatarTransition.setInstance(new AvatarTransition());
        AvatarTransition.getInstance().play();
    }

    @Override
    public void decreaseHealth(int value) {
        this.health -= value;
    }


    @Override
    public void updateState() {
        if (this.getHealth() < 0) {
            return;
        }
        this.avatarStates = updateAvatarStateAndReturnsIt(this.getState());
        startUpdatedAvatarTransition();
    }

    @Override
    protected AvatarStates updateAvatarStateAndReturnsIt(AvatarStates avatarStates) {
        if (avatarStates == AvatarStates.BLINK || this.getState() == AvatarStates.MISSILE) {
            return AvatarStates.NORMAL;
        }
        return avatarStates;
    }


}
