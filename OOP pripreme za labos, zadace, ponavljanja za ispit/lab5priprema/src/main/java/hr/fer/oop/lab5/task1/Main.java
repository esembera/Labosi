package main.java.hr.fer.oop.lab5.task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
		Path racuni = Paths.get("./src/main/resources/racuni"); 
		MyFileVisitor visitor = new MyFileVisitor();
		
		try {			
			
			Files.walkFileTree(racuni, visitor);			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}					
	}
}
