package MainModule.LoginMenu;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.util.Callback;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

class Person{
    String name;
    String job;
    String hooman;
    public Person(String name, String job, String hooman) {
        this.name = name;
        this.job = job;
        this.hooman = hooman;
    }

    public String getName() {
        return name;
    }

    public String getHooman() {
        return hooman;
    }

    public String getJob() {
        return job;
    }
}
public class testController implements Initializable {
     @FXML
    public TableView table;
     @FXML
    public TableColumn firstCol;
     @FXML
    public TableColumn secondCol;
    public TableColumn thirdCol;
    MediaPlayer mediaPlayer;
    @FXML
    MenuBar menuBar;
    @FXML
    MediaView mediaView;
    public void clickAction(){

        mediaPlayer.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/MainModule/aa.mp4").toURI().toString()));
        mediaView.setMediaPlayer(mediaPlayer);

        firstCol.setCellValueFactory(new PropertyValueFactory<Person,String>("name"));
        secondCol.setCellValueFactory(new PropertyValueFactory<Person,String>("job"));
        thirdCol.setCellValueFactory(new PropertyValueFactory<Person,String>("hooman"));
        final ObservableList<Person> data =
                FXCollections.observableArrayList(
                        new Person("Jacob", "Smith", "jacob.smith@example.com"),
                        new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
                        new Person("Ethan", "Williams", "ethan.williams@example.com"),
                        new Person("Emma", "Jones", "emma.jones@example.com"),
                        new Person("Michael", "Brown", "michael.brown@example.com")
                );


    }

}

