package hr.fer.oop.task4;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileSearcher {

    private FileSearchMonitor monitor;

    public FileSearcher(FileSearchMonitor monitor) {
        this.monitor = monitor;
    }

    public List<String> findPDFsInRange(String from, String to) {

        List<String> pdfovi = new ArrayList<String>();
        
        Path fromPath = Paths.get(from);
        Path toPath = Paths.get(to);
        
        MyFileVisitor visitor = new MyFileVisitor(toPath, pdfovi);
        
        try {
			Files.walkFileTree(fromPath, visitor);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        monitor.searchFinished();
        return pdfovi;
    		
    }

    private class MyFileVisitor extends SimpleFileVisitor<Path>{
    	private int i = 0;
    	private Path to;
    	private List<String> fileNameList;
    	public MyFileVisitor(Path toDirectory, List<String> list) {
    		this.to=toDirectory;
    		this.fileNameList=list;
    	}
    	
    	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    		boolean onGoodPath = to.startsWith(dir);
    		if(onGoodPath) {
    			monitor.directoryChangedTo(dir.toString());
    			return FileVisitResult.CONTINUE;
    			    	
    		}else {
    			return FileVisitResult.SKIP_SUBTREE;
    			
    		}
    		
    	}
    	
    	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    		if(i==0 || i==2) {
    			if(file.toString().toUpperCase().endsWith(".PDF")){
    				monitor.fileFound(file.toString());
    				fileNameList.add(file.toString());
    				
    			}
    		}
    		return FileVisitResult.CONTINUE;    	
    		}
	
    }
}