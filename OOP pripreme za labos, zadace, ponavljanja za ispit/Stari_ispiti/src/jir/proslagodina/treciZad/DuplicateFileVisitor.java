package jir.proslagodina.treciZad;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicateFileVisitor extends SimpleFileVisitor<Path> {
	private Map<String, Long> data = new HashMap<String, Long>();
	private Map<String, String> repo = new HashMap<String, String>();
	
	public Map<String, Long> getDeletedData() {
		return data;
	} 

	public Map<String, String> getDataRepo() {
		return repo;
	} 	
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		if(!(repo.containsValue(Digester.MD5(file)))) {
			repo.put(file.toString(), Digester.MD5(file));
		}else {
			deleteFile(file,attrs.size());
		}
		return FileVisitResult.CONTINUE;
	}

	private void deleteFile(Path file, long size) {
		data.put(file.toString(), size);
		try {
			Files.delete(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
