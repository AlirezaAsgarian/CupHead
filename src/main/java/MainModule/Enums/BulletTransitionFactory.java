package MainModule.Enums;

import MainModule.Model.Avatar;
import MainModule.Model.BossBirds.BossBird;
import MainModule.Model.BossBirdManger;
import MainModule.Model.Bullet;
import MainModule.View.BossBirdTransitions.BulletTransition;

import java.util.ArrayList;
import java.util.List;

public interface BulletTransitionFactory {


    default public BulletTransition createBulletTransition(Bullets bulletType, int x, int y, Bullet... bullets){
         switch (bulletType){
             case BOSS_BIRD_POULTRY_YELLOW , BOSS_BIRD_POULTRY_PURPLE ->{
                 Bullet bullet = new Bullet(x, y, bulletType, new ArrayList<>(List.of(Avatar.getInstance())));
                 BulletTransition bulletTransition = new BulletTransition(bullet, true);
                 bullet.setBulletTransition(bulletTransition);
                 return bulletTransition;
             }
             case AVATAR_BACK , AVATAR_BOMB , AVATAR_BULLET , AVATAR_MISSLE , AVATAR_TIR_HAVAII -> {
                 Bullet bullet = new Bullet(x, y, bulletType, new ArrayList<>(List.of(BossBird.getInstance())));
                 BulletTransition bulletTransition = new BulletTransition(bullet, false);
                 bullet.setBulletTransition(bulletTransition);
                 return bulletTransition;
             }
             case BED_BOSS_BULLET1 , BED_BOSS_BULLET3 , BED_BOSS_BULLET2 , BED_BOSS_BULLET4 , MINI_BOSS_BULLET_TO_LEFT , MINI_BOSS_BULLET_TO_RIGHT , EGG_BULLETS -> {
                 Bullet bossBirdBullet = BossBirdManger.getInstance().getBossBirdStack().lastElement().getBullet();
                 BulletTransition bossBirdBulletTransition = new BulletTransition(bossBirdBullet, false);
                 bossBirdBullet.setBulletTransition(bossBirdBulletTransition);
                 return bossBirdBulletTransition;
             }
             case  DOCTOR_BOSS_BULLET1,DOCTOR_BOSS_BULLET2 -> {
                 Bullet doctorBossBirdBullet = bullets[0];
                 BulletTransition doctorBossBirdBulletTransition = new BulletTransition(doctorBossBirdBullet, true);
                 doctorBossBirdBullet.setBulletTransition(doctorBossBirdBulletTransition);
                 return doctorBossBirdBulletTransition;
             }
             case MINI_BOSS_BULLET_EGG -> {
                return new BulletTransition(bullets[0], true);
             }
         }
       return null;
    }


}
