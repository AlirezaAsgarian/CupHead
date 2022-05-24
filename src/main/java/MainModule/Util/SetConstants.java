package MainModule.Util;

public class SetConstants {
    //todo : needs to set in time which we select the difficulty
    public static int AVATAR_BULLET_DAMAGE_RATIO = 5;
    public static int EGG_BULLET_DAMAGE_RATIO = 5;
    public static int AVATAR_BOMB_DAMAGE_RATIO = 5;

    public static void setEggBulletDamageRatio(int eggBulletDamageRatio) {
        EGG_BULLET_DAMAGE_RATIO = eggBulletDamageRatio;
    }

    public static void setAvatarBulletDamageRatio(int avatarBulletDamageRatio) {
        AVATAR_BULLET_DAMAGE_RATIO = avatarBulletDamageRatio;
    }

    public static void setAvatarBombDamageRatio(int avatarBombDamageRatio) {
        AVATAR_BOMB_DAMAGE_RATIO = avatarBombDamageRatio;
    }
}
