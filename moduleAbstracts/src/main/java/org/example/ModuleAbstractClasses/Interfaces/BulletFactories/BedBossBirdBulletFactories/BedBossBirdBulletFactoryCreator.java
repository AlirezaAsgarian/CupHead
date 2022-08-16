package org.example.ModuleAbstractClasses.Interfaces.BulletFactories.BedBossBirdBulletFactories;

import org.example.ModuleAbstractClasses.Interfaces.BulletFactory;
import org.example.ModuleAbstractClasses.Interfaces.BulletFactoryCreator;

import java.util.ArrayList;
import java.util.List;

public class BedBossBirdBulletFactoryCreator implements BulletFactoryCreator
{

    ArrayList<BulletFactory> bulletFactories;

    public BedBossBirdBulletFactoryCreator() {
        bulletFactories = new ArrayList<>(List.of(new BedBossBirdBulletFactoryType1() {},new BedBossBirdBulletFactoryType2() {},new BedBossBirdBulletFactoryType3() {},new BedBossBirdBulletFactoryType4() {}));
    }

    @Override
    public BulletFactory getNewBossBirdBulletFactory(int random) {
        return this.bulletFactories.get(random);
    }
}
