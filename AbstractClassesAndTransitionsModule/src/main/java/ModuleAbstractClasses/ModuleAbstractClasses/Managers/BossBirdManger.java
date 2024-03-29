package ModuleAbstractClasses.ModuleAbstractClasses.Managers;


import ModuleAbstractClasses.ModuleAbstractClasses.GameComponents.BossBird.BossBird;
import ModuleAbstractClasses.ModuleAbstractClasses.Transitions.BossBirdTransitions.BossBirdTransitions;

import java.util.ArrayList;
import java.util.Stack;

public class BossBirdManger {
    static BossBirdManger instance;
    Stack<BossBird> bossBirdStack;
    ArrayList<BossBirdTransitions> bossBirdTransitions;

    public BossBirdManger(Stack<BossBird> bossBirdStack, ArrayList<BossBirdTransitions> bossBirdTransitions) {
        this.bossBirdStack = bossBirdStack;
        this.bossBirdTransitions = bossBirdTransitions;
    }

    BossBirdManger() {

    }

    public static BossBirdManger getInstance() {
        return instance;
    }

    public static void setInstance(BossBirdManger instance) {
        BossBirdManger.instance = instance;
    }

    public Stack<BossBird> getBossBirdStack() {
        return bossBirdStack;
    }

    public void setBossBirdStack(Stack<BossBird> bossBirdStack) {
        this.bossBirdStack = bossBirdStack;
    }

    public ArrayList<BossBirdTransitions> getBossBirdTransitions() {
        return bossBirdTransitions;
    }

    public void setBossBirdTransitions(ArrayList<BossBirdTransitions> bossBirdTransitions) {
        this.bossBirdTransitions = bossBirdTransitions;
    }

    public void addBossBirdTransitions(BossBirdTransitions bossBirdTransitions1) {
        this.bossBirdTransitions.add(bossBirdTransitions1);
    }

    public void removeFirstElement() {
        this.bossBirdTransitions.get(0).stop();
        this.bossBirdTransitions.remove(0);
    }

    public void removeBossBirdTransitionByBossBird(BossBird bossBird) {
        for (BossBirdTransitions bossBirdTrans :
                this.bossBirdTransitions) {
            if (bossBirdTrans.getBossBird().equals(bossBird)) {
                bossBirdTrans.stop();
                this.bossBirdTransitions.remove(bossBirdTrans);
                return;
            }
        }
    }

}
