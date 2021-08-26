package hr.fer.oop.lab6.priprema;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class Tvrtka extends PoslovniPartner{
	private String ImeTvrtke = new String();
	
	public Tvrtka(String OiB, String ImeTvrtke) {
		super(OiB);
		this.ImeTvrtke=ImeTvrtke;
	}

	public String getImeTvrtke() {
		return ImeTvrtke;
	}
	
	Set<Osoba> zaposlenici = new LinkedHashSet<>();
	

	public void add(Osoba o) {
		zaposlenici.add(o);
	}
	
	public Set<Osoba> getZaposlenici(){
		return zaposlenici;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		String ispis = ImeTvrtke + " " + super.getOiB() + "\n";
		str.append(ispis);
		zaposlenici.forEach((o) -> {str.append("\t"+o.toString()+"\n");});
		return str.toString();
	}
	
}
