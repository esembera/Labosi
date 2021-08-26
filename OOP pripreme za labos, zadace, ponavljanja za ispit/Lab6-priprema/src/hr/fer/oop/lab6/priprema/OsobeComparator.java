package hr.fer.oop.lab6.priprema;

import java.util.Comparator;

public class OsobeComparator implements Comparator<Osoba>{

	@Override
	public int compare(Osoba o1, Osoba o2) {
		int r = o1.getOiB().compareTo(o2.getOiB());
		return r;
	}

}
