package ljir.proslagodina.prviZad;

public class Pack extends Item{

	private Item[] itemsInPack;

	public Pack(String name, String barcode, Item[] itemsInPack) {
		super(name, barcode, itemsInPack[0].getNetPrice()*itemsInPack.length);
		this.itemsInPack=itemsInPack;
		
	}
	
	public Item[] getItemsInPack() {
		return itemsInPack;
	}

	public double getSalePrice() {
		return ((Item) itemsInPack[0]).getSalePrice()*itemsInPack.length;
	}
	
}
