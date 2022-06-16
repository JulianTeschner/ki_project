package layer;

import activationStrategy.ActivationStrategy;

public class InputLayer implements Layer {
    public ActivationStrategy g;
    public double[] out;
    public double[] delta;
    public double[][] weights;
    public int offset = 1;

    public InputLayer(int nodes, int followNodes, ActivationStrategy strategy) {
        weights = new double[followNodes][nodes + 1];
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[0].length; j++) {
                weights[i][j] = 1;
            }
            weights[i][0]=0;
        }
        this.g = strategy;
        out = new double[followNodes];
        delta = new double[nodes + 1];

    }

    @Override
    public ActivationStrategy getG() {
        return g;
    }

    @Override
    public void backpropagation(double alpha, double[] in) {

    }

    @Override
    public void setG(ActivationStrategy g) {
        this.g = g;
    }

    @Override
    public double[] getOut() {
        return out;
    }

    @Override
    public void setOut(double[] out) {
        this.out = out;
    }

    @Override
    public double[] getDelta() {
        return delta;
    }

    @Override
    public void setDelta(double[] delta) {
        this.delta = delta;
    }

    @Override
    public double[][] getWeights() {
        return weights;
    }

    @Override
    public void setWeights(double[][] weights) {
        this.weights = weights;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public void calcDelta(Layer prev, Layer next, double y) {
    }

    @Override
    public void forwardStrategy(double[] in) {
        setOut(in);
    }

}
