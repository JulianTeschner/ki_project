import activationStrategy.Sigmoid;
import layer.HiddenLayer;
import layer.InputLayer;
import layer.Layer;
import layer.OutputLayer;
import neuralNet.NeuralNetImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static double alpha = 0.1;

    public static void main(String[] args) throws IOException {
//		//1. Trainingsdaten auswaehlen bzw. einlesen
//		double[][] daten = Einlesen.einlesenVorlesungsbeispiele(new File("data\\wetter.txt"));
//
//		//2. Netz aufbauen
//		Perzeptron p = new Perzeptron(daten);
//
//		//3. Netz trainieren
//		p.trainieren(daten, 20, 0.5);

        //4. Netz evaluieren
//		p.evaluieren(daten);

//		LayerImpl inLayer = new LayerImpl(2, 2, false, new Sigmoid(), null);
        double[] in = new double[]{0, 2, 2};
        ArrayList<Layer> layers = new ArrayList<>();
        Layer firstLayer = new InputLayer(2, 3, new Sigmoid());
        Layer secondLayer = new HiddenLayer(3, 1, new Sigmoid());
        Layer outputLayer = new OutputLayer(1, 1, new Sigmoid(), new double[]{1});
        layers.add(firstLayer);
        layers.add(secondLayer);
        layers.add(outputLayer);
        NeuralNetImpl NN = new NeuralNetImpl(layers, 0.1);

        NN.forwardPass(in);
        NN.calcDelta();

        System.out.println(Arrays.toString(NN.layers.get(0).getDelta()));
        System.out.println(Arrays.toString(NN.layers.get(1).getDelta()));
        System.out.println(Arrays.toString(NN.layers.get(2).getDelta()));
    }
}
