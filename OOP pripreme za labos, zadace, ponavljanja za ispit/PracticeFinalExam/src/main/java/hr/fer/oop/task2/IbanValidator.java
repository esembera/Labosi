package hr.fer.oop.task2;

import org.apache.commons.validator.routines.checkdigit.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class IbanValidator extends JFrame {
	private ActionListener buttonListener;

	private boolean gejko;
	
	
	public IbanValidator() {
		setTitle("IbanValidator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		GridLayout layout = new GridLayout(3,0);
		setLayout(layout);
		
		
		JLabel lblIban = new JLabel("IBAN");
		add(lblIban);

		JTextField tfInput = new JTextField();
		add(tfInput);
		tfInput.setColumns(10);
		
		JLabel lblValidan = new JLabel("Validan:");
		add(lblValidan);

		JTextField tfValidan = new JTextField();
		tfValidan.setEditable(false);
		add(tfValidan);
		tfValidan.setColumns(10);
		
		add(new JLabel());
		JButton btnSubmit = new JButton("Validiraj");
		
		buttonListener = (actionEvent) -> {
			btnSubmit.setEnabled(false);
			new Thread(() -> {
				IBANCheckDigit peder = new IBANCheckDigit();
				String IBAN;
				IBAN = tfInput.getText();
				gejko=peder.isValid(IBAN);
				if(gejko) {
					tfValidan.setText("DA");
				}else {
					tfValidan.setText("NE");
				}
				SwingUtilities.invokeLater(() -> btnSubmit.setEnabled(true));;
			}).start();
		};
		

		btnSubmit.addActionListener(buttonListener);
		add(btnSubmit);
		

		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()-> {
			JFrame frame = new IbanValidator();
			frame.setLocation(600, 300);
			frame.setSize(600,120);
			frame.setVisible(true);
		});
	}
	
}
