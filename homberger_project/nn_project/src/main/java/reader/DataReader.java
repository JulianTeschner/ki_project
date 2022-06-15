package reader;

import java.io.FileReader;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class DataReader {

		
		public static void auslesen(double[][] daten) {
			for(int i=0;i<daten.length;i++){
	        	System.out.print(i + " ");
	        	for(int j=0;j<daten[i].length;j++){
	        		System.out.print(daten[i][j] + " ");
	        	}
	        	System.out.println();
	        }
		}
		
	
		
		public static double[][] einlesenBankdaten(File file) {
			double[][] koord = null;
			int          dim = 0;
	        try{
	        	//Anzahl Zeilen ermitteln
	            Scanner scanner  = new Scanner(file);
	            String x;
		        while(scanner.hasNext()) {
	                x = scanner.next();
	                dim++;
	            }
		        dim = dim-1;//erste Zeile ist hier dummy
	            System.out.println("Anzahl Samples: " + dim);
	            scanner.close();
	            
	            //Zeilen einlesen
	            scanner    = new Scanner(file);
		        koord      = new double[dim][10];
		        int index  = 0;
		        int nr     = 0;
		        while(scanner.hasNext()) {
	                x = scanner.next();
	                StringTokenizer tokenizer = new StringTokenizer(x,",", false);
	                ArrayList<String> tokens  = new ArrayList<String>();
	                while (tokenizer.hasMoreTokens()){
	                    tokens.add(tokenizer.nextToken());
	                }
	                if(nr != 0){
		                for(int i=0;i<tokens.size();i++){
		                	String s = tokens.get(i);
		                	koord[index][i] = Double.parseDouble(s);
		                }
		                index++;
	                }
	                nr++;
	                if(index >= dim)break;
	            }    
		        scanner.close();
		        System.out.println("Daten eingelesen");
	        }
	        catch(FileNotFoundException ignored){
	        	
	        }
	        return koord;
	    }	
		
		public static double[][] einlesenDiabetes(File file, boolean train) {
			double[][] koord = null;
			int          dim = 0;
			int     dimTrain = 0;
			int     dimEvalu = 0;
		    double prozent   = 0.5;    
		    int anzahlMerk   = 9;//inclusive der Klasse!!

	        try{
	        	//Anzahl Zeilen ermitteln
	            Scanner scanner  = new Scanner(file);
	            String x;
		        while(scanner.hasNext()) {
	                x = scanner.next();
	                dim++;
	            }
		        
		        dim = dim-1;//erste Zeile ist hier dummy
		        
		        dimTrain = (int)(dim*prozent);
		        dimEvalu = dim-dimTrain;
		        
	            if(train)System.out.println("Anzahl Samples Original: " + dim + " Anzahl Samples Auswahl: " + dimTrain);
	            else     System.out.println("Anzahl Samples Original: " + dim + " Anzahl Samples Auswahl: " + dimEvalu);
	            scanner.close();
	            
	            //Zeilen einlesen
	            scanner        = new Scanner(file);
	            if(train)koord = new double[dimTrain][anzahlMerk]; 
	            else     koord = new double[dimEvalu][anzahlMerk];
		        int index      = 0;//Nummer des Musters in der Datei
		        int indexA     = 0;//Nummer des Musters in der Auswahl
		        int nr         = 0;//Attribute bzw Merkmale
		        
		        while(scanner.hasNext()) {
	                x = scanner.next();
	                StringTokenizer tokenizer = new StringTokenizer(x,",", false);
	                ArrayList<String> tokens  = new ArrayList<String>();
	                while (tokenizer.hasMoreTokens()){
	                    tokens.add(tokenizer.nextToken());
	                }
	                
	                if( (!train && index < dimTrain)||(train && index >= dimTrain) ){
//	                	System.out.println(train + " index 0");
	                	index++;
	                	continue;
	                }
	                
	                if(nr != 0){
		                for(int i=0;i<tokens.size();i++){
		                	String s = tokens.get(i);
		                	if(i==0){
		                		koord[indexA][i] = Double.parseDouble(s)/17.;
		                		if(koord[indexA][i]> 1.0)System.out.println("Fehler in Dateiwert " + index + " " + i);
		                	}
		                	else if(i==1){
		                		koord[indexA][i] = Double.parseDouble(s)/199.;
		                		if(koord[indexA][i]> 1.0)System.out.println("Fehler in Dateiwert " + index + " " + i);
		                	}
		                	else if(i==2){
		                		koord[indexA][i] = Double.parseDouble(s)/122.;
		                		if(koord[indexA][i]> 1.0)System.out.println("Fehler in Dateiwert " + index + " " + i);
		                	}
		                	else if(i==3){
		                		koord[indexA][i] = Double.parseDouble(s)/99.;
		                		if(koord[indexA][i]> 1.0)System.out.println("Fehler in Dateiwert " + index + " " + i);
		                	}
		                	else if(i==4){
		                		koord[indexA][i] = Double.parseDouble(s)/846.;
		                		if(koord[indexA][i]> 1.0)System.out.println("Fehler in Dateiwert " + index + " " + i);
		                	}
		                	else if(i==5){
		                		koord[indexA][i] = Double.parseDouble(s)/67.1;
		                		if(koord[indexA][i]> 1.0)System.out.println("Fehler in Dateiwert " + index + " " + i);
		                	}
		                	else if(i==6){
		                		koord[indexA][i] = Double.parseDouble(s)/2.42;
		                		if(koord[indexA][i]> 1.0)System.out.println("Fehler in Dateiwert " + index + " " + i);
		                	}
		                	else if(i==7){
		                		koord[indexA][i] = Double.parseDouble(s)/81.;
		                		if(koord[indexA][i]> 1.0)System.out.println("Fehler in Dateiwert " + index + " " + i);
		                	}
		                	else if(i==8){
		                		koord[indexA][i] = Double.parseDouble(s);
		                		if(koord[indexA][i]> 1.0)System.out.println("Fehler in Dateiwert " + index + " " + i);
		                	}
		                	else{
		                		System.out.println("Fehler in Dateiauf");
		                	}	
		                }
		                index++;
		                indexA++;
	                }
	                nr++;
	                //if(index >= dim)break;
	            }    
		        scanner.close();		        
	        }
	        catch(FileNotFoundException v){
	        	
	        }
	        return koord;
	    }	
		
		
		public static double[][] einlesenTitanic(File file) throws IOException {
			double[][] koord = null;
			int          dim = 0;
			
			if (file.exists() && file.isFile())
		    {
		        BufferedReader br = null;
		        java.io.FileReader fr = null;
		 
		        try
		        {
		            fr = new java.io.FileReader(file);
		            br = new BufferedReader(fr);
		             
		            String l;
		            double alterDurchs = 0;
		             
		            // solange Zeilen in der Datei vorhanden
		            while ((l = br.readLine()) != null)
		            {
		            	String[] col = l.split(",");
		            	if(dim != 0) {
		            		if(!col[3].isEmpty()) {
			            		alterDurchs += Double.parseDouble(col[3]);
			            	}
		            	}
		            	dim++;
		            }
		            dim = dim-1;//erste Zeile ist hier dummy
		            alterDurchs = alterDurchs/dim; //Durschnittsalter berechnen
		            System.out.println("Anzahl Samples: " + dim);
		            br.close();
		            fr.close();
		            
		          //Zeilen einlesen
		            fr = new java.io.FileReader(file);
		            br = new BufferedReader(fr);
			        koord      = new double[dim][10];
			        int index  = 0;
			        int nr     = 0;
			        
			        while ((l = br.readLine()) != null)
		            {
		                // Zeilen anhand des Separators,
		                String[] col = l.split(",");
		                 
		                if(nr != 0){
		                	for (int i = 0; i<col.length; i++)
			                {
		                		String s = col[i];
		                		
		                		//1. Zu lernende Ergebniss-Klasse (survival)
		                		if(i == 0) {
		                			koord[index][9] = Double.parseDouble(s);
		                		}
		                		
		                		//2. Ticket-Klasse (class)
		                		else if(i == 1) {
		                			if(s.isEmpty()) {
		                				koord[index][0] = 0;
		                			}
		                			else {
		                				koord[index][0] = Double.parseDouble(s);
		                			}
		                			
		                		}
		                		
		                		//3. Geschlecht (sex)
		                		else if(i == 2) {
		                			if(s.isEmpty()) {
		                				koord[index][1] = 0;
		                			}
		                			else {
		                				if(s.equals("male")) {
			                				koord[index][1] = 0;
			                			}
			                			else if(s.equals("female")) {
			                				koord[index][1] = 1;
			                			}
		                			}
		                			
		                		}
		                		
		                		//4. Alter (age)
		                		else if(i == 3) {
		                			if(s.isEmpty()) {
		                				koord[index][2] = alterDurchs;
		                			}
		                			else {
		                				koord[index][2] = Double.parseDouble(s);
		                			}
		                			
		                		}
		                		
		                		//5. Anzahl Ehepartner bzw. Geschwister (sibsp)
		                		else if(i == 4) {
		                			if(s.isEmpty()) {
		                				koord[index][3] = 0;//muss noch durchschnitt rein
		                			}
		                			else {
		                				koord[index][3] = Double.parseDouble(s);
		                			}
		                		}
		                		
		                		//6. Anzahl Eltern bzw. Kinder (parch)
		                		else if(i == 5) {
		                			if(s.isEmpty()) {
		                				koord[index][4] = 0;//muss noch durchschnitt rein
		                			}
		                			else {
		                				koord[index][4] = Double.parseDouble(s);
		                			}
		                			
		                		}
		                		
		                		//7. Ticketpreis (fare)
		                		else if(i == 6) {
		                			if(s.isEmpty()) {
		                				koord[index][5] = 0;//muss noch durchschnitt rein
		                			}
		                			else {
		                				koord[index][5] = Double.parseDouble(s);
		                			}
		                			
		                		}
		                		
		                		//8. Ort der Einschiffung (embarkation)
		                		else if(i == 7) {
		                			if(s.isEmpty()) {
		                				koord[index][6] = 0;
		                				koord[index][7] = 0;
		                				koord[index][8] = 0;
		                			}
		                			else {
		                				if(s.equals("C")) {
			                				koord[index][6] = 1;
			                				koord[index][7] = 0;
			                				koord[index][8] = 0;
			                			}
			                			else if(s.equals("Q")) {
			                				koord[index][6] = 0;
			                				koord[index][7] = 1;
			                				koord[index][8] = 0;
			                			}
			                			else if(s.equals("S")) {
			                				koord[index][6] = 0;
			                				koord[index][7] = 0;
			                				koord[index][8] = 1;
			                			}
		                			}
		                		}
			                }
		                	index++;
		                }
		                nr++;
		            }
		        }
		        finally
		        {
		            if (br != null)
		            {
		                br.close();
		            }
		 
		            if (fr != null)
		            {
		                fr.close();
		            }
		        }
		        
		    }
	        return koord;
		}	

		
		
		
		
		
		
		
		
		
		
		
		
		public static double[][] einlesenVorlesungsbeispiele(File file) {
			//Es wird angenommen, dass alle Eingabedaten im Intervall [0, 100] liegen
			double[][] koord = null;
			int          dim = 0;	        
			
			try{
	    		Scanner scanner      = new Scanner(file);            
	            while(scanner.hasNext()) {
	            	double x1 = Double.parseDouble(scanner.next());
	                double x2 = Double.parseDouble(scanner.next());
	            	int y = Integer.parseInt(scanner.next());
	            	//hier koennte man die minimalen und maximalen Eingabewerte ermitteln
	            	//um sie beim Einlesen auf den Bereich [0, 1] zu skalieren
	            	dim++;
	            } 
	            scanner.close();
	            koord   = new double[dim][3];
	            scanner = new Scanner(file);
	            int nr  = 0;
	            while(scanner.hasNext()) {
	            	double x1 = Double.parseDouble(scanner.next());
	                double x2 = Double.parseDouble(scanner.next());
	            	double y  = Double.parseDouble(scanner.next());
	            	koord[nr][0] = x1/100.;
	            	koord[nr][1] = x2/100.;
	            	koord[nr][2] = y;
	            	nr++;
	            } 
	            scanner.close();	            
	        }
	        catch(FileNotFoundException e){
				System.out.println(e.getMessage());
	        }
	        return koord;
		}
	
		
		
	}
