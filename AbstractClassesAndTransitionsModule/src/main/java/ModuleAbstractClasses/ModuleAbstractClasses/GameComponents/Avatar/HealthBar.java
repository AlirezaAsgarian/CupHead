package ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Avatar;

import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.Constants;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBar {
    Rectangle rectangleMax;
    Rectangle rectangleBackground;
    int MaxHealth;

    public HealthBar(Rectangle owner) {
        rectangleBackground = new Rectangle(owner.getX(), owner.getY() - Constants.FAR_FROM_OWNER_Y, owner.getWidth(), owner.getHeight());
        rectangleMax = new Rectangle(owner.getX(), owner.getY() - Constants.FAR_FROM_OWNER_Y, owner.getWidth(), owner.getHeight());
        rectangleMax.setFill(Color.WHITE);
        rectangleBackground.setFill(Color.GREEN);
    }

    public void updateHealthBar(int health) {
        rectangleBackground.setWidth(((double) health / MaxHealth) * rectangleMax.getWidth());
    }
}
