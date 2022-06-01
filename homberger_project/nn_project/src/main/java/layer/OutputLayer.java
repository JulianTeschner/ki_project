package layer;

import activationStrategy.ActivationStrategy;

public class OutputLayer implements Layer {


    public ActivationStrategy g;
    public double[] out;
    public double[] delta;
    public double[][] weights;
    public int offset = 0;
    public double[] y;

    public OutputLayer(int nodes, int followNodes, ActivationStrategy strategy, double[] y) {
        weights = new double[followNodes][nodes];
        initRandomWeights(weights);
        this.g = strategy;
        out = new double[followNodes];
        delta = new double[nodes];
        this.y = y;
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

    @Override
    public void calcDelta(Layer prev, Layer next) {
        for (int i = 0; i < this.getDelta().length; i++) {
            // in[i+1] wegen bias
            this.getDelta()[i] = this.getG().calcDerivedActivation(prev.getOut()[i + 1]) * (y[i] - this.getOut()[i]);
        }
    }

    @Override
    public void forwardStrategy(double[] in) {
        for (int i = 0; i < this.getWeights().length; i++) {
            double res = 0;
            for (int j = 0; j < this.getWeights()[0].length; j++) {
                res += in[j] * this.getWeights()[i][j];
            }
            this.getOut()[i + this.getOffset()] = this.getG().calcActivation(res);
        }
    }
}
