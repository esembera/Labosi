package hr.fer.oop.lab6.priprema;

public class Osoba extends PoslovniPartner{
	private String Ime = new String();
	private String Prezime = new String();
	

	public Osoba(String OiB,String Ime, String Prezime) {
		super(OiB);
		this.Ime=Ime;
		this.Prezime=Prezime;
	}


	public String getIme() {
		return Ime;
	}


	public String getPrezime() {
		return Prezime;
	}
	
	
	@Override
	public String toString() {
		return Ime + " " +  Prezime + " - " + super.getOiB();
		
	}
	
	@Override
	public int hashCode() {
		return super.getOiB().hashCode();
	}
	
	
	public boolean equals(Object o) {
		if(!(o instanceof Osoba)) return false;
		Osoba druga = (Osoba) o;
		return druga.getOiB().equals(this.getOiB());
	}
}
