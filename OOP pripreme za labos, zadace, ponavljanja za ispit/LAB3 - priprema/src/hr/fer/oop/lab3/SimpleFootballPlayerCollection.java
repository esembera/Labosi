package hr.fer.oop.lab3;

public interface SimpleFootballPlayerCollection {
	public boolean add(FootballPlayer player);
	public int calculateEmotionSum();
	public int calculateSkillSum();
	public void clear();
	public boolean contains(FootballPlayer player);
	public int getMaxSize();
	public FootballPlayer[] getPlayers();
	public int size();
}
