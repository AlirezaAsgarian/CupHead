package mainmodule.model;

import mainmodule.View.GameViewController;
import mainmodule.View.Menus.MenuStack;
import javafx.scene.image.ImageView;

public class ImageHandler {



    public static void updateIconOfShooting(ImageView iconOfShooting){
        if(MenuStack.getInstance().getTopMenu().getController() instanceof GameViewController gameViewController){
            gameViewController.setIconOfShooting(iconOfShooting);
        }
    }




}
