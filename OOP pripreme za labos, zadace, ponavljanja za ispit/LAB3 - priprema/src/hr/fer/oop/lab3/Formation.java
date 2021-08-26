package hr.fer.oop.lab3;

public class Formation {
	private int noDF;
	private int noMF;
	private int noFW;
	private final int noGK = 1;

	private Formation(int noDF, int noMF, int noFW) {
		this.noDF = noDF;
		this.noMF = noMF;
		this.noFW = noFW;
	}

	public int getNoDF() {
		return noDF;
	}

	public int getNoMF() {
		return noMF;
	}

	public int getNoFW() {
		return noFW;
	}

	public int getNoGK() {
		return noGK;
	}

	public static Formation F442() {
		return new Formation(4, 4, 2);
	}

	public static Formation F352() {
		return new Formation(3, 5, 2);
	}

	public static Formation F541() {
		return new Formation(5, 4, 1);
	}
}
