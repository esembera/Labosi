package ljir.proslagodina.prviZad;

public class Food extends Item{

	private double weight;
	private final int TAX = 5; 
	
	public Food(String name, String barcode, double netPrice, double weight) {
		super(name, barcode, netPrice);
		this.weight=weight;
	}
	
	public double getSalePrice() {
		return this.getNetPrice()*(1+TAX/100.0);
	}
	



}