package hr.fer.oop.first;

public class Vector {
	private final Point point;
	private static int counter = 0;
	private int id;
	
	public Vector(Point point) {
		this.point = new Point(point.getX(),point.getY());
		id=counter++;
		
	}
	
	public Point getPoint() {
		return this.point;
	}
	
	public void print() {
		this.point.print();
	}
	

	
	public int getId() {
		return id;
	}
}

