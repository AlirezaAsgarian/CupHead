package mainmodule.model.pluginA.Controllers.CollisionController.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainmodule.Main;
import java.net.URL;

public class TestStage extends Application {
    static AnchorPane anchorPane;
    static ImageView avatarIV;
    static ImageView houseBossBirdIV;
    @Override
    public void start(Stage stage) throws Exception {
        anchorPane = new AnchorPane();
        avatarIV = initializeComponentImageViewAndReturnsIt((Main.class.getResource("cuphead_frames/frames/images/red.png")));
        avatarIV.setId("avatarIV");
        houseBossBirdIV =  initializeComponentImageViewAndReturnsIt(Main.class.getResource("cuphead_frames/frames/BossFly/1.png"));
        houseBossBirdIV.setId("houseBossBirdIV");
        anchorPane.getChildren().addAll(avatarIV,houseBossBirdIV);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    private ImageView initializeComponentImageViewAndReturnsIt(URL Resource) {
        Image i = new Image(Resource.toExternalForm());
        ImageView iv = new ImageView(i);
        return iv;
    }
}
