package hr.fer.oop.second;

public class Point {
	private double x;
	private double y;
	
	public Point() {
	}
	
	public Point(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	public Point(Point point) {
		this(point.x,point.y);
	}
	
	public String toString() {
		return String.format("x = %.2f, y = %.2f", x,y);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x=x;
	}
	
	public void setY(double y) {
		this.y=y;
	}
	
	public boolean equals(Object other) {
		Point otherPoint=(Point) other;
		return Math.abs(this.x-otherPoint.x)< 1E-8 && Math.abs(this.y-otherPoint.y)<1E-8;
	}
	
	public static Point center(Point point1, Point point2, Point point3) {
		double x=0, y=0;
		
		x=(point1.x+point2.x+point3.x)/3;
		y=(point1.y+point2.y+point3.y)/3;
		
		Point center = new Point(x, y);
		return center;
	}
	
	public static Point center(Point a, Point b, Point...points) {
		double x=a.x+b.x, y=a.y+b.y;
		
		for (Point point:points) {
			x+=point.x;
			y+=point.y;
		}
		
		Point center = new Point(x/(points.length+2), y/(points.length+2));
		return center;
	}
	
}
