package fancybank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ChooseAction implements ActionListener {
	String username;
	JFrame frame;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	public ChooseAction(String username) {
		this.username = username;
		button1 = new JButton("Create an account");
		button2 = new JButton("View accounts information");
		button3 = new JButton("Make a deposit");
		button4 = new JButton("Withdraw");
		button5 = new JButton("Make a transaction");
		button6 = new JButton("Buy/Sell stock");
		frame = new JFrame("Choose what you want to do");
		frame.setSize(500, 400);
		button1.setBounds(0, 50, 200, 50);
		button2.setBounds(0, 150, 200, 50);
		button3.setBounds(0, 250, 200, 50);
		button4.setBounds(300, 50, 200, 50);
		button5.setBounds(300, 150, 200, 50);
		button6.setBounds(300, 250, 200, 50);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		frame.add(button1);
		frame.add(button2);
		frame.add(button3);
		frame.add(button4);
		frame.add(button5);
		frame.add(button6);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1) {
			new CreateAccount(username);
		}
		else if(e.getSource() == button2) {
			new ShowAccountInfo(username);
		}
		else if(e.getSource() == button3) {
			new MakeDeposit(username);
		}
		else if(e.getSource() == button4) {
			new MakeWithdrawal(username);
		}
		else if(e.getSource() == button5) {
			new MakeTransaction(username);
		}
		else if(e.getSource() == button6) {
			new DealStock(username);
		}
	}
}
