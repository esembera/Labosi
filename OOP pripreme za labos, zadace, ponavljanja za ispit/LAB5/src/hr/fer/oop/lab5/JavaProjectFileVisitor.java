package hr.fer.oop.lab5;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class JavaProjectFileVisitor extends SimpleFileVisitor<Path>{
	private Set<String> ekstenzije;
	private Map<String, Set<Path>> projekt = new LinkedHashMap<>();

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		
		for(String a:ekstenzije) {
			if(file.toString().endsWith((a))){
				if(projekt.get(a)==null) {
					projekt.put(a,new LinkedHashSet<Path>());
				}
				projekt.get(a).add(file);
			}
		}
		return FileVisitResult.CONTINUE;
	}
	
	public JavaProjectFileVisitor(Set<String> extensionFilter) {
		this.ekstenzije=extensionFilter;
	}
	public Map<String, Set<Path>> getProjectPathsPerExtension(){

		return projekt;
	}
	
	
}
