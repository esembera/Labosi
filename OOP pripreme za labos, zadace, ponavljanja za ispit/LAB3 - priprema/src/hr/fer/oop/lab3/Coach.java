package hr.fer.oop.lab3;

public class Coach extends Person {
	private int coachingSkill=50;
	private Formation formation;
	
	public Coach(String name, String country) {
		super(name,country);
	}
	
	public Coach(String name, String country, int emotion, int coachingSkill, Formation formation) {
		super(name, country, emotion);
		this.formation=formation;
		this.setCoachingSkill(coachingSkill);	
	}
	
	public void setCoachingSkill(int coachingSkill){
		if (coachingSkill<0 || coachingSkill>100) {
			System.out.println("Molimo unesite vrijednost izmedu 0 i 100.");
		}else {
			this.coachingSkill=coachingSkill;
		}
	}
	public int getCoachingSkill() {
		return this.coachingSkill;
	}
}
