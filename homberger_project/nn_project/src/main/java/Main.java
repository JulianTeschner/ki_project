import java.io.File;
import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException {		
		//1. Trainingsdaten auswaehlen bzw. einlesen
		double[][] daten = Einlesen.einlesenVorlesungsbeispiele(new File("data\\wetter.txt"));
	
		//2. Netz aufbauen
		Perzeptron p = new Perzeptron(daten);
		
		//3. Netz trainieren
		p.trainieren(daten, 20, 0.5);
		
		//4. Netz evaluieren
		p.evaluieren(daten);		
	}
}
