package reinforce.hanoi;

import reinforce.core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zach reeve on 12/10/2016.
 */
public class HanoiStateHandler extends StateHandler<HanoiStateAction> {

    public HanoiStateHandler(ActionPolicy<HanoiStateAction> policy) {
        this.actionPolicy = policy;
    }

    public HanoiStateAction createStartState() {
        return new HanoiStateAction();
    }

    public boolean isTerminal(HanoiStateAction stateAction) {
        return stateAction.state[2] != null
            && stateAction.state[2].size() == 5
            && stateAction.state[2].get(0) == 4
            && stateAction.state[2].get(1) == 3
            && stateAction.state[2].get(2) == 2
            && stateAction.state[2].get(3) == 1
            && stateAction.state[2].get(4) == 0;

    }

    protected List<HanoiStateAction> getPossibleActions(HanoiStateAction stateAction) {
        List<HanoiStateAction> moves = new ArrayList<HanoiStateAction>();

        for (int i = 0; i < stateAction.state.length; i++) {
            if (!stateAction.state[i].empty()) {
                for (int j = stateAction.state.length - 1; j >= 0; j--) {
                    if (i != j && (stateAction.state[j].size() == 0 || stateAction.state[i].peek() < stateAction.state[j].peek())) {
                        HanoiStateAction temp = (HanoiStateAction)stateAction.copy();
                        temp.action[0] = i;
                        temp.action[1] = j;
                        moves.add(temp);
                    }
                }
            }
        }

        return moves;
    }

    protected void advanceStateGivenStateAction(SARSA<HanoiStateAction> sarsa) {
        sarsa.nextStateAction = sarsa.stateAction.copy();
        Integer ring = sarsa.nextStateAction.state[sarsa.nextStateAction.action[0]].pop();
        sarsa.nextStateAction.state[sarsa.nextStateAction.action[1]].push(ring);
        sarsa.reward = isTerminal(sarsa.nextStateAction) ? 1 : -.01;

    }
}
