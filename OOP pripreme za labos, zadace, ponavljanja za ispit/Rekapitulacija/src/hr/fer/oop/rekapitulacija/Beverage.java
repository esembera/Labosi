package hr.fer.oop.rekapitulacija;

public class Beverage extends Item {
	private double volume;
	private final int TAX = 25; //(%)

	public Beverage(String name, String barcode, double netPrice, double volume) {
		super(name, barcode, netPrice);
		this.volume=volume;
	}

	@Override
	public double getSalePrice() {
		return netPrice+netPrice*TAX/100;
	}
	


}
