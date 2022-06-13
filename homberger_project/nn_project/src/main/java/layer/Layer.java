package layer;

import activationStrategy.ActivationStrategy;

public interface Layer {
    default void initRandomWeights(double[][] weights) {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[0].length; j++) {
//                weights[i][j] = Math.random()*2-1;
                weights[i][j] = 1;
            }
        }
    }


    ActivationStrategy getG();

    default void backpropagation(double alpha, double[] in) {
        for (int i = 0; i < this.getWeights().length; i++) {
            for (int j = 0; j < this.getWeights().length; j++) {
                double sum = 0;
                for (int k = 0; k < in.length; k++) {
                    sum += in[k] * this.getDelta()[k];
                }
                this.getWeights()[i][j] += alpha * sum;
            }
        }
    };

    void setG(ActivationStrategy g);

    double[] getOut();

    void setOut(double[] out);

    double[] getDelta();

    void setDelta(double[] delta);

    double[][] getWeights();

    void setWeights(double[][] weights);

    int getOffset();

    void setOffset(int offset);

    void calcDelta(Layer prev, Layer next);

    void forwardStrategy(double[] in);

}
