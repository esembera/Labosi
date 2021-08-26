package ljir.ovagodina.petiZad;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

public final class LetterCounterFrame extends JFrame {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new LetterCounterFrame();
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
			frame.setLocation(400, 400);
		});
	}

	
	
	
	private JTextArea textArea;
	private JLabel[] labels = new JLabel[26]; // JLabels for counting characters
	
	

	public LetterCounterFrame() {
		textArea = new JTextArea(5, 60); // sets JTextArea size
		textArea.setLineWrap(true);
		add(textArea, BorderLayout.CENTER);
		
		JButton button = new JButton("Count");
		
		JPanel southPanel = new JPanel();
		southPanel.add(button);
		add(southPanel, BorderLayout.SOUTH);
		
		JPanel northPanel = new JPanel(new GridLayout(2,0));
		for(int i = 0; i < 26; i++) {
			labels[i]=new JLabel();
			String label = String.format("%c:",i+'a');
			labels[i].setText(label);	
			northPanel.add(labels[i]);

		}
		
		add(northPanel, BorderLayout.NORTH);
		
		button.addActionListener((ActionEvent e) -> {
			// TODO implement the button listener
			String text = textArea.getText().toLowerCase();
			int[] temp = new int[26] ;
			for(int i=0;i<26;i++) {
				temp[i]=0;
			}
			
			char[] neki = text.toCharArray();
			for(int i=0;i<neki.length;i++) {
				if(neki[i]>='a' && neki[i]<='z') {
					int x = (int)(neki[i] - 'a');
					temp[x]++;
				}
			}
			for(int i=0;i<26;i++) {
				String label = String.format("%c: %d",i+'a',temp[i]);
				labels[i].setText(label);
			}
			
			
		});
	}
}