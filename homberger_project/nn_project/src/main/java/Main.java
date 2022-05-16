import activationStrategy.ActivationStrategy;
import activationStrategy.Sigmoid;
import layer.LayerImpl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class Main {

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
		LayerImpl firstLayer = new LayerImpl(2, 3, true, new Sigmoid());
		LayerImpl secondLayer = new LayerImpl(3, 1, true, new Sigmoid());
		LayerImpl outputLayer =  new LayerImpl(1, 1, false, new Sigmoid());


//		inLayer.forwardPass(new double[]{2,2});
		firstLayer.forwardPass(new double[]{1, 2, 2});
		secondLayer.forwardPass(firstLayer.out);
		outputLayer.forwardPass(secondLayer.out);

		System.out.println(Arrays.toString(firstLayer.out));
		System.out.println(Arrays.toString(secondLayer.out));
		System.out.println(Arrays.toString(outputLayer.out));

	}
}
