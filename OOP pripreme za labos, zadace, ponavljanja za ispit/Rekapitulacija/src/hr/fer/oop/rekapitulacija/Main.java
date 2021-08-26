package hr.fer.oop.rekapitulacija;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		List<Item> items = getItems();
		Item[] kosarica = new Item[items.size()];
		items.toArray(kosarica);
		// odnosi se na 2. zadatak
		Salesperson s = new Salesperson();
		s.printItems(items, i -> i.barcode().startsWith("385"));
		s.printTheBill(kosarica);
		// odnosi se na 2. zadatak
		Map<String, OriginOfProduct> origins = s.getOrigin(items);
		for (Map.Entry<String, OriginOfProduct> entry : origins.entrySet())
			System.out.println(entry);
		Set<Item> skup = new LinkedHashSet<>();
		skup.add(new Food("Dukat jogurt", "385000909", 2.20, 0.1));
		skup.add(new Food("Dukat jogurt", "385000909", 2.20, 0.1));
		skup.addAll(items);
		for (Item i : skup) {
			System.out.println(i.toString());
		}
		System.out.println("Seksi Cinks");
		
		
		items.stream().filter(s1 -> s1 instanceof Food).mapToDouble(s1 -> s1.getSalePrice()).average().ifPresentOrElse(
				(v)->System.out.println("Prosjecna cijena hrane je " + v),
				() -> System.out.println("Nema hrane u kosarici"));	
		
		}

	public static List<Item> getItems() {
		List<Item> items = new ArrayList<>();
		items.add(new Beverage("Jana voda", "38523456", 5.90, 1.5));
		items.add(new Food("Lay's", "492345678", 10.00, 0.3));
		Item[] itemsInPack = new Item[6];
		for (int i = 0; i < itemsInPack.length; i++)
			itemsInPack[i] = new Food("Dukat jogurt", "385000909", 2.20, 0.1);
		items.add(new Pack("Dukat jogurt pack", "385876543", itemsInPack));
		return items;
	}
}
