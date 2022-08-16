package mainmodule.model.pluginA.Enums;

import mainmodule.Main;
import mainmodule.model.ModuleAbstractClasses.Avatar;
import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.model.pluginA.util.Constants;
import mainmodule.model.pluginA.util.SetConstants;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;

public enum MoveFuncs {
    EGG_BULLET(bullet -> {
        bullet.setX(bullet.getX() - bullet.getSpeed());
    },null,new ArrayList<>(){{
        for (int i = 1; i <= 12; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 1/Hit Dust/birdhouse_cannon_dust_00" + i + ".png").toExternalForm())));
        }
    }}, Constants.EGG_BULLET_EXPLOSION_SIZE, Constants.EGG_BULLET_EXPLOSION_TIME),
    AVATAR_BULLET(bullet -> {
        bullet.setX(bullet.getX() + bullet.getSpeed());
    },KeyCode.D,new ArrayList<>(){{
        for (int i = 1; i <= 12; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 1/Hit Dust/birdhouse_cannon_dust_00" + i + ".png").toExternalForm())));
        }
    }},Constants.AVATAR_BULLET_EXPLOSION_SIZE, Constants.AVATAR_BULLET_EXPLOSION_TIME),
    AVATAR_BOMB(bullet -> {
        bullet.setY(bullet.getY() + bullet.getSpeed());
    },KeyCode.S,new ArrayList<>(){{
        for (int i = 1; i <= 12; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 1/Hit Dust/birdhouse_cannon_dust_00" + i + ".png").toExternalForm())));
        }
    }},Constants.AVATAR_BOMB_EXPLOSION_SIZE, Constants.AVATAR_BULLET_EXPLOSION_TIME),
    AVATAR_TIR_HAVAII(bullet -> { bullet.setY(bullet.getY() - bullet.getSpeed());},KeyCode.W,new ArrayList<>(){{
        for (int i = 1; i <= 12; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 1/Hit Dust/birdhouse_cannon_dust_00" + i + ".png").toExternalForm())));
        }
    }},Constants.AVATAR_BULLET_EXPLOSION_SIZE, Constants.AVATAR_BULLET_EXPLOSION_TIME) ,
     AVATAR_BACK(bullet -> { bullet.setX(bullet.getX() - bullet.getSpeed());},KeyCode.A,new ArrayList<>(){{
        for (int i = 1; i <= 12; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 1/Hit Dust/birdhouse_cannon_dust_00" + i + ".png").toExternalForm())));
        }
    }},Constants.AVATAR_BULLET_EXPLOSION_SIZE, Constants.AVATAR_BULLET_EXPLOSION_TIME),
     AVATAR_MISSLE(bullet -> { bullet.setY(bullet.getY() + bullet.getSpeed());},KeyCode.S,new ArrayList<>(){{
        for (int i = 1; i <= 12; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 1/Hit Dust/birdhouse_cannon_dust_00" + i + ".png").toExternalForm())));
        }
    }},Constants.AVATAR_MISSLE_EXPLOSION_SIZE, Constants.AVATAR_MISSLE_EXPLOSION_TIME),
     MINI_BOSS_BULLET_TO_RIGHT(bullet -> { bullet.setX(bullet.getX() + bullet.getSpeed());},KeyCode.W,new ArrayList<>(){{
        for (int i = 1; i <= 12; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 1/Hit Dust/birdhouse_cannon_dust_00" + i + ".png").toExternalForm())));
        }
    }},Constants.MINI_BOSS_EXPLOSION_SIZE, Constants.MINI_BOSS_EXPLOSION_TIME),
     MINI_BOSS_BULLET_TO_LEFT(bullet -> { bullet.setX(bullet.getX() - bullet.getSpeed());},KeyCode.W,new ArrayList<>(){{
        for (int i = 1; i <= 12; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 1/Hit Dust/birdhouse_cannon_dust_00" + i + ".png").toExternalForm())));
        }
    }},Constants.MINI_BOSS_EXPLOSION_SIZE, Constants.MINI_BOSS_EXPLOSION_TIME),
    MINI_BOSS_BULLET_EGG(bullet -> {
        BossBird bossBird = BossBird.getInstance();
        double xDistance = bossBird.getX() - bullet.getX();
        double yDistance = bossBird.getY() - bullet.getY();
        double distance = Math.sqrt(Math.pow(xDistance, 2.0) + Math.pow(yDistance, 2.0));
        if (distance >= Constants.MINI_BOSS_EGG_MAX_DISTANCE || distance <= Constants.MINI_BOSS_EGG_MIN_DISTANCE) {
            bullet.setSpeed(-bullet.getSpeed());
        }
        if (Avatar.getInstance().getX() > BossBird.getInstance().getX()) {
            bullet.setX(BossBird.getInstance().getX()  + Constants.BOSS_MINI_BIRD_SPEED);
        } else {
            bullet.setX(BossBird.getInstance().getX() - Constants.BOSS_MINI_BIRD_SPEED);
        }
        if (Avatar.getInstance().getY() > BossBird.getInstance().getY()) {
            bullet.setY(BossBird.getInstance().getY()  + Constants.BOSS_MINI_BIRD_SPEED);
        } else {
            bullet.setY(BossBird.getInstance().getY() - Constants.BOSS_MINI_BIRD_SPEED);
        }
      },KeyCode.W,new ArrayList<>(){{
        for (int i = 1; i <= 9; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Egg/Death/egghead_egg_death_000" + i + ".png").toExternalForm())));
        }
    }},Constants.MINI_BOSS_EGG_EXPLOSION_SIZE, Constants.MINI_BOSS_EGG_EXPLOSION_TIME),
    BED_BOSS_BIRD_BULLET(bullet -> {bullet.setY(bullet.getY() - Constants.BED_BOSS_BULLET_SPEED);},null,new ArrayList<>(){{
        for (int i = 1; i <= 9; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Egg/Death/egghead_egg_death_000" + i + ".png").toExternalForm())));
        }
    }},Constants.BED_BOSS_BULLET_EXPLOSION_SIZE, Constants.BED_BOSS_BULLET_EXPLOSION_TIME),
     POULTRY_BOSS_BIRD(bullet -> {bullet.setX(bullet.getX() - SetConstants.POULTRY_BOSS_BIRD_SPEED);},null,new ArrayList<>(){{
        for (int i = 1; i <= 9; i++) {
            add(new ImagePattern(new Image(Main.class.getResource("PC_Computer_Cuphead_Dont_Deal_With_the_Devil_Wally_Warbles/Phase 2/Egg/Death/egghead_egg_death_000" + i + ".png").toExternalForm())));
        }
    }},Constants.BOSS_BIRD_POULTRY_EXPLOSION_SIZE, Constants.BOSS_BIRD_POULTRY_EXPLOSION_TIME);

    final Moving moving;
    final KeyCode keyCode;
    final int explosionSize;
    final int explosionTime;
    final ArrayList<ImagePattern> explosion;
    MoveFuncs(Moving moving, KeyCode keyCode, ArrayList<ImagePattern> explosion, int explosionSize, int explosionTime) {
        this.moving = moving;
        this.keyCode = keyCode;
        this.explosion = explosion;
        this.explosionSize = explosionSize;
        this.explosionTime = explosionTime;
    }

    public Moving getMoving() {
        return moving;
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }

    public int getExplosionSize() {
        return explosionSize;
    }

    public int getExplosionTime() {
        return explosionTime;
    }

    public ArrayList<ImagePattern> getExplosion() {
        return explosion;
    }
}
