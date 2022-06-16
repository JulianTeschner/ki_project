package layer;

import activationStrategy.ActivationStrategy;

public class OutputLayer implements Layer {


    public ActivationStrategy g;
    public double[] out;
    public double[] delta;
    public double[][] weights;
    public int offset = 0;

    public OutputLayer(int nodes, int followNodes, ActivationStrategy strategy) {
        weights = new double[followNodes][nodes];
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

    @Override
    public void calcDelta(Layer prev, Layer next, double y) {
        for (int i = 0; i < this.getDelta().length; i++) {
            // in[i+1] wegen bias
            this.getDelta()[i] = this.getG().calcDerivedActivation(prev.getOut()[i + 1]) * (y - this.getOut()[i]);
        }
    }

    @Override
    public void forwardStrategy(double[] in) {
        for (int i = 0; i < this.getWeights().length; i++) {
            double res = 0;
            for (int j = 0; j < this.getWeights()[0].length; j++) {
                res += in[j+1] * this.getWeights()[i][j];
            }
            this.getOut()[i + this.getOffset()] = this.getG().calcActivation(res);
        }
    }

//    @Override
//    public void backpropagation(double alpha, double[] in) {
//        for (int i = 0; i < this.getWeights().length; i++) {
//            for (int j = 0; j < this.getWeights()[0].length; j++) {
//                this.getWeights()[i][j] += alpha * this.out[i] * delta[j];
//            }
//        }
//    };
}
