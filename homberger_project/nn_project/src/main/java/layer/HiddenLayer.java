package layer;

import activationStrategy.ActivationStrategy;

public class HiddenLayer implements Layer {
    public ActivationStrategy g;
    public double[] out;
    public double[] delta;
    public double[][] weights;
    public int offset = 1;

    public HiddenLayer(int nodes, int followNodes, ActivationStrategy strategy) {
        weights = new double[followNodes][nodes + 1];
        this.initRandomWeights(weights);
        this.g = strategy;
        out = new double[followNodes + 1];
        out[0] = 1;
        delta = new double[nodes + 1];
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
    public void calcDelta(Layer prev, Layer next, double y) {
        for (int j = 0; j < this.getWeights()[0].length; j++) {
            double sum = 0;
            for (int k = 0; k < this.getWeights().length; k++) {
                sum += next.getDelta()[k] * this.getWeights()[k][j];
            }
            this.getDelta()[j] = sum * this.getG().calcDerivedActivation(prev.getOut()[j]);
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
