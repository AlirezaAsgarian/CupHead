package mainmodule.model.pluginA.Enums;

import mainmodule.Main;
import mainmodule.model.pluginA.util.Constants;
import mainmodule.model.pluginA.util.SetConstants;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public enum Bullets {
    EGG_BULLETS(Constants.EGG_BULLET_HEIGHT, Constants.EGG_BULLET_WIDTH, Constants.EGG_BULLET_SPEED, SetConstants.EGG_BULLET_DAMAGE_RATIO, MoveFuncs.EGG_BULLET,new ArrayList<>(List.of(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/images/egg.png").toExternalForm())))), 1,Constants.EGG_BULLET_DURATION, 0),
    MINI_BOSS_BULLET_TO_LEFT(Constants.MINI_BOSS_BULLET_HEIGHT, Constants.MINI_BOSS_BULLET_WIDTH, Constants.MINI_BOSS_BULLET_SPEED, SetConstants.MINI_BOSS_BULLET_DAMAGE_RATIO, MoveFuncs.MINI_BOSS_BULLET_TO_LEFT,new ArrayList<>(){{
        for (int i = 1; i <= 6 ; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Bullet/to_left/egghead_bullet_000" + i + ".png").toExternalForm())));
        }
    }}, 1, Constants.MINI_BOSS_BULLET_DURATION,0),
    MINI_BOSS_BULLET_TO_RIGHT(Constants.MINI_BOSS_BULLET_HEIGHT, Constants.MINI_BOSS_BULLET_WIDTH, Constants.MINI_BOSS_BULLET_SPEED, SetConstants.MINI_BOSS_BULLET_DAMAGE_RATIO,MoveFuncs.MINI_BOSS_BULLET_TO_RIGHT,new ArrayList<>(){{
        for (int i = 1; i <= 6 ; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Bullet/to_right/egghead_bullet_000" + i + ".png").toExternalForm())));
        }
    }}, 1, Constants.MINI_BOSS_BULLET_DURATION,0),
    AVATAR_BULLET(Constants.AVATAR_BULLET_HEIGHT,Constants.AVATAR_BULLET_WIDTH,Constants.AVATAR_BULLET_SPEED, SetConstants.AVATAR_BULLET_DAMAGE_RATIO,MoveFuncs.AVATAR_BULLET, new ArrayList<>(){{
        for (int i = 1; i <= 9; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/Plane/Mini/Bullet/bird"+i+".png").toExternalForm())));
        }
    }}, 1,Constants.AVATAR_BULLET_DURATION,0),
    AVATAR_BOMB(Constants.AVATAR_BOMB_HEIGHT,Constants.AVATAR_BOMB_WIDTH,Constants.AVATAR_BOMB_SPEED, SetConstants.AVATAR_BOMB_DAMAGE_RATIO,MoveFuncs.AVATAR_BOMB,new ArrayList<>(List.of(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/images/bullet.png").toExternalForm())))), 1,Constants.AVATAR_BULLET_DURATION,0),
    AVATAR_TIR_HAVAII(Constants.AVATAR_BOMB_HEIGHT,Constants.AVATAR_BOMB_WIDTH,Constants.AVATAR_BOMB_SPEED,SetConstants.AVATAR_TIR_HAVAII_DAMAGE_RATIO,MoveFuncs.AVATAR_TIR_HAVAII,new ArrayList<>(List.of(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/images/bullet.png").toExternalForm())))), 1,Constants.AVATAR_BULLET_DURATION,0),
    //todo : check constanst of this
    AVATAR_BACK(Constants.AVATAR_BOMB_HEIGHT,Constants.AVATAR_BOMB_WIDTH,Constants.AVATAR_BOMB_SPEED,SetConstants.AVATAR_TIR_HAVAII_DAMAGE_RATIO,MoveFuncs.AVATAR_BACK,new ArrayList<>(List.of(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/images/bullet.png").toExternalForm())))), 1,Constants.AVATAR_BULLET_DURATION,0),
    AVATAR_MISSLE(Constants.AVATAR_MISSLE_HEIGHT,Constants.AVATAR_MISSLE_WIDTH,Constants.AVATAR_BOMB_SPEED,SetConstants.AVATAR_TIR_HAVAII_DAMAGE_RATIO,MoveFuncs.AVATAR_MISSLE,new ArrayList<>(){{
        for (int i = 1; i <= 7; i++) {
        add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/Plane/missle/00" + i + ".png").toExternalForm())));
        }
    }}, 1,Constants.AVATAR_MISSLE_DURATION,0),
    MINI_BOSS_BULLET_EGG(Constants.MINI_BOSS_EGG_HEIGHT,Constants.MINI_BOSS_EGG_WIDTH,Constants.MINI_BOSS_EGG_SPEED,SetConstants.MINI_BOSS_EGG_DAMAGE_RATIO,MoveFuncs.MINI_BOSS_BULLET_EGG,new ArrayList<>(){{
        for (int i = 1; i <= 32 ; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Egg/00" + i + ".png").toExternalForm())));
        }
    }}, -1,Constants.MINI_BOSS_BULLET_DURATION,0),

    BED_BOSS_BULLET1(Constants.BED_BOSS_BIRD_BULLET1_HEIGHT,Constants.BED_BOSS_BIRD_BULLET1_WIDTH,Constants.MINI_BOSS_EGG_SPEED,SetConstants.MINI_BOSS_EGG_DAMAGE_RATIO,MoveFuncs.BED_BOSS_BIRD_BULLET,new ArrayList<>(){{
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 3/Attack/shooting/Bullets/garbage0.png").toExternalForm())));
    }}, 1,Constants.BED_BOSS_BULLET_DURATION,0),
      BED_BOSS_BULLET2(Constants.BED_BOSS_BIRD_BULLET2_HEIGHT,Constants.BED_BOSS_BIRD_BULLET2_WIDTH,Constants.MINI_BOSS_EGG_SPEED,SetConstants.MINI_BOSS_EGG_DAMAGE_RATIO,MoveFuncs.BED_BOSS_BIRD_BULLET,new ArrayList<>(){{
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 3/Attack/shooting/Bullets/garbage1.png").toExternalForm())));
    }}, 1,Constants.BED_BOSS_BULLET_DURATION,0),
      BED_BOSS_BULLET3(Constants.BED_BOSS_BIRD_BULLET3_HEIGHT,Constants.BED_BOSS_BIRD_BULLET3_WIDTH,Constants.MINI_BOSS_EGG_SPEED,SetConstants.MINI_BOSS_EGG_DAMAGE_RATIO,MoveFuncs.BED_BOSS_BIRD_BULLET,new ArrayList<>(){{
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 3/Attack/shooting/Bullets/garbage2.png").toExternalForm())));
    }}, 1,Constants.BED_BOSS_BIRD_BULLET_DURATION,0),
    BED_BOSS_BULLET4(Constants.BED_BOSS_BIRD_BULLET4_HEIGHT,Constants.BED_BOSS_BIRD_BULLET4_WIDTH,Constants.MINI_BOSS_EGG_SPEED,SetConstants.MINI_BOSS_EGG_DAMAGE_RATIO,MoveFuncs.BED_BOSS_BIRD_BULLET,new ArrayList<>(){{
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 3/Attack/shooting/Bullets/garbage3.png").toExternalForm())));
    }}, 1,Constants.BED_BOSS_BULLET_DURATION,0),
    DOCTOR_BOSS_BULLET1(Constants.MINI_BOSS_EGG_HEIGHT,Constants.MINI_BOSS_EGG_WIDTH,Constants.MINI_BOSS_EGG_SPEED,SetConstants.MINI_BOSS_EGG_DAMAGE_RATIO,MoveFuncs.BED_BOSS_BIRD_BULLET,new ArrayList<>(){{
        for (int i = 1; i <= 32 ; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 3/Birds/Pills/Pink/Whole/00"+i+".png").toExternalForm())));
        }
    }}, 1,Constants.DOCTOR_BIRD_BULLET_DURATION,0),
    DOCTOR_BOSS_BULLET2(Constants.MINI_BOSS_EGG_HEIGHT,Constants.MINI_BOSS_EGG_WIDTH,Constants.MINI_BOSS_EGG_SPEED,SetConstants.MINI_BOSS_EGG_DAMAGE_RATIO,MoveFuncs.BED_BOSS_BIRD_BULLET,new ArrayList<>(){{
        for (int i = 1; i <= 32 ; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 3/Birds/Pills/Normal/Whole/00"+i+".png").toExternalForm())));
        }
    }}, 1,Constants.DOCTOR_BIRD_BULLET_DURATION,0),
    BOSS_BIRD_POULTRY_PURPLE(Constants.BOSS_BIRD_POULTRY_HEIGHT,Constants.BOSS_BIRD_POULTRY_WIDTH,SetConstants.POULTRY_BOSS_BIRD_SPEED,SetConstants.MINI_BOSS_EGG_DAMAGE_RATIO,MoveFuncs.POULTRY_BOSS_BIRD,new ArrayList<>(){{
        for (int i = 1; i <= 100 ; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/MiniBossFly/purple/"+i+".png").toExternalForm())));
        }
    }}, 1,Constants.BOSS_BIRD_POULTRY_DURATION,7),
    BOSS_BIRD_POULTRY_YELLOW(Constants.BOSS_BIRD_POULTRY_HEIGHT,Constants.BOSS_BIRD_POULTRY_WIDTH,SetConstants.POULTRY_BOSS_BIRD_SPEED,SetConstants.MINI_BOSS_EGG_DAMAGE_RATIO,MoveFuncs.POULTRY_BOSS_BIRD,new ArrayList<>(){{
        for (int i = 1; i <= 100 ; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("cuphead_frames/frames/MiniBossFly/yellow/"+i+".png").toExternalForm())));
        }
        Rectangle rectangle = new Rectangle();
    }}, 1,Constants.BOSS_BIRD_POULTRY_DURATION,7);
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamageRatio() {
        return damageRatio;
    }

    public MoveFuncs getMoveFuncs() {
        return moveFuncs;
    }

    public List<ImagePattern> getImagePattern() {
        return imagePattern;
    }

    public int getCycleCount() {
        return cycleCount;
    }

    public int getCycleDuration() {
        return cycleDuration;
    }

    final int height;
    final int width;
    final int speed;
    final int damageRatio;
    final MoveFuncs moveFuncs;
    final ArrayList<ImagePattern> imagePattern;
    final int cycleCount;
    final int cycleDuration;
    final int health;



    Bullets(int height, int width, int speed, int damageRatio, MoveFuncs moveFuncs, ArrayList<ImagePattern> imagePattern, int cycleCount, int cycleDuration, int health) {
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.damageRatio = damageRatio;
        this.moveFuncs = moveFuncs;
        this.imagePattern = imagePattern;
        this.cycleCount = cycleCount;
        this.cycleDuration = cycleDuration;
        this.health = health;
    }
}
