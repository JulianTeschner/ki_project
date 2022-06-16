package neuralNet;

import layer.Layer;

public interface NeuralNet {
    void forwardPass(double[] in);

    void backwardPass(double y);

    void calcDelta(double y);

}
