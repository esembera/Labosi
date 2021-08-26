package hr.fer.oop.rekapitulacija;

import java.util.Objects;

public abstract class Item implements Barcoded{
	private String name;
	private String barcode;
	protected double netPrice; 

	public String getName() { 
		return name; 
		}
	
	public double getNetPrice(){
		return netPrice;
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
	
	public abstract double getSalePrice();
	
	@Override
	public int hashCode() {
		return Objects.hash(name,barcode,netPrice);
		
	}
	
	@Override
	public boolean equals(Object other){
		if(other==null) {
			return false;
		}
		if(this==other) {
			return true;
		}
		if(!(other instanceof Item)) {
			return false;
		}
		Item o = (Item) other;
		if(this.barcode.equals(o.barcode) && this.name.equals(o.name) && this.netPrice==o.netPrice) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return String.format("%s %s Net: %.2f Sale: %.2f", name, barcode,netPrice,getSalePrice());
		
	}
	
}
