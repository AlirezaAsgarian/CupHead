package MainModule.Model;

import MainModule.View.GameController;
import MainModule.View.Menus.MenuStack;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;

public class ImageHandler {



    public static void updateIconOfShooting(ImageView iconOfShooting){
        if(MenuStack.getInstance().getTopMenu().getController() instanceof GameController gameController){
            gameController.setIconOfShooting(iconOfShooting);
        }
    }




}
