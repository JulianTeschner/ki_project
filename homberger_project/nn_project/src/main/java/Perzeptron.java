
public class Perzeptron {
	int n; 			//Dimension, d.h. Anzahl der Merkmale bzw. Eingaenge in das Perzeptron
	double b;		//Bias
    double[] w; 	//Gewichte, fuer jeden Eingang ein Gewicht
    int[] musterAuswahl;
	
	Perzeptron(double[][] daten){
		n = daten[0].length - 1;//das letzte Element daten[0].length enthaelt den y-Wert
		b = Math.random();
		w = new double[n];
				
		//Initialisierung der Gewichte
		for(int i=0;i<w.length;i++) {
			w[i] = Math.random();
			if(Math.random()<0.5)w[i]*=-1.0;
		}
		
		//Initialisierung der Muster-Auswahl
		musterAuswahl = new int[daten.length]; 
		for(int i=0;i<daten.length;i++) {
			musterAuswahl[i] = i;
		}
	}
	
	public void trainieren(double[][] daten, int epochen, double alphaStart) {
		double alpha  = alphaStart;
		
		for(int epoche=0;epoche<epochen;epoche++) {
			musterStochastischAuswaehlen();
			int fehler = 0;			
			for(int i=0;i<musterAuswahl.length;i++) {//fuer jedes Muster m
				int muster   = musterAuswahl[i];
				double in    = skalarProdukt(w, daten[muster]) + b;
				double out   = aktivierungsFunktionSchwellwert(in);
				double y     = daten[muster][n];
				double delta = y - out;
				gewichteAnpassen(delta, alpha, daten[muster]);
				if(y!=out)fehler++;
			}
			System.out.println("Epoche: " + epoche + " Fehler: " + fehler);
			//gewichteAusgeben();
		}
	}
	
	public void gewichteAnpassen(double delta, double alpha, double[] eingang) {
		
		//Uebungsaufgabe 1
		

	}	
	
	public void gewichteAusgeben() {
		for(int i=0;i<w.length;i++) {
			int x = (int)(w[i]*1000);
			double y = x/1000.;
			System.out.print(y + " ");
		}
		System.out.println();
	}
	
	public void musterStochastischAuswaehlen() {
		for(int i=0;i<musterAuswahl.length;i++) {
			int j = (int)(musterAuswahl.length*Math.random());
			int a = musterAuswahl[i];
			int b = musterAuswahl[j];
			musterAuswahl[i] = b;
			musterAuswahl[j] = a;
		}
	}
	
	public double skalarProdukt(double[] w, double[] eingang) {
		double result = 0.0;
		
		//Uebungsaufgabe 2
		

		return result;
	}
	
	private double aktivierungsFunktionSchwellwert(double x) {
		if(x<0)return 0.0;
		else   return 1.0;
	}	

//	public void evaluieren(double[][] daten) {
//		GUI.evaluierenGUIII(daten, this);
//	}
}
