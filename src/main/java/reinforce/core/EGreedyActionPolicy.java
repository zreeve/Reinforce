package reinforce.core;

import java.util.List;
import java.util.Random;

/**
 * Created by zach reeve on 12/21/2016.
 */
public class EGreedyActionPolicy<T extends StateAction> extends ActionPolicy<T> {

    private static Random random = new Random();
    double explore;

    public EGreedyActionPolicy (double explore) {
        this.explore = explore;
    }

    public T getStateActionForPolicy(List<Pair<T, Double>> possibleActionsAndQValues, boolean training) {
        if (possibleActionsAndQValues.size() == 1) {
            return possibleActionsAndQValues.get(0).left;
        }

        int highestIndex = -1;
        double highest = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < possibleActionsAndQValues.size(); i++) {
            Pair<T, Double> pair = possibleActionsAndQValues.get(i);

            if (pair.right > highest) {
                highest = pair.right;
                highestIndex = i;
            }
        }

        if (random.nextDouble() > explore) {
            return possibleActionsAndQValues.get(highestIndex).left;
        }

        int index = highestIndex;
        while (index == highestIndex) {
            index = random.nextInt(possibleActionsAndQValues.size());
        }

        return possibleActionsAndQValues.get(index).left;
    }
}
