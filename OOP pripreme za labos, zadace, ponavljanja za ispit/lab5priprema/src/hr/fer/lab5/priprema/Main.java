package hr.fer.lab5.priprema;

import java.util.*;

public class Main {


	
	
	
	public static void main(String[] args) {

		
		Scanner sc = new Scanner(System.in);
		
		TreeMap<String, ArrayList<Integer>> imenik = new TreeMap<>();
		
		while(true) {
			String[] sve;
			String name = sc.nextLine();
			if(name.equals("KRAJ")) break;
			sve=name.split(" ");
			if(imenik.get(sve[0])==null) {
				imenik.put(sve[0], new ArrayList<Integer>());	
			}
			imenik.get(sve[0]).add(Integer.parseInt(sve[1]));
		}
		Integer ukupno;
		Double kvadrat;
		Double ukKvad;
		Double varijanca;
		
		for (Map.Entry<String, ArrayList<Integer>> a: imenik.entrySet()) {
			ukupno=0;
			ukKvad=0.;
			varijanca=0.;
			System.out.println("Ucenik " + a.getKey());
			System.out.println("\tBroj ocjena: " + a.getValue().size());
			System.out.printf("\tOcjene: ");
			for(Integer i:a.getValue()) {
				System.out.print(i+" ");
				ukupno += i;
			}
			double prosjek = (double)ukupno/a.getValue().size(); 
			System.out.printf("\n\tProsjecna ocjena: %.2f\n",prosjek);
			
			for(Integer j:a.getValue()) {
				kvadrat = ((double)j - prosjek)*((double)j - prosjek);
				ukKvad += kvadrat;
			}
			varijanca=Math.sqrt((ukKvad/a.getValue().size()));
			System.out.printf("\tStandardno odstupanje: %.2f\n\n",varijanca);
			
		}
		
	}

}
