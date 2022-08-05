package mainmodule.model.BulletFactories.BedBossBirdBulletFactories;

import mainmodule.model.BulletFactories.BulletFactoryCreator;
import mainmodule.model.BulletFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BedBossBirdBulletFactoryCreator implements BulletFactoryCreator
{

    ArrayList<BulletFactory> bulletFactories;

    public BedBossBirdBulletFactoryCreator() {
        bulletFactories = new ArrayList<>(List.of(new BedBossBirdBulletFactoryType1() {},new BedBossBirdBulletFactoryType2() {},new BedBossBirdBulletFactoryType3() {},new BedBossBirdBulletFactoryType4() {}));
    }

    @Override
    public BulletFactory getNewBossBirdBulletFactory(int random) {
        switch (random){
            case 0 ->  this.bulletFactories.get(0);
            case 1 ->  this.bulletFactories.get(1);
            case 2 ->  this.bulletFactories.get(2);
            case 3 ->  this.bulletFactories.get(3);
        }
        throw new RuntimeException();
    }
}
