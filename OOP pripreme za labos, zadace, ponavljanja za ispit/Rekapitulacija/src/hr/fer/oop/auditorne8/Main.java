package hr.fer.oop.auditorne8;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		JavelinScores js = new JavelinScores();
		js.add("Katica", 50.69);
		js.add("Salama", 10.78);
		js.add("Meshki", 120.22);
		js.add("Salama", 20.69);
		js.add("Katica", 65.72);
		js.add("Salama", 100.78);
		js.add("Branach", 17.20);
		js.add("Branach", 29.36);
		
		js.print();
		
		
		
		/*Set s = new HashSet();
		s.add("a");
		s.add("b");
		s.add("c");
		s.add("d");
		Set z = new HashSet();
		z.add("c");
		z.add("d");
		z.add("e");
		z.add("f");
		
		System.out.println(SetOperations.union(s,z));
		System.out.println(SetOperations.cut(s,z));
		System.out.println(SetOperations.difference(s,z));
	*/
	
	}

}
