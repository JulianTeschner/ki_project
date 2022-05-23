package layer;

import activationStrategy.ActivationStrategy;

public class OutputLayer implements Layer{


    public ActivationStrategy g;
    public double[] out;
    public double[] delta;
    public double[][] weights;
    public int offset = 1;

    public OutputLayer(int nodes, int followNodes, ActivationStrategy strategy) {
        weights = new double[followNodes][nodes +1];
        initRandomWeights(weights);
        this.g = strategy;
        out = new double[followNodes];
        delta = new double[nodes];
    }

}
