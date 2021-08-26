package ljir.ovagodina.treciZad;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class Dog implements Comparable<Dog>{
	private String name;
	private char gender;
	private Date dateOfBirth;
	private Set<Breed> breed;
	private int weight;
	private String owner;
	private Size size;

	public static final Comparator<Dog> BY_BREED_PURITY = new ByBreedPurityComparator();
	public static final Comparator<Dog> BY_WEIGHT = (a,b) -> Integer.compare(a.weight, b.weight);
	public static final Comparator<Dog> BY_BREED_THEN_WEIGHT = BY_BREED_PURITY.thenComparing(BY_WEIGHT);			
	private static class ByBreedPurityComparator implements Comparator<Dog>{

		@Override
		public int compare(Dog o1, Dog o2) {
			return Integer.compare(o1.breed.size(), o2.breed.size());
		}
	}

	public Dog(String name, char gender, Date dateOfBirth,
			Set<Breed> breed, int weight, String owner) {
			super();
			this.name=name;
			this.gender=gender;
			this.dateOfBirth=dateOfBirth;
			this.breed=breed;
			this.weight=weight;
			if(weight>10 && weight<=20) {
				this.size=Size.SMALL;
			}else if(weight>20 && weight<=35) {
				this.size=Size.MEDIUM;
			}else if(weight>35 && weight<=45) {
				this.size=Size.LARGE;
			}		
	}

		public int hashCode() {
			return Objects.hash(name,gender,dateOfBirth,owner);
		}

		public boolean equals(Object obj) {
			if(obj instanceof Dog) {
				Dog temp = (Dog) obj;
				if(temp.name.equals(this.name) && this.dateOfBirth.equals(temp.dateOfBirth) && this.gender==temp.gender && this.owner.equals(temp.owner)) {
					return true;
				}
				return false;
			}
			return false;	
		}
		
		public int compareTo(Dog other) {
			if(this.dateOfBirth.compareTo(other.dateOfBirth)!=0) {
				return this.dateOfBirth.compareTo(other.dateOfBirth);
			}else {
				return Integer.compare(this.weight, other.weight);
			}
		
		}

	
}

