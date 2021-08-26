package hr.fer.oop.lab6;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FactorialIterator implements Iterator{

	public Integer brojevi;
	public Integer i=0;;
	Integer rezultat = 0;
	
	public FactorialIterator(Integer br) {
		if(br<0) 
			throw new ArithmeticException();
		else
			this.brojevi=br;
	}
	
	
	@Override
	public boolean hasNext() {
		if(i.equals(brojevi))
			return false;
		else
			return true;
	}

	@Override
	public Integer next() {
		if(!hasNext())
			throw new NoSuchElementException();
		if(i.equals(0)) { 
			rezultat=rezultat.valueOf(1);
			i = Integer.valueOf(i.intValue()+1);
			return rezultat;
		}
		else if(i.equals(1)) {
			rezultat=rezultat.valueOf(1);
			i = Integer.valueOf(i.intValue()+1);
			return rezultat;
		}
		else {
			rezultat=Integer.valueOf(rezultat.intValue()*i.intValue());
			i = Integer.valueOf(i.intValue()+1);	
			return rezultat.intValue();	
		} 
	}
}
