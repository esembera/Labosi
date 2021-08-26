package hr.fer.oop.lab3;

public class FootballPlayer extends Person {

	private PlayingPosition position;
	private int playingSkill=50;
	
	public FootballPlayer() {
		
	}
	public FootballPlayer(String name, String country, PlayingPosition position) {
		super(name, country);
		if(position==null) {
			this.position=PlayingPosition.MF;
		}else {
			this.position=position;	
		}
		
	}
	
	public FootballPlayer(String name, String country, int emotion, int playingSkill, PlayingPosition position) {
		super(name, country, emotion);
		this.playingSkill=playingSkill;
		this.setPlayingSkill(playingSkill);
		if(position==null) {
			this.position=PlayingPosition.MF;
		}else {
			this.position=position;	
		}

	}
	
	public void setPlayingSkill(int playingSkill){
		if (playingSkill<0 || playingSkill>100) {
			System.out.println("Molimo unesite vrijednost izmedu 0 i 100.");
			this.playingSkill=50;
		}else {
			this.playingSkill=playingSkill;
		}
	}
	
	public int getPlayingSkill() {
		return this.playingSkill;
	}
	
	public void setPlayingPosition(PlayingPosition position){
		this.position=position;
	}
	
	public PlayingPosition getPlayingPosition() {
		return this.position;
	}
	
}
