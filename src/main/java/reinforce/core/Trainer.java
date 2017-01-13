package reinforce.core;

import java.util.ArrayList;

/**
 * Created by zach reeve on 12/3/2016.
 */
public class Trainer<T extends StateAction> {

    long max_history_age = 100000;
    int num_states_from_history_per_batch = 2;
    int num_states_from_run_per_batch = 10;


    StateHandler<T> state_handler;
    History<T> history;
    QModel<T> model;

    public Trainer (StateHandler<T> handler, History<T> history, QModel<T> model) {
        state_handler = handler;
        this.history = history;
        this.model = model;
        state_handler.model = model;
    }

    public void runTrainingIterations(int iters) {
        for (int i = 0; i < iters; i++) {
            runTrainingIteration();
        }
    }

    public void runTrainingIteration() {
        ArrayList<SARSA<T>> lastActions = new ArrayList<SARSA<T>>();
        T stateAction = state_handler.createStartState();
        SARSA<T> lastHistoryRecord = null;
        int count = 0;

        while (!state_handler.isTerminal(stateAction)) {
            lastActions.clear();

            while (lastActions.size() < num_states_from_run_per_batch && !state_handler.isTerminal(stateAction)) {
                //advance the state and return a reinforce.core.SARSA object with this state and reward populated
                SARSA<T> historyRecord = state_handler.advance(stateAction, true);
                //set the last histories next state action
                if (lastHistoryRecord != null) {
                    //complete the last history record and add to history and last actions
                    lastHistoryRecord.nextStateAction = historyRecord.stateAction;
                    history.addState(lastHistoryRecord);
                    lastActions.add(lastHistoryRecord);
                }

                //advance state, set last reinforce.core.SARSA to this incomplete reinforce.core.SARSA
                stateAction = (T)historyRecord.nextStateAction.copy();
                lastHistoryRecord = historyRecord;
                count++;
            }

            //here we have a list of the recent reinforce.core.SARSA records and need to train a batch
            model.train(lastActions, history.getRandom(num_states_from_history_per_batch));


        }

        history.addState(lastHistoryRecord);
        lastActions.add(lastHistoryRecord);
        model.train(lastActions, history.getRandom(num_states_from_history_per_batch));

        //clean up history after iteration
        history.dropBeforeAge(history.age - max_history_age);

        System.out.println(count);
    }


}
