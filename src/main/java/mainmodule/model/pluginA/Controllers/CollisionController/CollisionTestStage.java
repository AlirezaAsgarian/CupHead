package mainmodule.model.pluginA.Controllers.CollisionController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainmodule.Main;
import java.net.URL;

public class CollisionTestStage extends Application {
    static AnchorPane anchorPane;
    static ImageView avatarIV;
    static ImageView houseBossBirdIV;
    @Override
    public void start(Stage stage) throws Exception {
        initializeStageComponents();
        initializeStageScene(stage);
        stage.show();
    }

    private void initializeStageComponents() {
        initializeHouseBossBirdImageView();
        initializeAvatarImageView();
        initializeMainPageAnchorPane();
    }

    private void initializeStageScene(Stage stage) {
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
    }

    private void initializeMainPageAnchorPane() {
        anchorPane = new AnchorPane();
        anchorPane.setId("mainAnchorPane");
        anchorPane.setPrefSize(houseBossBirdIV.getImage().getWidth(),houseBossBirdIV.getImage().getHeight());
        anchorPane.getChildren().addAll(avatarIV,houseBossBirdIV);
    }

    private void initializeAvatarImageView() {
        avatarIV = initializeComponentImageViewAndReturnsIt((Main.class.getResource("cuphead_frames/frames/images/red.png")));
        avatarIV.setId("avatarIV");
    }

    private void initializeHouseBossBirdImageView() {
        houseBossBirdIV =  initializeComponentImageViewAndReturnsIt(Main.class.getResource("cuphead_frames/frames/BossFly/1.png"));
        houseBossBirdIV.setId("houseBossBirdIV");
    }

    private ImageView initializeComponentImageViewAndReturnsIt(URL Resource) {
        Image i = new Image(Resource.toExternalForm());
        ImageView iv = new ImageView(i);
        return iv;
    }
}
