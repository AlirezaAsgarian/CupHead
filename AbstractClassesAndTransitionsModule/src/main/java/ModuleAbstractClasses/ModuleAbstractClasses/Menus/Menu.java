package ModuleAbstractClasses.ModuleAbstractClasses.Menus;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Menu {
    static Logger logger = LoggerFactory.getLogger(Menu.class);

    AnchorPane root;
    ViewController controller;

    public Menu(AnchorPane root, ViewController controller) {
        this.root = root;
        this.controller = controller;
    }

    public static Menu pushMenu(String name) {
        try {
            File file = new File("/home/alireza/CupHeadd/MainModule/src/main/resources/mainmodule/FXML/" + name);
            logger.debug("{}", file.getPath());
            FXMLLoader fxmlLoader = new FXMLLoader(file.toURI().toURL());
            AnchorPane root = fxmlLoader.load();
            ViewController viewController = fxmlLoader.getController();
            return new Menu(root, viewController);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
}
