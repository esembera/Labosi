package hr.fer.oop.lab3;

public class SimpleFootballPlayerCollectionImpl implements SimpleFootballPlayerCollection {
	private FootballPlayer[] players;
	private int count = 0;
	private int size;
	
	public SimpleFootballPlayerCollectionImpl(int velicina) {
		this.size=velicina;
		players = new FootballPlayer[velicina];
	}
	
	
	@Override
	public boolean add(FootballPlayer player) {
		if (count<size) {
			this.players[count]=player;
			count++;
			return true;
		}else {
			return false;
		}
	}


	@Override
	public int calculateEmotionSum() {
		int sum=0;
		for(FootballPlayer player:players) {
			if(player!=null) {
				sum+=player.getEmotion();			
			}
		}
		return sum;
	}


	@Override
	public int calculateSkillSum() {
		int sum=0;
		for(FootballPlayer player:players) {
			if(player!=null) {
				sum+=player.getPlayingSkill();			
			}
		}
		return sum;
	}


	@Override
	public void clear() {
		this.players=new FootballPlayer[size];
		this.count=0;
	}


	@Override
	public boolean contains(FootballPlayer player) {
		int count=0;
		for(FootballPlayer player1:players) {
		if (player1.samePerson(player)==true)
			count++;
		}
		if(count>0) {
			return true;
		}else {
			return false;
		}
	}


	@Override
	public int getMaxSize() {
		return this.size;
	}


	@Override
	public FootballPlayer[] getPlayers() {
			return players;
	}


	@Override
	public int size() {
		return this.count;
	}
	
	

}
