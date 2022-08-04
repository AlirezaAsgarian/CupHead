package mainmodule.Enums;

import mainmodule.Main;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;

import java.util.ArrayList;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public enum BackGround {
    BACK_GROUND(new ArrayList<>() {{
            add(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Background/images (2)/" + "1" + ".png").toExternalForm()));
            add(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Background/images (2)/" + "15" + ".png").toExternalForm()));
    }});

    final ArrayList<Background> backgrounds;
    final ArrayList<Image> images;
    BackGround( ArrayList<Image> imagesAhead) {
        this.images = imagesAhead;
        this.backgrounds = new ArrayList<>() {{
            imagesAhead.forEach(image -> {

                BackgroundImage backgroundImage = new BackgroundImage(image, NO_REPEAT,
                        NO_REPEAT,
                        BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                add(background);
            });
        }};
    }



    public ArrayList<Image> getBackgrounds() {
        return images;
    }
}
