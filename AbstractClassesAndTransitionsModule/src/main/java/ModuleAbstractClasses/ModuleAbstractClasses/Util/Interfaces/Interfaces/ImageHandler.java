package ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;

public class ImageHandler {
    static Logger logger = LoggerFactory.getLogger(ImageHandler.class);

    public static Image imageFactory(String imagePath) {
        try {
            return new Image(new File("/home/alireza/CupHeadd/MainModule/target/classes/mainmodule/" + imagePath).toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void updateIconOfShooting(ImageView iconOfShooting) {
        //todo fix
    }
}





