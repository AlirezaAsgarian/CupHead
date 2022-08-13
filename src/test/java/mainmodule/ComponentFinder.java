package mainmodule;

import javafx.scene.Node;

public interface ComponentFinder {
   public <T extends Node> T find(String query);
}
