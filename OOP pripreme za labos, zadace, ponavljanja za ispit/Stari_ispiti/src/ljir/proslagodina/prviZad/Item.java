package ljir.proslagodina.prviZad;

import java.util.Objects;

public abstract class Item implements Barcoded {
	private String name;
	private String barcode;
	protected double netPrice;

	public String getName() {
		return name;
	}

	public Item(String name, String barcode, double netPrice) {

		this.name = name;
		this.barcode = barcode;
		this.netPrice = netPrice;

	}
	
	@Override
	public String barcode() {
		return barcode;
	}
	
	public int hashCode() {
		return Objects.hash(name, barcode, netPrice);
	}
	
	public boolean equals(Object other) {
		if(other instanceof Item) {
			Item other1 = (Item) other;
			if(this.name.equals(other1.name) && this.barcode.equals(other1.barcode) && this.netPrice==other1.netPrice) {
				return true;
			}
		}
		return false;
	}
	
	public abstract double getSalePrice();

	public String getBarcode() {
		return barcode;
	}

	public double getNetPrice() {
		return netPrice;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s Net:%.2f Sale:%.2f",this.getName(),this.getBarcode(),this.getNetPrice(),this.getSalePrice());
	}

}
