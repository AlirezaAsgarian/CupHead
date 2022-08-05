package mainmodule.model.BulletFactories;

import mainmodule.model.BulletFactory;

public interface BulletFactoryCreator {
    public BulletFactory getNewBossBirdBulletFactory(int randomNumber);
}
