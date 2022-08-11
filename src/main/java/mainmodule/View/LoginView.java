package mainmodule.View;
import mainmodule.View.Menus.Menu;
import mainmodule.View.Menus.MenuStack;
import mainmodule.util.DatabaseConnectors.UserDatabase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import mainmodule.View.Menus.ViewController;

//todo : improve line 80
public class LoginView implements ViewController {

    private final String[] choiceBoxOptions = {"Register", "Login"};
    @FXML
    private HBox buttonBox;
    @FXML
    private VBox mainBox;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField nickname;
    @FXML
    private VBox usernameBox;
    @FXML
    private VBox nicknameBox;
    @FXML
    private VBox passwordBox;
    private boolean nicknameFieldOn;

    public void initialize() {
        choiceBox.setValue("Login");
        createButton();
        nicknameFieldOn = false;
        choiceBox.getItems().addAll(choiceBoxOptions);
        choiceBox.setOnAction(this::choiceBoxAction);
        createNicknameField();
    }

    private void choiceBoxAction(ActionEvent event) {
        if (choiceBox.getValue().equals("Login")) {
            mainBox.getChildren().remove(nicknameBox);
            createNicknameField();
            nicknameFieldOn = false;
            buttonBox.getChildren().remove(0);
            buttonBox.getChildren().add(loginButton);
        } else if (choiceBox.getValue().equals("Register")) {
            nicknameFieldOn = true;
            if (!mainBox.getChildren().contains(nicknameBox))
                mainBox.getChildren().add(1, nicknameBox);
            buttonBox.getChildren().remove(0);
            buttonBox.getChildren().add(registerButton);
        }
    }

    private void createButton() {
        loginButton = new Button("Login");
        loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loginClick();
            }
        });
        registerButton = new Button("Register");
        registerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                registerClick();
            }
        });
    }

    public void createNicknameField() {
        Text nicknameLabel = new Text("NICKNAME");
        nicknameLabel.setTextAlignment(TextAlignment.CENTER);
        this.nickname = new TextField();
        nickname.setPromptText("Nickname");
        nickname.setAlignment(Pos.CENTER);
        nickname.setOnKeyPressed(keyEvent -> LoginView.this.enterNickname());
        this.nicknameBox = new VBox(nicknameLabel, nickname);
        nicknameBox.setSpacing(10);
        nicknameBox.setAlignment(Pos.CENTER);
    }


    public void enterUsername() {
        removeAdditional();
        if (username.getText().length() > 12) {
            username.setStyle("-fx-border-color: #ff0066; -fx-border-radius: 5; -fx-border-width: 3;");
            Text lengthError = new Text("Username's Length Can't Be More Than 12");
            lengthError.setStyle("-fx-fill: #ff0066; -fx-font-size: 10");
            usernameBox.getChildren().add(lengthError);
        }
    }

    public void enterNickname() {
        removeAdditional();
        if (nickname.getText().length() > 12) {
            nickname.setStyle("-fx-border-color: #ff0066; -fx-border-radius: 5; -fx-border-width: 3;");
            Text lengthError = new Text("Nickname's Length Can't Be More Than 12");
            lengthError.setStyle("-fx-fill: #ff0066; -fx-font-size: 10");
            nicknameBox.getChildren().add(lengthError);
        }
    }

    public void enterPassword() {
        removeAdditional();

    }

    private void removeAdditional() {
        removeBorder(username,nickname,password);
        removeSecondChildrenOfBox(usernameBox,nicknameBox,passwordBox);
    }

    private void removeSecondChildrenOfBox(VBox... vBoxes) {
        for (VBox vb:
             vBoxes){
            if (vb.getChildren().size() > 2)
                vb.getChildren().remove(2);
        }
    }

    private void removeBorder(TextField... username) {
        for (TextField t: username)
         t.setStyle("-fx-border-color: none;");
    }

    public void loginClick() {
        removeAdditional();
        if (emptyUsernameAndOrPassword())
            return;
        if(UserDatabase.getInstance().isLoginPossible(username.getText(),password.getText())){
            MenuStack.getInstance().pushMenu(Menu.pushMenu("GameMenu.fxml"));
        }
    }

    public void registerClick() {
        removeAdditional();
        if (emptyUsernameAndOrPassword())
            return;
        if(UserDatabase.getInstance().isRegisterPossible(username.getText(),password.getText(),nickname.getText())){
            UserDatabase.getInstance().registerUser(username.getText(),password.getText(),nickname.getText());
        }
    }



    private boolean emptyUsernameAndOrPassword() {
        boolean isEmpty = false;
        if (textBoxIsEmpty(username)) {
            username.setStyle("-fx-border-color: #ff0066; -fx-border-radius: 5; -fx-border-width: 3;");
            isEmpty = true;
        }
        if (textBoxIsEmpty(password)) {
            password.setStyle("-fx-border-color: #ff0066; -fx-border-radius: 5; -fx-border-width: 3;");
            isEmpty = true;
        }
        if (textBoxIsEmpty(nickname) && nicknameFieldOn) {
            nickname.setStyle("-fx-border-color: #ff0066; -fx-border-radius: 5; -fx-border-width: 3;");
            isEmpty = true;
        }
        return isEmpty;
    }

    private boolean textBoxIsEmpty(TextField textField) {
        return textField.getText().length() == 0;
    }

    public void exitClick() {
        System.exit(0);
    }
}
