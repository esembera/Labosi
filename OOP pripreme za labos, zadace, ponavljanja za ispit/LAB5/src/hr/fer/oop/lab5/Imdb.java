package hr.fer.oop.lab5;

import java.util.*;
import java.util.Map.Entry;

class Utils {
	String name;
	MovieInfo movieInfo;
	HashMap<String, MovieInfo> movies;

	public static double getDirectorAverageRating(HashMap<String, MovieInfo> movies, String director) {
		double prosjek = 0;
		int brojac = 0;
		for (Map.Entry<String, MovieInfo> entry : movies.entrySet()) {
			MovieInfo v = entry.getValue();
			if (v.getDirector().equals(director)) {
				prosjek += v.getAverageRating();
				++brojac;
			}
		}
		return prosjek / brojac;
	}

	public static String getHighestRatedByYear(HashMap<String, MovieInfo> movies, int year) {

		double max = -1;
		for (Map.Entry<String, MovieInfo> entry : movies.entrySet()) {
			MovieInfo v = entry.getValue();
			if (max < v.getAverageRating() && year == v.getYear()) {
				max = v.getAverageRating();
			}
		}

		for (Map.Entry<String, MovieInfo> entry : movies.entrySet()) {
			String k = entry.getKey();
			MovieInfo v = entry.getValue();
			if (max == v.getAverageRating() && year == v.getYear()) {
				return k;
			}
		}
		return null;
	}

	public static void addNewMovie(String name, MovieInfo movieInfo, HashMap<String, MovieInfo> movies) {
		movies.put(name, movieInfo);
	}

	public static void changeRating(String name, Rating ratingSite, int newRating, HashMap<String, MovieInfo> movies) {

		for (Map.Entry<String, MovieInfo> entry : movies.entrySet()) {
			MovieInfo v = entry.getValue();
			String k = entry.getKey();

			if (k == name) {
				if (ratingSite == Rating.IMDB) {
					movies.get(name).setIMDBRating(newRating);
				}else if (ratingSite == Rating.RottenTomatoes) {
					movies.get(name).setRottenTomatoesRating(newRating);	
				}
				movies.get(name).setAverageRating((movies.get(name).getIMDBRating()+movies.get(name).getRottenTomatoesRating())/2d);
			}	
		}
	}

	public static double getAverageMovieRating(String name, HashMap<String, MovieInfo> movies) {
		double prosjek = 0;
		int brojac = 0;
		for (Map.Entry<String, MovieInfo> entry : movies.entrySet()) {
			MovieInfo v = entry.getValue();
			prosjek += v.getAverageRating();
			++brojac;
		}
		return prosjek / brojac;
	}

	public static void removeMovieMadeBefore(HashMap<String, MovieInfo> movies, int year) {
		for (Map.Entry<String, MovieInfo> entry : movies.entrySet()) {
			MovieInfo v = entry.getValue();
			String k = entry.getKey();

			if (v.getYear() < year) {
				movies.remove(k);
			}
		}
	}
}