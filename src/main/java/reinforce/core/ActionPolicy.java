package reinforce.core;

import java.util.List;

/**
 * Created by zach reeve on 12/8/2016.
 */
public abstract class ActionPolicy<T extends StateAction> {

    //return the stateaction from the given list for the policy
    public abstract T getStateActionForPolicy(List<Pair<T, Double>> possibleActionsAndQValues, boolean training);
}
