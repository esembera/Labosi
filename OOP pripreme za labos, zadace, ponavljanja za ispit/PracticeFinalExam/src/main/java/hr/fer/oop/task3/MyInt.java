package hr.fer.oop.task3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyInt implements Iterable<Integer>{
	private int n=0;
	

	public MyInt(int i) {
		this.n=i;
	}

	@Override
	public Iterator<Integer> iterator() {
		
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<Integer>{
		private int i=2;
		private int num = n;
		@Override
		public boolean hasNext() {
			
			return i<=num;
			
		}

		@Override
		public Integer next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			if(num==1) {
				return num;
			}
			while(num%i!=0) {
				i++;
			}

			num/=i;
			
			return i;
		}
		
	}
	
}
