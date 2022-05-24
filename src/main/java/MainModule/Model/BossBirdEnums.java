package MainModule.Model;

import MainModule.Main;
import MainModule.Util.Constants;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public enum BossBirdEnums {
    FIRST_BOSS_BIRD(new HouseBossBird(Constants.Max_Width - 500, Constants.Max_Height - 500, Constants.BOSS_1_WIDTH, Constants.BOSS_1_HEIGHT, new HashMap<>(Map.of(BossBirdStates.FLYING, new ArrayList<ImagePattern>() {{
        for (int i = 1; i <= 6; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/BossFly/" + i + ".png").toExternalForm())));
        }
    }}, BossBirdStates.SHOOTING, new ArrayList<ImagePattern>() {{
        for (int i = 1; i <= 12; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/BossShoot/" + i + ".png").toExternalForm())));
        }
    }}, BossBirdStates.Death, new ArrayList<ImagePattern>() {{
        for (int i = 1; i <= 30; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 1/Death/bird_large_death_00" + i + ".png").toExternalForm())));
        }
    }}))), Constants.BOSS_BIRD1_HEALTH), SECOND_BOSS_BIRD(new MiniBossBird(Constants.Max_Width - 500, Constants.Max_Height - 500, Constants.BOSS_2_WIDTH, Constants.BOSS_2_HEIGHT, new HashMap<>(Map.of(BossBirdStates.TURN_TO_RIGHT, new ArrayList<ImagePattern>() {{
        for (int i = 1; i <= 6; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Turn/egghead_turn_000" + i + ".png").toExternalForm())));
        }
    }}, BossBirdStates.TURN_TO_LEFT, new ArrayList<ImagePattern>() {{
        for (int i = 1; i <= 6; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Turn/Flipped/egghead_turn_flipped_000" + i + ".png").toExternalForm())));
        }
    }}, BossBirdStates.SHOOTING_TO_RIGHT, new ArrayList<ImagePattern>() {{
        for (int i = 1; i <= 20; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Shoot/Flipped/*_00" + i + ".png").toExternalForm())));
        }
    }}, BossBirdStates.RIGHT, new ArrayList<ImagePattern>() {{
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Shoot/Flipped/*_0020.png").toExternalForm())));
    }}, BossBirdStates.SHOOTING_TO_LEFT, new ArrayList<ImagePattern>() {{
        for (int i = 1; i <= 20; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Shoot/egghead_shoot_00" + i + ".png").toExternalForm())));
        }
    }}, BossBirdStates.LEFT, new ArrayList<ImagePattern>() {{
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Shoot/egghead_shoot_0020.png").toExternalForm())));
    }}, BossBirdStates.Death, new ArrayList<ImagePattern>() {{
        for (int i = 1; i <= 12; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Death/00" + i + ".png").toExternalForm())));
        }
    }}))),Constants.AVATAR_HEALTH);

    BossBird bossBird;
    final int health;

    BossBirdEnums(BossBird bossBird, int health) {
        this.bossBird = bossBird;
        this.health = health;
    }

    public BossBird getBossBird() {
        return bossBird;
    }
}
