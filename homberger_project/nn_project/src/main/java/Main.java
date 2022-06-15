import activationStrategy.Sigmoid;
import evaluation.Evaluate;
import evaluation.EvaluateImpl;
import layer.HiddenLayer;
import layer.InputLayer;
import layer.Layer;
import layer.OutputLayer;
import neuralNet.NeuralNetImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {

        int epochs = 1000;
        double alpha = 0.1;


        double[] in = new double[]{0, 2, 2};
        ArrayList<Layer> layers = new ArrayList<>();
        Layer firstLayer = new InputLayer(2, 3, new Sigmoid());
        Layer secondLayer = new HiddenLayer(3, 1, new Sigmoid());
        Layer outputLayer = new OutputLayer(1, 1, new Sigmoid(), new double[]{1});
        layers.add(firstLayer);
        layers.add(secondLayer);
        layers.add(outputLayer);
        NeuralNetImpl NN = new NeuralNetImpl(layers, alpha);
        Evaluate evaluation = new EvaluateImpl(new double[]{1}, outputLayer);


        for (int i = 0; i < epochs; i++) {
            NN.forwardPass(in);
            NN.backwardPass();

            System.out.println(Arrays.toString(NN.layers.get(2).getOut()));
            evaluation.evaluate();
        }
    }
}
