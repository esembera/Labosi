package hr.fer.oop.lab4;

class Triple<T>{
	private T first;
	private T second;
	private T third;
	public Triple(T first, T second, T third) {
		this.first=first;
		this.second=second;
		this.third=third;
	}
	
	public T getElement(int br){
		if(br==1) {
			return (T)this.first;
		}else if(br==2) {
			return (T)this.second;
		}else if(br==3){
			return (T)this.third;
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	public void setElement(int br, T other) {
		if(br==1) {
			this.first=other;
		}else if(br==2) {
			this.second=other;
		}else if(br==3){
			this.third=other;
		}else {
			throw new IllegalArgumentException();
		}
	}
	
}


class Triangle extends Triple<Number>{
	public Triangle(Number first, Number second, Number third) {
		
      super(first, second, third);
      if (first.floatValue()+second.floatValue()<=third.floatValue() || third.floatValue()+second.floatValue()<=first.floatValue() || first.floatValue()+third.floatValue()<=second.floatValue()){
			throw new IllegalArgumentException();
		}
      
	}
	
	public double perimeter() {
		double opseg=0;
		opseg=(double)super.getElement(1).floatValue()+(double)super.getElement(2).floatValue()+(double)super.getElement(3).floatValue();
		return opseg;
	}
}
