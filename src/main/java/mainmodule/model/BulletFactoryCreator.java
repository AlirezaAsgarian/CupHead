package mainmodule.model;

import mainmodule.model.BulletFactory;

public interface BulletFactoryCreator {
    public BulletFactory getNewBossBirdBulletFactory(int randomNumber);
}
