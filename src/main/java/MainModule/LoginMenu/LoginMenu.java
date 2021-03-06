package MainModule.LoginMenu;

import MainModule.Main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class LoginMenu extends Application {
    static Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        DataBase.getInstanse().serialize();
        scene = new Scene(new FXMLLoader(Main.class.getResource("FXML/LoginMenu.fxml")).load());
        stage.setScene(scene);
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }
    public static void changeRoot(String url) throws IOException {
        scene.setRoot(new FXMLLoader(Main.class.getResource(url)).load());
    }


}
