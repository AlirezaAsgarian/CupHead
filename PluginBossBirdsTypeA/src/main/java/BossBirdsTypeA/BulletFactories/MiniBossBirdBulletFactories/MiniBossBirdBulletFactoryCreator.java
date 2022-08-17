package BossBirdsTypeA.BulletFactories.MiniBossBirdBulletFactories;

import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.BulletFactoryCreator;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Interfaces.Interfaces.BulletFactory;
import ModuleAbstractClasses.ModuleAbstractClasses.Util.Constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class MiniBossBirdBulletFactoryCreator implements BulletFactoryCreator {

    ArrayList<BulletFactory> bulletFactories;
    MiniBossBulletEggFactory miniBossBulletEggFactory;

    public MiniBossBirdBulletFactoryCreator() {
        bulletFactories = new ArrayList<>(List.of(new MiniBossBulletToLeft() {
        }, new MiniBossBulletToRight() {
        }));
        miniBossBulletEggFactory = new MiniBossBulletEggFactory() {
        };
    }

    @Override
    public BulletFactory getNewBossBirdBulletFactory(int selectionNumber) {
        if (selectionNumber == Constants.SPECIAL_BULLET_SELECTION_NUMBER) return miniBossBulletEggFactory;
        return bulletFactories.get(selectionNumber);
    }
}
