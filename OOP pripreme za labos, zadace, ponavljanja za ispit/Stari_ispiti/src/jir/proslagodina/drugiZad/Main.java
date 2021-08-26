package jir.proslagodina.drugiZad;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
		PlasmanExplorer pe=new PlasmanExplorer();
        try {
			pe.loadPlasmani("D:\\vozaci.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        pe.printPobjednike();

	}

}
