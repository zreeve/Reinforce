package reinforce.hanoi;


import reinforce.core.EGreedyActionPolicy;
import reinforce.core.History;
import reinforce.core.Trainer;
import reinforce.core.qmodels.SimpleQModel;

/**
 * Created by zach reeve on 12/21/2016.
 */
public class HanoiMain {

    public static void main (String args[]) {

        EGreedyActionPolicy<HanoiStateAction> policy = new EGreedyActionPolicy<HanoiStateAction>(0.01);
        Trainer<HanoiStateAction> trainer = new Trainer<HanoiStateAction>(new HanoiStateHandler(policy), new History<HanoiStateAction>(), new SimpleQModel<HanoiStateAction>(.1));
        trainer.runTrainingIterations(1000);

    }
}
