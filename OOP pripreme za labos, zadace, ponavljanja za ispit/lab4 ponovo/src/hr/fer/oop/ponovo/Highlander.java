package hr.fer.oop.ponovo;

public class Highlander {
	int nastavak;
	
	
	public Highlander() throws OnlyOneException{}

	private Highlander(int nastavak) {
		if(nastavak>5 || nastavak <1) {
			throw new WaitForItException();
		}
		
		this.nastavak=nastavak;
	}
	
	
	public static void callMacLeod(int nastavak) {
			
		
		if(h.nastavak == null) {
			Highlander h = new Highlander(nastavak);
		}else {
			Highlander(h);
		}
		
	}
	
	public int getSequel() {
		return this.nastavak;
	}
	
}
