package mainmodule;

import javafx.application.Platform;

public interface JavaFXThreadModifier {
    default void applyChangeOnJavaFXApplication(Runnable a) {
        Platform.runLater(a);
    }
}
