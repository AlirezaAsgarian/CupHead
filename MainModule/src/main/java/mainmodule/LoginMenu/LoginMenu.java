package mainmodule.LoginMenu;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainmodule.Main;

import java.io.File;
import java.io.IOException;

public class LoginMenu extends Application {
    static Scene scene;

    public static void changeRoot(String url) throws IOException {
        scene.setRoot(new FXMLLoader(Main.class.getResource(url)).load());
    }

    @Override
    public void start(Stage stage) throws Exception {
        DataBase.getInstanse().serialize();
        scene = new Scene(new FXMLLoader(new File("/home/alireza/CupHeadd/MainModule/target/classes/mainmodule/FXML/LoginMenu.fxml").toURI().toURL()).load());
        stage.setScene(scene);
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }


}
