package hr.fer.oop.lab3;

public abstract class Person {
	private String name;
	private String country;
	private int emotion=50;
	
	public Person() {
		
	}
	
	public Person(String name, String country) {
		this.name=name;
		this.country=country;
	}
	
	public Person(String name, String country, int emotion) {
		this.name=name;
		this.country=country;
		this.setEmotion(emotion);
	}
	
	public void setEmotion(int emotion){
		if (emotion<0 || emotion>100) {
			System.out.println("Molimo unesite vrijednost izmedu 0 i 100.");
		}else {		
			this.emotion=emotion;
		}
	}
	public int getEmotion() {
		return this.emotion;
	}
	
	public boolean samePerson(Person other) {
		if (this.name==other.name && this.country==other.country) {
			return true;
		}else {
			return false;
		}
	}
}
