package mainmodule.model.pluginA.BulletFactories.BedBossBirdBulletFactories;

import mainmodule.model.ModuleAbstractClasses.BulletFactoryCreator;
import mainmodule.model.ModuleAbstractClasses.BulletFactory;

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
