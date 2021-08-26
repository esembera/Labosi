package jir.proslagodina.treciZad;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
		final String directory = "D:\\OOP";
		Path root = Paths.get(directory);
		DuplicateFileVisitor visitor = new DuplicateFileVisitor();
		try {
			Files.walkFileTree(root, visitor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Freed disk space " + 
		visitor.getDeletedData().values().
				stream().mapToLong(m -> m).sum() + " Deleted files " + visitor.getDeletedData().keySet().size());
		
		

	}

}
