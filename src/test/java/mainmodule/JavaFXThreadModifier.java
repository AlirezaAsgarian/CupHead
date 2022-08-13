package mainmodule;

import javafx.application.Platform;

public interface JavaFXThreadModifier {
    default public void applyChangeOnJavaFXApplication(Runnable a) {
        Platform.runLater(a);
    }
}
