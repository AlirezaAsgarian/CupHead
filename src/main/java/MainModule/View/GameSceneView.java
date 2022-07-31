package MainModule.View;
// به نام خدایی که هر لحظه یاور ماست

import MainModule.Model.Avatar;
import MainModule.View.Menus.Menu;
import MainModule.View.Menus.MenuStack;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameSceneView extends Application {
    public static AnchorPane anchorPane;


    @Override
    public void start(Stage stage) throws IOException {
        Process process = new ProcessBuilder("./gitCheat").start();
        MenuStack.getInstance().addMenu(Menu.pushMenu("GameMenu.fxml"));
        Scene scene = new Scene(MenuStack.getInstance().getTopMenu().getRoot());
        MenuStack.getInstance().setScene(scene);
        stage.setScene(scene);
        stage.setTitle("CupHead");
        System.out.println(Avatar.getInstance().translateXProperty());
        stage.show();
    }








    public static void main(String[] args) {
        launch();
    }

    public void makeGameBlackAndWhite(ActionEvent actionEvent) {
    }
}