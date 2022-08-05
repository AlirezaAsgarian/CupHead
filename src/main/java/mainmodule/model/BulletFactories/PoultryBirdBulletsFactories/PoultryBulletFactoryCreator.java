package mainmodule.model.BulletFactories.PoultryBirdBulletsFactories;

import mainmodule.model.BulletFactories.BulletFactoryCreator;
import mainmodule.model.BulletFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoultryBulletFactoryCreator implements BulletFactoryCreator {
    ArrayList<BulletFactory> bulletFactories;

    public PoultryBulletFactoryCreator() {
        bulletFactories = new ArrayList<>(List.of(new PurplePoultryFactory() {},new YellowPoultryFactory(){}));
    }

    @Override
    public BulletFactory getNewBossBirdBulletFactory(int random) {
        if(random == 0){
            return new PurplePoultryFactory() {};
        } else {
            return new YellowPoultryFactory() {};
        }
    }
}
