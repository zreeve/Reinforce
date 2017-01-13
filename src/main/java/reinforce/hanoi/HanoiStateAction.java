package reinforce.hanoi;

import reinforce.core.StateAction;

import java.util.Stack;

/**
 * Created by zach reeve on 12/10/2016.
 */
public class HanoiStateAction extends StateAction {

    public Stack<Integer>[] state;
    public int[] action;

    public HanoiStateAction() {
        state = new Stack[5];
        state[0] = new Stack<Integer>();
        state[1] = new Stack<Integer>();
        state[2] = new Stack<Integer>();
        state[3] = new Stack<Integer>();
        state[4] = new Stack<Integer>();

        state[0].push(4);
        state[0].push(3);
        state[0].push(2);
        state[0].push(1);
        state[0].push(0);
        action = new int[2];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HanoiStateAction that = (HanoiStateAction) o;

        for (int i = 0; i < state.length; i++) {
            if (state[i].size() != that.state[i].size()) {
                return false;
            }
            for (int j = 0; j < state[i].size(); j++) {
                if (state[i].get(j) != that.state[i].get(j)) {
                    return false;
                }
            }
        }

        return action[0] == that.action[0] && action[1] == that.action[1];

    }

    @Override
    public int hashCode() {
        int result = 0;

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].size(); j++) {
                result += state[i].get(j) * 31 * (i + 1) + 53 * (j + 1);
            }
        }

        result += (action[0] + 1) * 101;
        result += (action[1] + 1) * 49;
        return result;
    }

    public HanoiStateAction(HanoiStateAction other) {

        state = new Stack[other.state.length];
        action = other.action.clone();

        for (int i = 0; i < other.state.length; i++) {
            state[i] = copyStack(other.state[i]);
        }
    }

    private Stack<Integer> copyStack(Stack<Integer> integers) {
        Stack stack = new Stack();
        for (int i = 0; i < integers.size(); i++) {
            stack.push(integers.get(i));
        }
        return stack;
    }

    public HanoiStateAction copy() {
        return new HanoiStateAction(this);
    }
}
