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
                layers.get(k).forwardStrategy(layers.get(k).getOut());
            }
        }
    }

//    @Override
//    public void forwardStrategy(Layer layer, double[] in) {
//        for (int i = 0; i < layer.getWeights().length; i++) {
//            double res = 0;
//            for (int j = 0; j < layer.getWeights()[0].length; j++) {
//                res += in[j] * layer.getWeights()[i][j];
//            }
//            layer.getOut()[i + layer.getOffset()] = layer.getG().calcActivation(res);
//        }
//    }

    @Override
    public void backwardPass() {
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
