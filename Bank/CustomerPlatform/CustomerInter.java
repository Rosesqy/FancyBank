package Bank.CustomerPlatform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CustomerInter implements ActionListener {
	
	JFrame frame;
	JButton button1;
	JButton button2;
	
	public CustomerInter() {
		frame = new JFrame("Customer");
		frame.setSize(500, 400);
		button1 = new JButton("Log in");
		button2 = new JButton("Register");
		button1.setBounds(150, 100, 200, 50);
		button2.setBounds(150, 200, 200, 50);
		button1.addActionListener(this);
		button2.addActionListener(this);
		frame.add(button1);
		frame.add(button2);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1) {
			new CustomerLogin();
		}
		else if(e.getSource() == button2) {
			new CustomerRegister();
		}
		
	}

}
