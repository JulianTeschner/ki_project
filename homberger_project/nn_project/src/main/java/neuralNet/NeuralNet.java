package neuralNet;

import layer.HiddenLayer;

public interface NeuralNet {
    public void forwardPass(double[] in);

    public void backwardPass();

    public void calcDeltaHiddenLayer();

    public void calcDeltaOutputLayer(double[] in, double[] y);

    public void forwardStrategy(HiddenLayer layer, double[] in);
}
