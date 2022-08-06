package mainmodule.model.pluginA.BulletFactories;

import mainmodule.model.BulletFactory;

public interface BulletFactoryCreator {
    public BulletFactory getNewBossBirdBulletFactory(int randomNumber);
}
