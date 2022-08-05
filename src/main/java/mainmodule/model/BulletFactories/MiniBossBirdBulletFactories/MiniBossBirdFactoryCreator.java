package mainmodule.model.BulletFactories.MiniBossBirdBulletFactories;

import mainmodule.model.BossBirdStates;
import mainmodule.model.BossBirds.BossBird;
import mainmodule.model.BulletFactories.BulletFactoryCreator;
import mainmodule.model.BulletFactory;
import mainmodule.util.Constants;

import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.List;

public class MiniBossBirdFactoryCreator implements BulletFactoryCreator {

    ArrayList<BulletFactory> bulletFactories;

    public MiniBossBirdFactoryCreator() {
        bulletFactories = new ArrayList<>(List.of(new MiniBossBulletToLeft() {},new MiniBossBulletToRight(){}));
    }

    @Override
    public BulletFactory getNewBossBirdBulletFactory(int selectionNumber) {
            return bulletFactories.get(selectionNumber);
    }
}
