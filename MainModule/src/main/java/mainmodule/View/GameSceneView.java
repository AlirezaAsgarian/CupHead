package mainmodule.View;
// به نام خدایی که هر لحظه یاور ماست


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.Menu;
import ModuleAbstractClasses.ModuleAbstractClasses.Menus.MenuStack;

import java.io.IOException;


public class GameSceneView extends Application {
    public static AnchorPane anchorPane;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        MenuStack.getInstance().pushMenu(Menu.pushMenu("LoginPage.fxml"));
        initializeSceneOfStage(stage);
        stage.setTitle("CupHead");
        stage.show();
    }

    private void initializeSceneOfStage(Stage stage) {
        Scene scene = new Scene(MenuStack.getInstance().getTopMenu().getRoot());
        MenuStack.getInstance().setScene(scene);
        MenuStack.getInstance().setStage(stage);
        stage.setScene(scene);
    }


}