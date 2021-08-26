package hr.fer.oop.rekapitulacija;

public class Pack extends Item {
	private Item[] itemsInPack;
	public Item[] getItemsInPack() {
		return itemsInPack; 
		}
	
	public Pack(String name, String barcode, Item[] pack) {
		super(name, barcode,(pack[0].getNetPrice()*pack.length));
		this.itemsInPack=pack;
		
	}

	@Override
	public double getSalePrice() {
		return itemsInPack[0].getSalePrice()*itemsInPack.length;
	}

}
