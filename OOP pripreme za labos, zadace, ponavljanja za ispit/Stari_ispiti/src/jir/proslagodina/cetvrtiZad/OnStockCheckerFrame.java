package jir.proslagodina.cetvrtiZad;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

public class OnStockCheckerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lbl1 = new JLabel("Item:");
	private JTextField tf = new JTextField(20);
	private JButton btn = new JButton("Check");
	private JTextArea ta = new JTextArea(10, 0);
	private JLabel lbl2 = new JLabel("Found items: ");

	public OnStockCheckerFrame() {
		
		setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		northPanel.add(lbl1);
		northPanel.add(tf);
		northPanel.add(btn);
		add(northPanel, BorderLayout.NORTH);
		
		JScrollPane sp = new JScrollPane(ta);
		add(sp,BorderLayout.CENTER);
		
		add(lbl2, BorderLayout.SOUTH);
		
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn.setEnabled(false);
				lbl2.setText("Found item: ");
				ta.setText("");
				
				OnStockCheckerWorker bs = new OnStockCheckerWorker(tf.getText());
				bs.execute();
			}
		});
	} // public OnStockCheckerFrame()

	private class OnStockCheckerWorker extends SwingWorker<Integer,String>{
		
		String ID;
		Integer total;
		
		public OnStockCheckerWorker(String itemID) {
			this.ID=itemID;
			total=0;
		}

		@Override
		protected Integer doInBackground() throws Exception {
			
			for(String warehouse : RemoteDatabaseChecker.getRemoteWarehouses()) {
				int num = RemoteDatabaseChecker.checkRemoteForItem(warehouse, ID);
				total+=num;
				publish(warehouse + " " + num);
			}
			
			return total;
		}

		@Override
		  protected void process(List<String> chunks) {
			
			for(String i:chunks) {
				ta.append(i + "\n");
			}
		  }

		@Override
		protected void done() {
			lbl2.setText("Found items: " + total);
			btn.setEnabled(true);	
		}
	} 

	
	
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				OnStockCheckerFrame frame = new OnStockCheckerFrame();
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}




