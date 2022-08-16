package mainmodule.model.pluginA.BulletFactories.HouseBossBirdBulletFactories;

import mainmodule.model.ModuleAbstractClasses.BulletFactoryCreator;
import mainmodule.model.ModuleAbstractClasses.BulletFactory;

import java.util.ArrayList;
import java.util.List;

public class HouseBossBirdBulletFactoryCreator implements BulletFactoryCreator {
    ArrayList<BulletFactory> bulletFactories = new ArrayList<>(List.of(new HouseBossBirdEggBulletFactory() {}));
    @Override
    public BulletFactory getNewBossBirdBulletFactory(int randomNumber) {
        return bulletFactories.get(0);
    }
}
