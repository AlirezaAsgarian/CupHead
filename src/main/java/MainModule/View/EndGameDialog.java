package MainModule.View;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.List;

public class EndGameDialog extends Dialog<String> {

    public EndGameDialog(String winOrLose){
        setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent dialogEvent) {
                dialogEvent.consume();
            }
        });
        buildUI(winOrLose);
        handleResult();
    }

    private void handleResult() {
        this.getDialogPane().getButtonTypes().addAll(List.of(ButtonType.OK,ButtonType.CANCEL));
        Button retry = (Button) this.getDialogPane().lookupButton(ButtonType.OK);
        retry.setText("Retry");
        Button backToMenu = (Button) this.getDialogPane().lookupButton(ButtonType.CANCEL);
        backToMenu.setText("Back to Menu");
        this.setResultConverter(new Callback<ButtonType, String>() {
            @Override
            public String call(ButtonType buttonType) {
                System.out.println("pop up button fired ");
                System.out.println(buttonType.getText());
                if(buttonType.getText().equals("OK")){
                    return "Retry";
                } else {
                    return "Back";
                }
            }
        });
    }

    private void buildUI(String winOrLose) {
        if(winOrLose.equals("win")){
            VBox vBox = new VBox();
            Text text = new Text("You win and your score equals 200");
            vBox.getChildren().add(text);
            vBox.setPadding(new Insets(10));
            this.getDialogPane().setContent(vBox);
        } else {
            VBox vBox = new VBox();
            Text text = new Text("You lose and your score equals -200");
            vBox.getChildren().add(text);
            vBox.setPadding(new Insets(10));
            this.getDialogPane().setContent(vBox);
        }
    }


}
