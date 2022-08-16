package org.example.ModuleAbstractClasses.Interfaces.BulletFactories.PoultryBirdBulletsFactories;

import org.example.ModuleAbstractClasses.Interfaces.BulletFactory;
import org.example.ModuleAbstractClasses.Interfaces.BulletFactoryCreator;

import java.util.ArrayList;
import java.util.List;

public class PoultryBulletFactoryCreator implements BulletFactoryCreator {
    ArrayList<BulletFactory> bulletFactories;

    public PoultryBulletFactoryCreator() {
        bulletFactories = new ArrayList<>(List.of(new PurplePoultryFactory() {},new YellowPoultryFactory(){}));
    }

    @Override
    public BulletFactory getNewBossBirdBulletFactory(int random) {
        if(random == 1){
            return new PurplePoultryFactory() {};
        } else {
            return new YellowPoultryFactory() {};
        }
    }
}
