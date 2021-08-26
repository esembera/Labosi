package main.java.hr.fer.oop.lab5.task1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class MyFileVisitor extends SimpleFileVisitor<Path>{
	
	int brojRacuna;
	double ukPromet;
	

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		
		if(dir.getFileName().toString().length()==4) {
			brojRacuna=0;
			ukPromet=0.;
		}
		return FileVisitResult.CONTINUE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		if(file.toString().endsWith(".txt")) {
			 brojRacuna++;
			 try(Stream<String> strimcina = Files.lines(file)) {
			 for(String line:(Iterable<String>)strimcina::iterator) {
				 if(line.startsWith("UKUPNO")) {
					String[] poljetina = line.split(" ");
					ukPromet+=Double.parseDouble(poljetina[poljetina.length-1]);
				 }
			 }
			 }	 
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		if(dir.getFileName().toString().length()==4) {
			new File("./src/Output").mkdir();
			File pimpekJoza = new File("./src/Output/"+dir.getFileName().toString()+".txt");
			pimpekJoza.createNewFile(); 
			try{
			      FileWriter myWriter = new FileWriter("./src/Output/"+dir.getFileName().toString()+".txt");
			      myWriter.write("Broj racuna: " + brojRacuna +"\nUkupan promet: " + ukPromet);
			      myWriter.close();
			}catch (IOException e) {
			      e.printStackTrace();
			    }
		}
		
		return FileVisitResult.CONTINUE;
	}

}
