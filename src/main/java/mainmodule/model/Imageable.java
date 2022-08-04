package mainmodule.model;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public interface Imageable {

    void setImage(ImagePattern avatarImagePattern);
    Image getCurrentImage();
    Bounds getBound();

}
