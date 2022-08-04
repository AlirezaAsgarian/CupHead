package mainmodule.LoginMenu;

import mainmodule.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
