package hr.fer.oop.rekapitulacija;

public class Food extends Item{

	private double weight;
	private final int TAX = 5;
	
	public Food(String name, String barcode, double netPrice, double weight) {
		super(name, barcode, netPrice);
		this.weight=weight;
	}

	@Override
	public double getSalePrice() {
		return netPrice+netPrice*TAX/100;
	}

}
