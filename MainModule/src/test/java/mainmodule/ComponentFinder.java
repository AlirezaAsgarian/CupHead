package mainmodule;

import javafx.scene.Node;

public interface ComponentFinder {
    <T extends Node> T find(String query);
}
