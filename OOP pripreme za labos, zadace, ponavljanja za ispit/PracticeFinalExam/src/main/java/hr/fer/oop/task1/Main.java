package hr.fer.oop.task1;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Set<Driver> drivers = DBLoader.loadDrivers();
        
        drivers.stream().filter(s -> s.getAddress().contains("Zagreb")).sorted(
        		Comparator.comparing(Driver::getPid)).collect(Collectors.toList()).forEach((s)->System.out.println(s.toString())); 
                
        drivers.stream().filter(s -> s.getSurname().startsWith("M")).mapToLong(
        		s -> s.getPid()).boxed().collect(Collectors.toSet()).forEach(s -> System.out.println(s.toString()));        
       
        System.out.println(drivers.stream().mapToInt(s -> s.getFirstName().length()).average().getAsDouble());
        
        
    }
}