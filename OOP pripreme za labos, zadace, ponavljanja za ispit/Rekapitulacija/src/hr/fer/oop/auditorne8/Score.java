package hr.fer.oop.auditorne8;

public class Score implements Comparable {

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	private Double score;
	
	public Score(String ime, Double rez) {
		this.name=ime;
		this.score=rez;
		
	}
	
	public String toString() {
		return "Ime:" +" "+  name + " " + "Rezultat:" + " " + score;  
	}

	@Override
	public int compareTo(Object o) {
		Score s = (Score) o;
		return Double.compare(s.getScore(), this.getScore());
	}
	
	
	
	
}
