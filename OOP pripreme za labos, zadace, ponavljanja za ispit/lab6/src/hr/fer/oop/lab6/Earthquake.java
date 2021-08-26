package hr.fer.oop.lab6;

class Earthquake {
    private String cityName;
    private String date; //YYYY-MM-DD 2020-05-07
    private int depth;
    private double magnitude;

    public Earthquake(String cityName, String date, int depth, double magnitude) {
        this.cityName = cityName;
        this.date = date;
        this.depth = depth;
        this.magnitude = magnitude;
    }
    public String getCityName() {
        return cityName;
    }
    public String getDate() {
        return date;
    }
    public int getDepth() {
        return depth;
    }
    public double getMagnitude() {
        return magnitude;
    }
    @Override
    public String toString() {
        return cityName + " " + date + " " + depth + " " + magnitude;
    }
}