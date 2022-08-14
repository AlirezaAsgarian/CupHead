package mainmodule.model;

import mainmodule.model.pluginA.BossBirds.BossBird;
import mainmodule.View.BossBirdTransitions.BossBirdTransitions;
import java.util.ArrayList;
import java.util.Stack;

public class BossBirdManger {
    static BossBirdManger instance;
    Stack<BossBird> bossBirdStack;
    ArrayList<BossBirdTransitions> bossBirdTransitions;

    public static void setInstance(BossBirdManger instance) {
        BossBirdManger.instance = instance;
    }

    public void setBossBirdStack(Stack<BossBird> bossBirdStack) {
        this.bossBirdStack = bossBirdStack;
    }

    public BossBirdManger(Stack<BossBird> bossBirdStack, ArrayList<BossBirdTransitions> bossBirdTransitions) {
        this.bossBirdStack = bossBirdStack;
        this.bossBirdTransitions = bossBirdTransitions;
    }

    public Stack<BossBird> getBossBirdStack() {
        return bossBirdStack;
    }

    public ArrayList<BossBirdTransitions> getBossBirdTransitions() {
        return bossBirdTransitions;
    }

    public void setBossBirdTransitions(ArrayList<BossBirdTransitions> bossBirdTransitions) {
        this.bossBirdTransitions = bossBirdTransitions;
    }

    BossBirdManger(){

    }

    public static BossBirdManger getInstance() {
        return instance;
    }

    public void addBossBirdTransitions(BossBirdTransitions bossBirdTransitions1) {
        this.bossBirdTransitions.add(bossBirdTransitions1);
    }

    public void removeFirstElement() {
        this.bossBirdTransitions.get(0).stop();
        this.bossBirdTransitions.remove(0);
    }

    public void removeBossBirdTransitionByBossBird(BossBird bossBird) {
        for (BossBirdTransitions bossBirdTrans:
             this.bossBirdTransitions) {
            if(bossBirdTrans.getBossBird().equals(bossBird)){
                bossBirdTrans.stop();
                this.bossBirdTransitions.remove(bossBirdTrans);
                return;
            }
        }
    }

}
