package jir.proslagodina.prviZad;

import java.util.HashMap;
import java.util.Map;

public class SpecialtyDish {
	
	private Map<FoodItem, AmountAndUnit> namirnice;
	private Type type;
	
	public SpecialtyDish(Type type) {
		namirnice=new HashMap<>();
		this.type=type;
	}
	
	
	public void addFoodItem(FoodItem namirnica, AmountAndUnit jedKol) {
		if(namirnice==null) {
			namirnice=new HashMap<>();
		}
		namirnice.put(namirnica, jedKol);
	}
	
	
	
	
	
}
