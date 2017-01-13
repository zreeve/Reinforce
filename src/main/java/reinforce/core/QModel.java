package reinforce.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zach reeve on 12/27/2016.
 */
public abstract class QModel<T extends StateAction> {

    public abstract void train(ArrayList<SARSA<T>> lastActions, List<SARSA<T>> historyRecords);

    public abstract List<Pair<T, Double>> getQValues(List<T> possibleActions);

    public abstract Pair<T, Double> getQValue(T sa);
}
