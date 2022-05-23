package neuralNet;

import layer.Layer;

import java.util.ArrayList;

public class NeuralNetImpl implements NeuralNet{

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
                forwardStrategy(layers.get(k), in);
            } else {
                forwardStrategy(layers.get(k), layers.get(k - 1).getOut());
            }
        }
    }

    @Override
    public void forwardStrategy(Layer layer, double[] in) {
        for (int i = 0; i < layer.getWeights().length; i++) {
            double res = 0;
            for (int j = 0; j < layer.getWeights()[0].length; j++) {
                res += in[j] * layer.getWeights()[i][j];
            }
            layer.getOut()[i+layer.getOffset()] = layer.getG().calcActivation(res);
        }
    }

    @Override
    public void backwardPass() {
    }

    @Override
    public void calcDeltaOutputLayer(double[] in, double[] y) {
        Layer output =  layers.get(layers.size()-1);
        for (int i = 0; i < output.getDelta().length; i++) {
            // in[i+1] wegen bias
            output.getDelta()[i] = output.getG().calcDerivedActivation(in[i+1]) * (y[i] - output.getOut()[i]);
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
            for (int j = 0; j < this.layers.get(i).getWeights()[0].length ; j++) {
                double sum = 0;
                for (int k = 0; k < this.layers.get(i).getWeights().length; k++) {
                   sum += layers.get(i+1).getDelta()[k] * layers.get(i).getWeights()[k][j];
                }
                layers.get(i).getDelta()[j] = sum * layers.get(i).getG().calcDerivedActivation(layers.get(i-1).getOut()[j]);
            }
        }
    }
}
