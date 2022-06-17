import activationStrategy.Sigmoid;
import evaluation.Evaluate;
import evaluation.EvaluateImpl;
import layer.HiddenLayer;
import layer.InputLayer;
import layer.Layer;
import layer.OutputLayer;
import neuralNet.NeuralNetImpl;
import reader.DataReader;

import java.io.File;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        int epochs = 1000;
        double alpha = 0.1;
        double[][] input= DataReader.einlesenDiabetes(new File("data\\diabetes.csv"), true);
        double[] y = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            y[i] = input[i][input[0].length-1];
        }
        double[] out = new double[y.length];

        ArrayList<Layer> layers = new ArrayList<>();
        Layer firstLayer = new InputLayer(input[0].length , input[0].length, new Sigmoid());
        Layer secondLayer = new HiddenLayer(input[0].length - 1, 3, new Sigmoid());
        Layer thirdLayer = new HiddenLayer(3, 1, new Sigmoid());
        Layer outputLayer = new OutputLayer(1, 1, new Sigmoid());
        layers.add(firstLayer);
        layers.add(secondLayer);
        layers.add(thirdLayer);
        layers.add(outputLayer);
        NeuralNetImpl nn = new NeuralNetImpl(layers, alpha);

        for (int i = 0; i < input.length; i++) {
            System.arraycopy(input[i], 0,input[i], 1, input[i].length-1);
            input[i][0] = 1;
        }

        for (int i = 0; i < epochs; i++) {
            for (int j = 0; j < input.length; j++) {
                nn.forwardPass(input[j]);
                out[j] = nn.layers.get(nn.layers.size()-1).getOut()[0];
            }
            for (int j = 0; j < input.length; j++) {
                nn.backwardPass(y[j]);
            }
            Evaluate evaluation = new EvaluateImpl(y, out);
            evaluation.evaluate(i);
        }
    }
}
