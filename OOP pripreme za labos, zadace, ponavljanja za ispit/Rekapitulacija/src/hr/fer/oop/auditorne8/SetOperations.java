package hr.fer.oop.auditorne8;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {
	
	
	public static Set union(Set a, Set b) {
		Set x = new HashSet();
		for(Object o: a) {
			x.add(o);
		}
		for(Object o1: b) {
			x.add(o1);
		}
		return x;
		
	}
	public static Set cut(Set a, Set b) {
		Set x = new HashSet();
		Set y=SetOperations.union(a, b);
		for(Object o: y) {
			for(Object o1: a) {
				for(Object o2: b) {
					if(o.equals(o1) && o.equals(o2)) {
						x.add(o);
					}
				}
			}
		}
		return x;
	}
	public static Set difference(Set a, Set b) {
		Set x = new HashSet();
		for(Object o: a) {
			x.add(o);
		}
		for(Object o1:b) {
			x.remove(o1);
		}
		return x;
	}
}
