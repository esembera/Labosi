package hr.fer.oop.second;


public class Main {
	public static void main(String[] args) {
		Point p1 = new Point (2.0, 5.5);
		Point p2 = new Point(p1);
		System.out.println(p1.toString());
		
		boolean result = p1.equals(p2);
		System.out.println(result);
		
		Object o2 = p2;
		
		result = p1.equals(o2);
		
		System.out.println(result);
		
	}
}
