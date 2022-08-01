package MainModule.View;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class EndGameDialog extends Dialog<String> {
    ToggleButton retry;
    ToggleButton backToMenu;

    public EndGameDialog(String winOrLose) {
        buildUI(winOrLose);
        initializeFooterButtons();
        setCallBackOfDialog();
    }

    private void setCallBackOfDialog() {
        setResultConverter((c) -> {
            if(retry.isSelected()){
                return "Retry";
            } else {
                return "Back To Menu";
            }
        });
    }

    private void initializeFooterButtons() {
        this.getDialogPane().getButtonTypes().addAll(List.of(ButtonType.OK));
    }




    private void buildUI(String winOrLose) {
        VBox resultVBox;
        if (winOrLose.equals("win")) {
            resultVBox = initializeVbox("You win and your score equals 200");
        } else {
            resultVBox = initializeVbox("You lose and your score equals -200");
        }
        this.getDialogPane().setContent(resultVBox);
    }

    private VBox initializeVbox(String resultMessage) {
        VBox vBox = new VBox();
        setVBoxGeneralGraphics(vBox);
        addResultMessageTextToVbox(resultMessage, vBox);
        addToggleButtons(vBox);
        return vBox;
    }

    private void addToggleButtons(VBox vBox) {
        HBox toggleButtonsHBox = createToggleButtonsHBoxAndSetItsGeneralGraphics();
        this.retry = new ToggleButton();
        this.backToMenu = new ToggleButton();
        initializeRetryAndBackToMenuToggleButtons();
        toggleButtonsHBox.getChildren().addAll(retry,backToMenu);
        vBox.getChildren().add(toggleButtonsHBox);
    }

    private HBox createToggleButtonsHBoxAndSetItsGeneralGraphics() {
        HBox toggleButtonsHBox = new HBox();
        toggleButtonsHBox.setAlignment(Pos.CENTER);
        toggleButtonsHBox.setSpacing(50);
        return toggleButtonsHBox;
    }

    private void initializeRetryAndBackToMenuToggleButtons() {
        retry.setSelected(true);
        retry.setText("Retry");
        backToMenu.setSelected(false);
        backToMenu.setText("Back To Menu");
        retry.setOnAction((a) -> backToMenu.setSelected(false));
        backToMenu.setOnAction((a) -> retry.setSelected(false));
    }


    private void addResultMessageTextToVbox(String resultMessage, VBox vBox) {
        Text text = new Text(resultMessage);
        vBox.getChildren().add(text);
    }

    private void setVBoxGeneralGraphics(VBox vBox) {
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(20);
    }


}
