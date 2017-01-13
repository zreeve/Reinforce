package reinforce.core;

import java.util.List;

/**
 * Created by zach reeve on 12/3/2016.
 */
public abstract class StateHandler<T extends StateAction> {

    protected ActionPolicy<T> actionPolicy;
    protected QModel<T> model;

    public abstract T createStartState();

    public abstract boolean isTerminal(T stateAction);

    //state action comes in with just state populated.
    public SARSA<T> advance(T stateAction, boolean training) {
        //get all possible actions from the given state
        List<T> possibleActions = getPossibleActions(stateAction);
        //pair all stateActions with their Q value
        List<Pair<T, Double>> possibleActionsAndQValues = model.getQValues(possibleActions);
        //get the stateaction chosen by the policy
        T thisStateActionPopulated = actionPolicy.getStateActionForPolicy(possibleActionsAndQValues, training);
        //set stateaction is sarsa
        SARSA<T> sarsa = new SARSA<T>();
        sarsa.stateAction = thisStateActionPopulated;
        //populate reward and next state in sarsa
        advanceStateGivenStateAction(sarsa);

        //core.SARSA should have stateAction with both state and action, reward, and a state action with just the state
        return sarsa;

    }

    protected abstract List<T> getPossibleActions(T stateAction);

    //populate the reward and the next state (not action) in the passed in reinforce.core.SARSA
    protected abstract void advanceStateGivenStateAction(SARSA<T> sarsa);
}
