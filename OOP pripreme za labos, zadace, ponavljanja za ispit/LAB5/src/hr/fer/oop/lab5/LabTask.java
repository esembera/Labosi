package hr.fer.oop.lab5;

import java.nio.file.Path;
import java.util.*;

public class LabTask {
	public static Set<String> theMostKnown(Map<String, Set<String>> map) {
		Map<String,Integer> osobe = new TreeMap<>();
		Set<String> osoba = new TreeSet<>();
		Integer br=0;
		Integer val=0;
		for (Set<String> a: map.values()) {
				for(String b: a) {
					val = osobe.get(b);
					osobe.put(b, val == null ? 1: val +1 );
				}
			}
		
		for (Map.Entry<String, Integer> c: osobe.entrySet()) {
			if(c.getValue()==br) {
				osoba.add(c.getKey());
			}else if(c.getValue()>br) {
				br=c.getValue();
				osoba.clear();
				osoba.add(c.getKey());
			}
		}
		
		return osoba;
	}
}
