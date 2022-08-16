package org.example.ModuleAbstractClasses.Interfaces.BulletFactories.DoctorBossBirdBulletFactories;

import org.example.ModuleAbstractClasses.Interfaces.BulletFactory;
import org.example.ModuleAbstractClasses.Interfaces.BulletFactoryCreator;

import java.util.ArrayList;
import java.util.List;

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
