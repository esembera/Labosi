package hr.fer.oop.lab6;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		Iterator<Integer> iterator = new FactorialIterator(10);
		while(iterator.hasNext())
		    System.out.println(iterator.next());
	    	System.out.println(iterator.next());


	}

}
