package BossBirdsTypeA.BulletFactories.HouseBossBirdBulletFactories;

import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.BulletFactoryCreator;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.BulletFactory;

import java.util.ArrayList;
import java.util.List;

public class HouseBossBirdBulletFactoryCreator implements BulletFactoryCreator {
    ArrayList<BulletFactory> bulletFactories = new ArrayList<>(List.of(new HouseBossBirdEggBulletFactory() {
    }));

    @Override
    public BulletFactory getNewBossBirdBulletFactory(int randomNumber) {
        return bulletFactories.get(0);
    }
}
