package layer;

import activationStrategy.ActivationStrategy;

import java.util.Arrays;

public class LayerImpl implements Layer {
    ActivationStrategy g;
    public double[] out;
    public double[] delta;
    public double[][] weights;
    public int offset = 0;

    public LayerImpl(int nodes, int followNodes, boolean isHidden, ActivationStrategy strategy) {
        weights = new double[followNodes][nodes +1];
        initRandomWeights(weights);
        this.g = strategy;
        out = new double[followNodes];
        if (isHidden) {
            this.offset = 1;
            out = new double[followNodes + 1];
            out[0] = 1;
        }
    }

    private void initRandomWeights(double[][] weigths) {
        for (int i = 0; i < weigths.length; i++) {
            weights[i][0] = 1;
            for (int j = 0; j < weigths[0].length; j++) {
                weights[i][j] = Math.random()*2-1;
            }
        }
    }

    @Override
    public void forwardPass(double[] in) {
        for (int i = 0; i < weights.length; i++) {
            double res = 0;
            for (int j = 0; j < weights[0].length; j++) {
                res += in[j] * weights[i][j];
            }
            this.out[i+offset] = g.calcActivation(res);
        }
    }

    @Override
    public void backwardPass() {

    }

    public void calcDeltaOutputLayer(double[] in, double[] y) {
        for (int i = 0; i < delta.length; i++) {
           delta[i] = g.calcDerivedActivation(in[i]) * (y[i] - this.out[i]);
        }
    }

    public void calcDeltaHiddenLayer() {
        
    }

}
