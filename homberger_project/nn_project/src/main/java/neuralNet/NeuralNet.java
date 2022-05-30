package neuralNet;

import layer.Layer;

public interface NeuralNet {
    void forwardPass(double[] in);

    void backwardPass();

    void calcDelta();

    void forwardStrategy(Layer layer, double[] in);
}
