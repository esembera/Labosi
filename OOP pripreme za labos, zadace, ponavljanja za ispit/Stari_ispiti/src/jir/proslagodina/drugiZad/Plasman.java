package jir.proslagodina.drugiZad;

public class Plasman {
	private int godina,redniBrojUtrke,plasman;
	private String vozac;
	
	public Plasman(int godina, int redniBrojUtrke, String vozac, int plasman) {
		this.godina=godina;
		this.redniBrojUtrke=redniBrojUtrke;
		this.vozac=vozac;
		this.plasman=plasman;
	}

	public int getGodina() {
		return godina;
	}

	public int getRedniBrojUtrke() {
		return redniBrojUtrke;
	}

	public int getPlasman() {
		return plasman;
	}

	public String getVozac() {
		return vozac;
	}

}
