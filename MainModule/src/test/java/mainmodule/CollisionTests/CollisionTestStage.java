package mainmodule.CollisionTests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
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

    private void initializeMainPageAnchorPane() {
        anchorPane = new AnchorPane();
        anchorPane.setId("mainAnchorPane");
        anchorPane.setPrefSize(houseBossBirdIV.getImage().getWidth(), houseBossBirdIV.getImage().getHeight());
        anchorPane.getChildren().addAll(avatarIV, houseBossBirdIV);
    }

    private void initializeAvatarImageView() {
        try {
            avatarIV = initializeComponentImageViewAndReturnsIt((new File("/home/alireza/CupHeadd/MainModule/target/classes/mainmodule/cuphead_frames/frames/images/red.png").toURI().toURL()));
            avatarIV.setId("avatarIV");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeHouseBossBirdImageView() {
        try {
            houseBossBirdIV = initializeComponentImageViewAndReturnsIt(new File("/home/alireza/CupHeadd/MainModule/target/classes/mainmodule/cuphead_frames/frames/BossFly/1.png").toURI().toURL());
            houseBossBirdIV.setId("houseBossBirdIV");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private ImageView initializeComponentImageViewAndReturnsIt(URL Resource) {
        Image i = new Image(Resource.toExternalForm());
        ImageView iv = new ImageView(i);
        return iv;
    }

    private void initializeStageScene(Stage stage) {
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
    }
}
