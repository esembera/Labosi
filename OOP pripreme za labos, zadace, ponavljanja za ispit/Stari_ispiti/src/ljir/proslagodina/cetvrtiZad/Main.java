package ljir.proslagodina.cetvrtiZad;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		final String directory = "D:/OOP";
		final String extension = "*"; // ili "pdf" ili "ppt" ili "java" ...
		Path root = Paths.get(directory);
		MetadataFileVisitor visitor = new MetadataFileVisitor(extension);
		try {
			Files.walkFileTree(root, visitor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> lista = new ArrayList<>();
		
		lista = visitor.getMetaData();
		
		System.out.println(lista.stream().mapToInt(a -> Integer.parseInt(a.split(", ")[2])).filter(s -> s != 0).average().getAsDouble());
	}
}