package Bank.CustomerPlatform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Initiate implements ActionListener {
	
	JFrame frame;
	JButton button1;
	JButton button2;
	JLabel label;
	
	public Initiate() {
		frame = new JFrame("FancyBank");
		frame.setSize(500, 400);
		label = new JLabel("Welcome to the Fancy Bank!");
		button1 = new JButton("Manager");
		button2 = new JButton("Customer");
		button1.setBounds(150, 150, 200, 50);
		button2.setBounds(150, 250, 200, 50);
		button1.addActionListener(this);
		button2.addActionListener(this);
		label.setBounds(170, 50, 180, 50);
		frame.add(button1);
		frame.add(button2);
		frame.add(label);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1) {
			//new ManagerInter();
		}
		else if(e.getSource() == button2) {
			new CustomerInter();
		}
		
	}

}
