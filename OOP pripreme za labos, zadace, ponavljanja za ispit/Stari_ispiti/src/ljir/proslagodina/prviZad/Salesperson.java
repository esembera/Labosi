package ljir.proslagodina.prviZad;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Salesperson {
	public void printItems(List<Item> list, Predicate<Item> p) {
		
		list.stream().filter(p).sorted(Comparator.comparing(Item::getName)).forEach(i -> System.out.println(i.toString()));
	 }

	public void printTheBill(Item[] basket){
	 double total = 0.;
	 System.out.println("");
	 	for(Item i: basket){
	 		if(i instanceof Pack) {
	 			System.out.printf("%s %dx%.2f\n",i.getName(), ((Pack) i).getItemsInPack().length, i.getSalePrice()/((Pack) i).getItemsInPack().length);
	 			System.out.printf("%36.2f\n", i.getSalePrice());
	 			total+=i.getSalePrice();
	 		}else {
	 			System.out.printf("%-30s%6.2f\n", i.getName(), i.getSalePrice());
	 			total+=i.getSalePrice();
	 		}
	 	}
	 	System.out.format("%30s%6.2f\n", "TOTAL: ", total);
	 }
	
	
	
	 public Map<String, OriginOfProduct> getOrigin(List<? extends Barcoded> itemi ) {
		 Map<String, OriginOfProduct> mapa = new LinkedHashMap<>();
		 for(Barcoded i: itemi) {
			 if(i.barcode().startsWith("385")) {
				 mapa.put(i.barcode(), OriginOfProduct.DOMESTIC);
			 }else {
			 mapa.put(i.barcode(), OriginOfProduct.FOREIGN);
		 }}
		 return mapa;
	  }
}
