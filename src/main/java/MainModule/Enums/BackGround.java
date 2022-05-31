package MainModule.Enums;

import MainModule.Main;
import MainModule.Util.Constants;
import MainModule.View.GameSceneView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public enum BackGround {
    BACK_GROUND(new ArrayList<>() {{
        for (int i = 1; i <= 15; i++) {
            add(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Background/images (2)/" + i + ".png").toExternalForm()));
        }
    }},new Rectangle(0,0,Constants.Max_Width,Constants.Max_Height),new Rectangle(-Constants.Max_Width,0,Constants.Max_Width,Constants.Max_Height));
    final ArrayList<Image> backgrounds;
    final Rectangle rectangleAhead;
    final Rectangle rectangleBack;

    BackGround(ArrayList<Image> imagesAhead, Rectangle rectangleAhead, Rectangle rectangleBack) {
        this.backgrounds = imagesAhead;
        this.rectangleAhead = rectangleAhead;
        this.rectangleBack = rectangleBack;
        rectangleAhead.setFill(new ImagePattern(imagesAhead.get(0)));
        rectangleBack.setFill(new ImagePattern(imagesAhead.get(imagesAhead.size() - 1)));
        GameSceneView.anchorPane.getChildren().add(rectangleAhead);
        GameSceneView.anchorPane.getChildren().add(rectangleBack);
    }

    public Rectangle getRectangleAhead() {
        return rectangleAhead;
    }

    public Rectangle getRectangleBack() {
        return rectangleBack;
    }

    public static BackGround getInstance() {
        return BACK_GROUND;
    }

    public ArrayList<Image> getBackgrounds() {
        return backgrounds;
    }
}
