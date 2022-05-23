package layer;

import activationStrategy.ActivationStrategy;

public class HiddenLayer implements Layer {
    public ActivationStrategy g;
    public double[] out;
    public double[] delta;
    public double[][] weights;
    public int offset = 1;

    public HiddenLayer(int nodes, int followNodes, ActivationStrategy strategy) {
        weights = new double[followNodes][nodes +1];
        this.initRandomWeights(weights);
        this.g = strategy;
        out = new double[followNodes + 1];
        out[0] = 1;
        delta = new double[nodes + 1];
    }

}
