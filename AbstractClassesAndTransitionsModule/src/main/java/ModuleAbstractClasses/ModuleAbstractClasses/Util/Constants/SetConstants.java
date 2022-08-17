package ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants;

public class SetConstants {
    //todo : needs to set in time which we select the difficulty
    public static int AVATAR_BULLET_DAMAGE_RATIO = 5;
    public static int EGG_BULLET_DAMAGE_RATIO = 5;
    public static int AVATAR_BOMB_DAMAGE_RATIO = 5;
    public static int MINI_BOSS_DEATH_SPEED = 0;
    public static int AVATAR_TIR_HAVAII_DAMAGE_RATIO = 5;
    public static int AVATAR_MISSLE_DAMAGE_RATIO = 5;
    public static int MINI_BOSS_BULLET_DAMAGE_RATIO = 10;
    public static int MINI_BOSS_EGG_DAMAGE_RATIO = 6;
    public static int POULTRY_BOSS_BIRD_SPEED = 5;
    public static int BOSS_BIRD_POULTRY_Y = 5;


    public static void setEggBulletDamageRatio(int eggBulletDamageRatio) {
        EGG_BULLET_DAMAGE_RATIO = eggBulletDamageRatio;
    }

    public static void setAvatarBulletDamageRatio(int avatarBulletDamageRatio) {
        AVATAR_BULLET_DAMAGE_RATIO = avatarBulletDamageRatio;
    }

    public static void setAvatarBombDamageRatio(int avatarBombDamageRatio) {
        AVATAR_BOMB_DAMAGE_RATIO = avatarBombDamageRatio;
    }

    public static void setMiniBossBulletDamageRatio(int miniBossBulletDamageRatio) {
        MINI_BOSS_BULLET_DAMAGE_RATIO = miniBossBulletDamageRatio;
    }

    public static void setMiniBossEggDamageRatio(int miniBossEggDamageRatio) {
        MINI_BOSS_EGG_DAMAGE_RATIO = miniBossEggDamageRatio;
    }

    public static void setPoultryBossBird(int poultryBossBird) {
        POULTRY_BOSS_BIRD_SPEED = poultryBossBird;
    }

    public static void setBossBirdPoultryY(int bossBirdPoultryY) {
        BOSS_BIRD_POULTRY_Y = bossBirdPoultryY;
    }

    public static int getMiniBossDeathSpeed() {
        return MINI_BOSS_DEATH_SPEED;
    }

    public static void setMiniBossDeathSpeed(int miniBossDeathSpeed) {
        MINI_BOSS_DEATH_SPEED = miniBossDeathSpeed;
    }
}
