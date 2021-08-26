package hr.fer.oop.first;

public class Main {
	public static void main (String[] args) {
		Point point1 = new Point(2.7, 3.6);
		Point point2 = new Point(point1);
		Point point3 = new Point(1.1, 6.2);
		Point point4 = new Point(2.3, 4.5);

		Point center = Point.center(point1, point2, point3);
		center.print();
		
		Vector vector1 = new Vector(point1); 
		System.out.println(vector1.getId());
		
		Vector vector2 = new Vector (point2);
		System.out.println(vector2.getId());
		
		for(int i=0;i<10;i++) {
			Vector vector = new Vector(point3);
			System.out.println(vector.getId());
		}
	}
}
