package neuralNet;

import layer.HiddenLayer;

import java.util.ArrayList;

public class NeuralNetImpl implements NeuralNet{

    public ArrayList<HiddenLayer> layers;
    public double alpha;

    public NeuralNetImpl(ArrayList<HiddenLayer> layers, double alpha) {
       this.layers = layers;
       this.alpha = alpha;

    }

    @Override
    public void forwardPass(double[] in) {
        for (int k = 0; k < layers.size(); k++) {
            if (k == 0) {
                forwardStrategy(layers.get(k), in);
            } else {
                forwardStrategy(layers.get(k), layers.get(k - 1).out);
            }
        }
    }

    @Override
    public void forwardStrategy(HiddenLayer layer, double[] in) {
        for (int i = 0; i < layer.weights.length; i++) {
            double res = 0;
            for (int j = 0; j < layer.weights[0].length; j++) {
                res += in[j] * layer.weights[i][j];
            }
            layer.out[i+layer.offset] = layer.g.calcActivation(res);
        }
    }



    @Override
    public void backwardPass() {

    }

    @Override
    public void calcDeltaOutputLayer(double[] in, double[] y) {
        HiddenLayer output =  layers.get(layers.size()-1);
        for (int i = 0; i < output.delta.length; i++) {
            // in[i+1] wegen bias
            output.delta[i] = output.g.calcDerivedActivation(in[i+1]) * (y[i] - output.out[i]);
        }
//        // weight = weight + alpha * out * delta
//        for (int i = 0; i < weights.length; i++) {
//            for (int j = 0; j < weights[0].length; j++) {
//
//            }
//
//        }
    }

    @Override
    public void calcDeltaHiddenLayer() {
        for (int i = layers.size()-2; i > 0; i--) {
            for (int j = 0; j < this.layers.get(i).weights[0].length ; j++) {
                double sum = 0;
                for (int k = 0; k < this.layers.get(i).weights.length; k++) {
                   sum += layers.get(i+1).delta[k] * layers.get(i).weights[k][j];
                }
                layers.get(i).delta[j] = sum * layers.get(i).g.calcDerivedActivation(layers.get(i-1).out[j]);
            }
        }
    }
}
