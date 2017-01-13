package reinforce.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zach reeve on 12/3/2016.
 */
public class History<T extends StateAction> {
    protected static Random random = new Random();
    long age = 0;
    List<Pair<SARSA<T>, Long>> states;

    public History() {
        states = new ArrayList<Pair<SARSA<T>, Long>>();
    }

    public void addState (SARSA<T> state) {
        states.add(new Pair<SARSA<T>, Long>(state, age));
        age++;
    }

    public void dropBeforeAge (long max_age) {
        List<Pair<SARSA<T>, Long>> new_states = new ArrayList<Pair<SARSA<T>, Long>>();

        for (Pair<SARSA<T>, Long> pair : states) {
            if (pair.right >= max_age) {
                new_states.add(pair);
            }
        }

        states = new_states;
    }

    public List<SARSA<T>> getRandom(int num) {
        if (num <= states.size()) {
            List<SARSA<T>> ret = new ArrayList<SARSA<T>>();
            for (Pair<SARSA<T>, Long> pair : states) {
                ret.add(pair.left);
            }

            return ret;
        }

        List<Integer> indeces = new ArrayList<Integer>();

        while (indeces.size() < num) {
            Integer index = random.nextInt(states.size());

            if (!indeces.contains(index)) {
                indeces.add(index);
            }
        }

        List<SARSA<T>> ret = new ArrayList<SARSA<T>>();
        for (int index : indeces) {
            ret.add(states.get(index).left);
        }

        return ret;
    }

}
