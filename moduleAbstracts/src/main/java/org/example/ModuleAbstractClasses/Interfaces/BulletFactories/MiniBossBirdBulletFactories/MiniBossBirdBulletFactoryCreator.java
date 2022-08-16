package org.example.ModuleAbstractClasses.Interfaces.BulletFactories.MiniBossBirdBulletFactories;

import org.example.ModuleAbstractClasses.Constants.Constants;
import org.example.ModuleAbstractClasses.Interfaces.BulletFactory;
import org.example.ModuleAbstractClasses.Interfaces.BulletFactoryCreator;

import java.util.ArrayList;
import java.util.List;

public class MiniBossBirdBulletFactoryCreator implements BulletFactoryCreator {

    ArrayList<BulletFactory> bulletFactories;
    MiniBossBulletEggFactory miniBossBulletEggFactory;

    public MiniBossBirdBulletFactoryCreator() {
        bulletFactories = new ArrayList<>(List.of(new MiniBossBulletToLeft() {}, new MiniBossBulletToRight(){}));
        miniBossBulletEggFactory = new MiniBossBulletEggFactory() {};
    }

    @Override
    public BulletFactory getNewBossBirdBulletFactory(int selectionNumber) {
        if(selectionNumber == Constants.SPECIAL_BULLET_SELECTION_NUMBER) return miniBossBulletEggFactory;
            return bulletFactories.get(selectionNumber);
    }
}
