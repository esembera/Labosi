package hr.fer.oop.task5;

import hr.fer.oop.task4.FileSearchMonitor;
import hr.fer.oop.task4.FileSearcher;

import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class FileSearcherWorker extends SwingWorker<List<String>, String> implements FileSearchMonitor {
	private String fromPath;
	private String toPath;
	private FileSearchMonitor monitor;
	
	
	public FileSearcherWorker(FileSearchMonitor monitor, String fromPath, String toPath) {
		this.monitor=monitor;
		this.fromPath=fromPath;
		this.toPath=toPath;
		
	}
	
	@Override
	protected List<String> doInBackground() throws Exception {
		FileSearcher searcher = new FileSearcher(this);
		return searcher.findPDFsInRange(this.fromPath, this.toPath);
	}

	@Override
	public void directoryChangedTo(String directory) {
		SwingUtilities.invokeLater(() -> {
			monitor.directoryChangedTo(directory);
		});
	}

	@Override
	public void fileFound(String filename) {
		SwingUtilities.invokeLater(() -> {
			monitor.fileFound(filename);
		});
	}

	@Override
	public void searchFinished() {
		SwingUtilities.invokeLater(() -> {
			monitor.searchFinished();
		});
	}

}
