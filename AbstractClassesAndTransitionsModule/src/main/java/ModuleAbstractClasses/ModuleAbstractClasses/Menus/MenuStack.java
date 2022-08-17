package ModuleAbstractClasses.ModuleAbstractClasses.Menus;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.Bullet;
import javafx.scene.Scene;
import ModuleAbstractClasses.ModuleAbstractClasses.Game;

import java.util.ArrayList;

public class MenuStack {

    static MenuStack instance;
    Scene scene;
    Stage stage;
    ArrayList<Menu> menus;
    Game currentGame;

    MenuStack() {
        menus = new ArrayList<>();
    }

    public static MenuStack getInstance() {
        if (instance == null) instance = new MenuStack();
        return instance;
    }

    public static void setInstance(MenuStack newInstance) {
        instance = newInstance;
    }

    public void pushMenu(Menu menu) {
        menus.add(menu);
        updateScene();
    }

    public void updateScene() {
        if (scene == null) return;
        System.out.println("scene updated" + menus.size());
        scene.setRoot(getTopMenuRoot());
        updateStageSize();
    }

    private void updateStageSize() {
        if (getTopMenuRoot().getPrefHeight() != stage.getHeight()) {
            stage.setHeight(getTopMenuRoot().getPrefHeight());
            stage.setWidth(getTopMenuRoot().getPrefWidth());
        }
    }

    private AnchorPane getTopMenuRoot() {
        return menus.get(menus.size() - 1).getRoot();
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public void backToPreviousMenu() {
        this.popMenu();
    }

    public void popMenu() {
        menus.remove(menus.size() - 1);
        updateScene();
    }

    public void addNodeToCurrentMenuChildrens(Bullet bullet) {
        instance.getTopMenu().getRoot().getChildren().add(bullet);
    }

    public Menu getTopMenu() {
        return this.menus.get(menus.size() - 1);
    }

    public void addNodeToCurrentMenuChildrens(Node bullet) {
        instance.getTopMenu().getRoot().getChildren().add(bullet);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
