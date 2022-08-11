package mainmodule.View;
// به نام خدایی که هر لحظه یاور ماست

import mainmodule.model.Avatar;
import mainmodule.View.Menus.Menu;
import mainmodule.View.Menus.MenuStack;
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
        MenuStack.getInstance().pushMenu(Menu.pushMenu("LoginPage.fxml"));
        Scene scene = new Scene(MenuStack.getInstance().getTopMenu().getRoot());
        MenuStack.getInstance().setScene(scene);
        MenuStack.getInstance().setStage(stage);
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