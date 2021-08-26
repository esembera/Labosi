package ljir.proslagodina.prviZad;

public class Beverage extends Item {
	private double volume;
	private final int TAX = 25; 

	public Beverage(String name, String barcode, double netPrice, double volume) {
		super(name, barcode, netPrice);
		this.volume=volume;
		
	}
	
	public double getSalePrice() {
		return getNetPrice()*(1+TAX/100.0);
	}


}
