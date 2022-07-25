package MainModule.LoginMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class LoginMenuController {
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    Text Error;

    public void Login(ActionEvent actionEvent) throws IOException {
        String username = this.username.getText();
        String password = this.password.getText();
        if(DataBase.getInstanse().checkForError(username,password).equals("ok")){
            System.out.println("ahsant");
            LoginMenu.changeRoot("FXML/MainMenu.fxml");
        }else {
             DataBase.getInstanse().addUser(username,password);
             Error.setVisible(true);
        }
    }

}
