package mainmodule.model.pluginA.BulletFactories.MiniBossBirdBulletFactories;

import mainmodule.model.BulletFactoryCreator;
import mainmodule.model.BulletFactory;
import mainmodule.model.pluginA.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class MiniBossBirdFactoryCreator implements BulletFactoryCreator {

    ArrayList<BulletFactory> bulletFactories;
    MiniBossBulletEggFactory miniBossBulletEggFactory;

    public MiniBossBirdFactoryCreator() {
        bulletFactories = new ArrayList<>(List.of(new MiniBossBulletToLeft() {},new MiniBossBulletToRight(){}));
        miniBossBulletEggFactory = new MiniBossBulletEggFactory() {};
    }

    @Override
    public BulletFactory getNewBossBirdBulletFactory(int selectionNumber) {
        if(selectionNumber == Constants.SPECIAL_BULLET_SELECTION_NUMBER) return miniBossBulletEggFactory;
            return bulletFactories.get(selectionNumber);
    }
}
