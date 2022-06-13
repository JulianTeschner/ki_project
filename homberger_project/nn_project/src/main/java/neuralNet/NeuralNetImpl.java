package neuralNet;

import layer.Layer;

import java.util.ArrayList;

public class NeuralNetImpl implements NeuralNet {

    public ArrayList<Layer> layers;
    public double alpha;

    public NeuralNetImpl(ArrayList<Layer> layers, double alpha) {
        this.layers = layers;
        this.alpha = alpha;

    }

    @Override
    public void forwardPass(double[] in) {
        for (int k = 0; k < layers.size(); k++) {
            if (k == 0) {
                layers.get(k).forwardStrategy(in);
            } else {
                layers.get(k).forwardStrategy(layers.get(k-1).getOut());
            }
        }
    }


    @Override
    public void backwardPass() {
        this.calcDelta();
        for (int i = this.layers.size()-1; i > 0; i--) {
            this.layers.get(i).backpropagation(this.alpha, this.layers.get(i-1).getOut());
        }
    }

    @Override
    public void calcDelta() {
        for (int i = layers.size() - 1; i >= 1; i--) {
            if (i == layers.size() - 1) {
                layers.get(i).calcDelta(layers.get(i - 1), null);
            } else {
                layers.get(i).calcDelta(layers.get(i - 1), layers.get(i + 1));
            }
        }
    }


}
