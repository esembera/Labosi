package jir.proslagodina.prviZad;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Chef implements Comparable<Chef>{
	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	private String firstName, lastName;
	private Set<SpecialtyDish> specialiteti;
	
	
	public Chef(String firstName, String lastName) {
		if(specialiteti==null) {
			specialiteti = new HashSet<>();
		}
		this.firstName=firstName;
		this.lastName=lastName;
	}
	
	
	public void addDish(SpecialtyDish dish) {
		specialiteti.add(dish);
	}
	
	public int hashCode() {
		return Objects.hash(specialiteti);
	}
	
	public boolean equals(Object o) {
		if(o instanceof Chef) {
			Chef temp = (Chef) o;
			if(this.specialiteti.equals(temp.specialiteti))
				return true;
			return false;
		}
		return false;
	}


	@Override
	public int compareTo(Chef o) {
		return Comparator.comparing(Chef::getLastName).thenComparing(Chef::getFirstName).compare(this,o);
	}
	
	
	
	
}
