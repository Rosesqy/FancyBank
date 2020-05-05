package Bank.CustomerPlatform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ChooseAction implements ActionListener {
	
	JFrame frame;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	
	public ChooseAction() {
		button1 = new JButton("Create an account");
		button2 = new JButton("View accounts information");
		button3 = new JButton("Make a deposit");
		button4 = new JButton("Withdraw");
		button5 = new JButton("Make a transaction");
		frame = new JFrame("Choose what you want to do");
		frame.setSize(500, 400);
		button1.setBounds(0, 50, 200, 50);
		button2.setBounds(0, 150, 200, 50);
		button3.setBounds(0, 250, 200, 50);
		button4.setBounds(300, 50, 200, 50);
		button5.setBounds(300, 150, 200, 50);
		frame.add(button1);
		frame.add(button2);
		frame.add(button3);
		frame.add(button4);
		frame.add(button5);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
