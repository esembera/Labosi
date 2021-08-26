package hr.fer.oop.lab6.priprema;

import java.util.Comparator;

public class PoslovniPartnerComparator implements Comparator<PoslovniPartner> {
	int r;
	@Override
	public int compare(PoslovniPartner p1, PoslovniPartner p2) {
		if(p1 instanceof Tvrtka && p2 instanceof Tvrtka){
			Tvrtka a1 = (Tvrtka) p1;
			Tvrtka a2 = (Tvrtka) p2;
			r = -(a1.getZaposlenici().size() - a2.getZaposlenici().size());
			if(r!=0)
				return r;
			return a1.getOiB().compareTo(a2.getOiB());
		}
		if(p1 instanceof Osoba && p2 instanceof Osoba) {
			return p1.getOiB().compareTo(p2.getOiB());
				

		}
		if(p1 instanceof Osoba && p2 instanceof Tvrtka) {
			return 1;
		}
		return -1;
	}

}
