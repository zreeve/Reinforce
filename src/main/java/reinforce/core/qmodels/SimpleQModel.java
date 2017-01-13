package reinforce.core.qmodels;

import reinforce.core.Pair;
import reinforce.core.QModel;
import reinforce.core.SARSA;
import reinforce.core.StateAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zach reeve on 12/27/2016.
 */
public class SimpleQModel<T extends StateAction> extends QModel<T> {
    double learning_rate;
    Map<T, Double> Qtable;

    public SimpleQModel (double rate){
        this.learning_rate = rate;
        Qtable = new HashMap<T, Double>();
    }

    public void train(ArrayList<SARSA<T>> lastActions, List<SARSA<T>> historyRecords) {
        for (int i = 0; i < lastActions.size(); i++) {
            SARSA<T> sarsa = lastActions.get(i);
            if (!Qtable.containsKey(sarsa.nextStateAction)) {
                Qtable.put(sarsa.nextStateAction, 0d);
            }
            if (!Qtable.containsKey(sarsa.stateAction)) {
                Qtable.put(sarsa.stateAction, 0d);
            }

            double thisQ = Qtable.get(sarsa.stateAction);
            double nextQ = Qtable.get(sarsa.nextStateAction);
            Qtable.put(sarsa.stateAction, thisQ + learning_rate * (nextQ + sarsa.reward - thisQ));
        }
    }

    public List<Pair<T, Double>> getQValues(List<T> possibleActions) {
        ArrayList<Pair<T, Double>> values = new ArrayList<Pair<T, Double>>();
        for (T sa : possibleActions) {
            values.add(getQValue(sa));
        }

        return values;
    }

    public Pair<T, Double> getQValue(T sa) {
        if (!Qtable.containsKey(sa)) {
            Qtable.put(sa, 0d);
        }

        return new Pair(sa, Qtable.get(sa));
    }
}
