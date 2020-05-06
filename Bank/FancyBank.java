package Bank;

import Bank.BankManagerPlatform.BankManagerPlatform;
import Bank.CustomerPlatform.CustomerInter;
import Bank.Utilities.CreateDataBase;
import Bank.Utilities.DbHelperPSQL;
import Bank.Utilities.InitializeTables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class FancyBank implements ActionListener {
	
	JFrame frame;
	JButton button1;
	JButton button2;
	JLabel label;
	
	public FancyBank() {
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
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1) {
			BankManagerPlatform bm = new BankManagerPlatform();
			bm.setVisible(true);
			frame.dispose();
		}
		else if(e.getSource() == button2) {
			CustomerInter ci = new CustomerInter();
			frame.dispose();
		}
		
	}

	public static void main(String args[]) throws SQLException {
		new CreateDataBase();
		DbHelperPSQL helper = new DbHelperPSQL();
			helper.test();
		new FancyBank();
	}

}
