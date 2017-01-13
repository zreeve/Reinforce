package reinforce.core.qmodels;

import reinforce.core.Pair;
import reinforce.core.QModel;
import reinforce.core.SARSA;
import reinforce.core.StateAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zach reeve on 12/27/2016.
 */
public abstract class DQNModel<T extends StateAction> extends QModel<T> {

    MultiLayerNetwork model;

    protected DQNModel(DQNProperties props) {
        model = new MultiLayerNetwork(props.getConf());
        model.init();
    }

    public void train(ArrayList<SARSA<T>> lastActions, List<SARSA<T>> historyRecords) {
        ArrayList<SARSA<T>> all = new ArrayList<SARSA<T>>();
        all.addAll(lastActions);
        all.addAll(historyRecords);
        Pair<INDArray, INDArray> dataset = getDataSet(all);
        model.fit(dataset.left, dataset.right);
    }

    protected abstract Pair<INDArray,INDArray> getDataSet(ArrayList<SARSA<T>> sarsas);

    public List<Pair<T, Double>> getQValues(List<T> possibleActions) {
        return null;
    }

    public Pair<T, Double> getQValue(T sa) {
        return null;
    }
}
