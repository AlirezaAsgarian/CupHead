package mainmodule.model.BulletFactories.DoctorBossBirdBulletFactories;

import mainmodule.model.BulletFactories.BedBossBirdBulletFactories.BedBossBirdBulletFactoryType1;
import mainmodule.model.BulletFactories.BedBossBirdBulletFactories.BedBossBirdBulletFactoryType2;
import mainmodule.model.BulletFactories.BedBossBirdBulletFactories.BedBossBirdBulletFactoryType3;
import mainmodule.model.BulletFactories.BedBossBirdBulletFactories.BedBossBirdBulletFactoryType4;
import mainmodule.model.BulletFactories.BulletFactoryCreator;
import mainmodule.model.BulletFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DoctorBossBirdBulletFactoryCreator implements BulletFactoryCreator {
    ArrayList<BulletFactory> bulletFactories;

    public DoctorBossBirdBulletFactoryCreator() {
        bulletFactories = new ArrayList<>(List.of(new DoctorBossBirdBulletFactoryType1() {}, new DoctorBossBirdBulletFactoryType2() {}));
    }

    @Override
    public BulletFactory getNewBossBirdBulletFactory(int random){
        if(random == 0){
            return this.bulletFactories.get(0);
            } else {
            return this.bulletFactories.get(1);
        }
    }
}
