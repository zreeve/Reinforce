package reinforce.core.qmodels;

import reinforce.core.Pair;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.weights.WeightInit;

/**
 * Created by zach reeve on 1/6/2017.
 */
public abstract class DQNProperties {


    public MultiLayerConfiguration getConf() {
        NeuralNetConfiguration.ListBuilder lb = new NeuralNetConfiguration.Builder()
                                                    // use stochastic gradient descent as an optimization algorithm
                                                    .optimizationAlgo(getOptimizationAlgorithm())
                                                    .iterations(getIterations())
                                                    .learningRate(getLearningRate()) //specify the learning rate
                                                    .updater(getUpdater())
                                                    .momentum(getMomentum()) //specify the rate of change of the learning rate.
                                                    .regularization(getRegularization())
                                                    .l2(getL2())
                                                    .list();

            int index = 0;
            for (Pair<Pair<Integer, Integer>, String> layer : getLayerConfs()) {
                lb = lb.layer(index, new DenseLayer.Builder()
                    .nIn(layer.left.left)
                    .nOut(layer.left.right)
                    .activation(layer.right)
                    .weightInit(WeightInit.XAVIER)
                    .build());
                index++;
            }

            return lb.pretrain(getPretrain())
                    .backprop(getBackprop()) //use backpropagation to adjust weights
                    .build();
    }

    protected abstract boolean getBackprop();

    protected abstract boolean getPretrain();

    protected abstract Pair<Pair<Integer,Integer>, String>[] getLayerConfs();

    protected abstract double getL2();

    protected abstract boolean getRegularization();

    protected abstract double getMomentum();

    protected abstract Updater getUpdater();

    protected abstract double getLearningRate();

    protected abstract int getIterations();

    protected abstract OptimizationAlgorithm getOptimizationAlgorithm();

    protected abstract Integer getNumOutputs();

    protected abstract Integer getNumInputs();
}
