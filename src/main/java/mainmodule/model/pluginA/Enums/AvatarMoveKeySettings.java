package mainmodule.model.pluginA.Enums;

import mainmodule.Main;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public enum AvatarMoveKeySettings {

    NORMAL(new HashMap<KeyCode, Boolean>(Map.of(KeyCode.LEFT, false, KeyCode.RIGHT, false, KeyCode.UP, false, KeyCode.DOWN, false)), new HashMap<>(Map.of(KeyCode.LEFT, new ArrayList<ImagePattern>() {{
        for (int i = 1; i <= 4; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/Plane/Idle/mugman_plane_idle_straight_000" + i + ".png").toExternalForm())));
        }
    }}, KeyCode.RIGHT, new ArrayList<ImagePattern>() {{
        for (int i = 1; i <= 4; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/Plane/Idle/mugman_plane_idle_straight_000" + i + ".png").toExternalForm())));
        }
    }}, KeyCode.UP, new ArrayList<ImagePattern>() {{
        for (int i = 1; i <= 4; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/Plane/Idle/mugman_plane_idle_up_000" + i + ".png").toExternalForm())));
        }
    }}, KeyCode.DOWN, new ArrayList<ImagePattern>() {{
        for (int i = 0; i <= 3; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/Plane/Idle/mugman_plane_idle_down_0001-00" + i + ".png").toExternalForm())));
        }
    }}))),
    MISSLE(new HashMap<KeyCode, Boolean>(Map.of(KeyCode.RIGHT, false, KeyCode.UP, false, KeyCode.DOWN, false)), new HashMap<>(Map.of(KeyCode.RIGHT, new ArrayList<ImagePattern>() {{
        for (int i = 1; i <= 26; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/Plane/Mini/Super/Bomb/Intro/00" + i + ".png").toExternalForm())));
        }
    }}, KeyCode.UP, new ArrayList<ImagePattern>() {{
        for (int i = 1; i <= 26; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/Plane/Mini/Super/Bomb/Intro/00" + i + ".png").toExternalForm())));
        }
    }}, KeyCode.DOWN, new ArrayList<ImagePattern>() {{
       for (int i = 1; i <= 26; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/Plane/Mini/Super/Bomb/Intro/00" + i + ".png").toExternalForm())));
        }
    }})));


    final HashMap<KeyCode, Boolean> moveKeyEvents;
    final HashMap<KeyCode, ArrayList<ImagePattern>> keyMoves;

    AvatarMoveKeySettings(HashMap<KeyCode, Boolean> keyEvents, HashMap<KeyCode, ArrayList<ImagePattern>> keyMoves) {
        this.moveKeyEvents = keyEvents;
        this.keyMoves = keyMoves;
    }

    public HashMap<KeyCode, ArrayList<ImagePattern>> getKeyMoves() {
        return keyMoves;
    }

    public HashMap<KeyCode, Boolean> getMoveKeyEvents() {
        return moveKeyEvents;
    }
}
