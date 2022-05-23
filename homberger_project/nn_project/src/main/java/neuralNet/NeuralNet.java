package neuralNet;

import layer.Layer;

public interface NeuralNet {
    public void forwardPass(double[] in);

    public void backwardPass();

    public void calcDeltaHiddenLayer();

    public void calcDeltaOutputLayer(double[] in, double[] y);

    public void forwardStrategy(Layer layer, double[] in);
}
