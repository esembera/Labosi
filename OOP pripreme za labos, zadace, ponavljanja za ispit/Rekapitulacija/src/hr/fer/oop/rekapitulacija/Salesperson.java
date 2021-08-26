package hr.fer.oop.rekapitulacija;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Salesperson {
	
	public void printItems(List<Item> list, Predicate<Item> p) {
		list.stream().distinct().filter(p).sorted(Comparator.comparing(Item::getName)).forEach(s -> System.out.println(s.toString()));
	 }
	
	public void printTheBill(Item[] kosarica) {
		double total = 0.;
		for(Item i: kosarica){
			if(i instanceof Pack) {
				System.out.printf("%s %dx%.2f\n", i.getName(),((Pack)i).getItemsInPack().length,((Pack) i).getItemsInPack()[0].getSalePrice());
				System.out.printf("%30s%6.2f\n", " ",i.getSalePrice());
				total = total + i.getSalePrice();
			}else {
				System.out.printf("%-30s%6.2f\n",i.getName(),i.getSalePrice());
				total=total + i.getSalePrice();				
			}

		}
		System.out.format("%30s%6.2f\n", "TOTAL: ", total);
		 
	}
	

	 public Map<String, OriginOfProduct> getOrigin(List<Item> list) {
		 Map<String, OriginOfProduct> kita = new LinkedHashMap<>();
		 
		 for(Barcoded i: list) {
			 if(i.barcode().toString().startsWith("385")) {
				 kita.put(i.barcode(), OriginOfProduct.DOMESTIC);
			 }
			 else {
				 kita.put(i.barcode(), OriginOfProduct.FOREIGN);
			 }
		 }
		 
		 return kita;
		 
	  }
	
}
	 

		

