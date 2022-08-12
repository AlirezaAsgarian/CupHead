package mainmodule.View.Menus;

import mainmodule.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class Menu {

    AnchorPane root;
    ViewController controller;

    public Menu(AnchorPane root, ViewController controller){
        this.root = root;
        this.controller = controller;
    }

    public AnchorPane getRoot() {
        return root;
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }

    public ViewController getController() {
        return controller;
    }

    public void setController(ViewController controller) {
        this.controller = controller;
    }

    public static Menu pushMenu(String name){
        URL resource = Main.class.getResource("FXML/" + name);
        System.out.println(resource);
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        try {
            AnchorPane root = fxmlLoader.load();
            ViewController viewController = fxmlLoader.getController();
            return new Menu(root,viewController);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
