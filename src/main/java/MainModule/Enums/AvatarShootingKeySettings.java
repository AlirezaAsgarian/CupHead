package MainModule.Enums;

import MainModule.Util.Constants;
import javafx.scene.input.KeyCode;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public enum AvatarShootingKeySettings {

    NORMAL_BULLETS(new HashMap<>(Map.of(KeyCode.D, false, KeyCode.S, false, KeyCode.W, false, KeyCode.A, false)),
            new HashMap<>(Map.of(KeyCode.D, Bullets.AVATAR_BULLET, KeyCode.S, Bullets.AVATAR_BOMB, KeyCode.W, Bullets.AVATAR_TIR_HAVAII, KeyCode.A, Bullets.AVATAR_BACK)),
            new HashMap<>(Map.of(KeyCode.D, Constants.getCurrentTime(), KeyCode.W, Constants.getCurrentTime(), KeyCode.S, Constants.getCurrentTime(), KeyCode.A, Constants.getCurrentTime())),
            new HashMap<>(Map.of(KeyCode.D, new Pair<>(Constants.AVATAR_BULLET_DISTANCE_FROM_CENTER, 0), KeyCode.W, new Pair<>(0, -Constants.AVATAR_BULLET_DISTANCE_FROM_CENTER),
                    KeyCode.S, new Pair<>(0, Constants.AVATAR_BULLET_DISTANCE_FROM_CENTER), KeyCode.A, new Pair<>(-Constants.AVATAR_BACK_DISTANCE_FROM_CENTER, 0))), "normal"),
    AVATAR_BOMB(new HashMap<>(Map.of(KeyCode.S, false)),
            new HashMap<>(Map.of(KeyCode.S, Bullets.AVATAR_MISSLE)),
            new HashMap<>(Map.of(KeyCode.S, Constants.getCurrentTime())),
            new HashMap<>(Map.of(KeyCode.S, new Pair<>(0, Constants.AVATAR_BULLET_DISTANCE_FROM_CENTER))), "bomb");


    AvatarShootingKeySettings(HashMap<KeyCode, Boolean> shootingKeyEvents, HashMap<KeyCode, Bullets> bullets, HashMap<KeyCode, Long> shootingTimeLine, HashMap<KeyCode, Pair<Integer, Integer>> startCoordinateBullet, String name) {
        this.shootingKeyEvents = shootingKeyEvents;
        this.bullets = bullets;
        this.shootingTimeLine = shootingTimeLine;
        this.startCoordinateBullet = startCoordinateBullet;
        this.name = name;
    }

    HashMap<KeyCode, Boolean> shootingKeyEvents;
    HashMap<KeyCode, Bullets> bullets;
    HashMap<KeyCode, Long> shootingTimeLine;
    HashMap<KeyCode, Pair<Integer, Integer>> startCoordinateBullet;
    final String name;

    public HashMap<KeyCode, Boolean> getShootingKeyEvents() {
        return shootingKeyEvents;
    }

    public HashMap<KeyCode, Bullets> getBullets() {
        return bullets;
    }

    public HashMap<KeyCode, Long> getShootingTimeLine() {
        return shootingTimeLine;
    }

    public HashMap<KeyCode, Pair<Integer, Integer>> getStartCoordinateBullet() {
        return startCoordinateBullet;
    }

    public String getName() {
        return name;
    }

    public static AvatarShootingKeySettings getNextAvatarKey(String name){
        for (AvatarShootingKeySettings shootingKey :
             AvatarShootingKeySettings.values()) {
            if(!name.equals(shootingKey.name)) return shootingKey;
        }
        //never
        return null;
    }

}
