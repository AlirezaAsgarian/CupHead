package mainmodule.model.ModuleAbstractClasses;

import javafx.animation.Transition;
import mainmodule.View.AvatarTransitions.AvatarTransition;
import mainmodule.View.BackGroundTransiton.BackGroundTransition;
import mainmodule.View.BossBirdTransitions.BossBirdTransitions;
import java.util.ArrayList;
import java.util.List;


public class TransitionManager {
    static AvatarTransition avatarTransition;
    static List<Transition> bossBirdTransitions = new ArrayList<Transition>();
    static List<Transition> bulletTransitions = new ArrayList<Transition>();
    static List<Transition> backGroundTransitions = new ArrayList<Transition>();
    public static void setAvatarTransition(AvatarTransition newTran) {
        avatarTransition = newTran;
    }

    public static void removeTransition(TransitionType transitionType, Transition bossBirdTransition) {
        switch (transitionType){
            case BOSS_BIRD_TRANSITION -> {bossBirdTransitions.remove((BossBirdTransitions) bossBirdTransition);}
            case BULLET_TRANSITION -> {bulletTransitions.remove(bossBirdTransition);}
            case BACKGROUND_TRANSITION -> {backGroundTransitions.remove((BackGroundTransition) bossBirdTransition);}
        }
    }

    public static void addTransition(TransitionType transitionType, Transition transition) {
        switch (transitionType){
            case BOSS_BIRD_TRANSITION -> {bossBirdTransitions.add((BossBirdTransitions) transition);}
            case BULLET_TRANSITION -> {bulletTransitions.add(transition);}
            case BACKGROUND_TRANSITION -> {backGroundTransitions.add((BackGroundTransition) transition);}
        }
    }

    public static AvatarTransition getAvatarTransition() {
        return avatarTransition;
    }

    public static void stopTransitions() {
        avatarTransition.stop();
        stopTransitionList(bossBirdTransitions);
        stopTransitionList(backGroundTransitions);
        stopTransitionList(bulletTransitions);
    }

    private static void stopTransitionList(List<Transition> transitions) {
        for (Transition tran:
             transitions) {
            tran.stop();
        }
    }

    public static void clearTransitions() {
        bulletTransitions.clear();
        bossBirdTransitions.clear();
        backGroundTransitions.clear();
        avatarTransition = null;
    }
}
