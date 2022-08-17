package BossBirdsTypeA.BulletFactories.DoctorBossBirdBulletFactories;

import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.BulletFactoryCreator;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.BulletFactory;

import java.util.ArrayList;
import java.util.List;

public class DoctorBossBirdBulletFactoryCreator implements BulletFactoryCreator {
    ArrayList<BulletFactory> bulletFactories;

    public DoctorBossBirdBulletFactoryCreator() {
        bulletFactories = new ArrayList<>(List.of(new DoctorBossBirdBulletFactoryType1() {
        }, new DoctorBossBirdBulletFactoryType2() {
        }));
    }

    @Override
    public BulletFactory getNewBossBirdBulletFactory(int random) {
        if (random == 0) {
            return this.bulletFactories.get(0);
        } else {
            return this.bulletFactories.get(1);
        }
    }
}
