package ljir.proslagodina.cetvrtiZad;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Function;

public class MetadataFileVisitor extends SimpleFileVisitor<Path> {
	private List<String> data = new LinkedList<>();

	public List<String> getMetaData() {
		return data;
	} // dohvaćanje liste

	private String fileExtension;

	public MetadataFileVisitor(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		fillList(dir, attrs);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path dir, BasicFileAttributes attrs) throws IOException{
		if(fileExtension.equals("*")) {
			fillList(dir,attrs);
			return FileVisitResult.CONTINUE;
		}
		if(dir.toString().endsWith(fileExtension)) 
			fillList(dir,attrs);
		return FileVisitResult.CONTINUE;
	}
	
	

	private void fillList(Path file, BasicFileAttributes attr) {
		StringBuilder sb = new StringBuilder();
		Function<String, String> f = a -> {
			a = a.replace("T", " ");
			int i = a.lastIndexOf(":");
			String b = String.copyValueOf(a.toCharArray(), 0, i);
			return b;
		};
		
		sb.append(file.getFileName());
		sb.append(", ");
		sb.append(f.apply(attr.creationTime().toString()));
		sb.append(", ");
		if(attr.isDirectory()) sb.append("0");
		else sb.append(attr.size());
		data.add(sb.toString());
		
		System.out.println(sb.toString());
	}
}
