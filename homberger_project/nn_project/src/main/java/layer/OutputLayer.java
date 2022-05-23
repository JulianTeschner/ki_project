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

    @Override
    public ActivationStrategy getG() {
        return g;
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

    public void calcDelta() {

    }

}
