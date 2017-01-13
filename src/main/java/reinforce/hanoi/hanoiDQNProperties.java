package reinforce.hanoi;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.Updater;
import reinforce.core.Pair;
import reinforce.core.qmodels.DQNProperties;

/**
 * Created by zach reeve on 1/6/2017.
 */
public class hanoiDQNProperties extends DQNProperties {
    protected boolean getBackprop() {
        return true;
    }

    protected boolean getPretrain() {
        return false;
    }

    protected Pair<Pair<Integer, Integer>, String>[] getLayerConfs() {
        Pair<Pair<Integer, Integer>, String>[] ret = new Pair[3];
        ret[0] = new Pair<Pair<Integer, Integer>, String>(new Pair<Integer, Integer>(getNumInputs(), 100), "relu");
        ret[1] = new Pair<Pair<Integer, Integer>, String>(new Pair<Integer, Integer>(100, 10), "relu");
        ret[2] = new Pair<Pair<Integer, Integer>, String>(new Pair<Integer, Integer>(10, getNumOutputs()), "relu");
        return ret;
    }

    protected Integer getNumOutputs() {
        return 1;
    }

    protected Integer getNumInputs() {
        return 36;
    }

    protected double getL2() {
        return 1e-4;
    }

    protected boolean getRegularization() {
        return true;
    }

    protected double getMomentum() {
        return 0.9;
    }

    protected Updater getUpdater() {
        return Updater.NESTEROVS;
    }

    protected double getLearningRate() {
        return .006;
    }

    protected int getIterations() {
        return 1;
    }

    protected OptimizationAlgorithm getOptimizationAlgorithm() {
        return OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT;
    }


}
