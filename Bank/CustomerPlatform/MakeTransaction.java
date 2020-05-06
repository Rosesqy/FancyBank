package fancybank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MakeTransaction implements ActionListener {
	
	String username;
	JFrame frame;
	JLabel from;
	JLabel to;
	JLabel amount;
	JComboBox fromaccount;
	JTextField toaccount;
	JTextField amountField;
	JLabel Date;
	JLabel year;
	JLabel month;
	JLabel day;
	JTextField yearField;
	JTextField monthField;
	JTextField dayField;
	JButton transfer;
	
	public MakeTransaction(String username) {
		this.username = username;
		//Search the database using this username to retrieve all savings and checking accounts id that this customer has
		//Use these ids to form a String array to create the combox
		//First get how many savings and checking accounts this customer has
		int n = 10;//Change it to the number of checking and savings accounts
		String[] ids = new String[n];
		for(int i = 0; i < n; i++) {
			ids[i] = "";//Get from the database
		}
		
		//Use those ids to form a combobox
		frame = new JFrame("Create an Account");
		frame.setSize(500, 600);
		from = new JLabel("From");
		from.setBounds(50, 100, 100, 20);
		to = new JLabel("To");
		to.setBounds(200, 100, 100, 20);
		amount = new JLabel("Amount");
		amount.setBounds(350, 100, 100, 20);
		fromaccount = new JComboBox(ids);
		fromaccount.setBounds(50, 150, 100, 20);
		toaccount = new JTextField();
		toaccount.setBounds(200, 150, 100, 20);
		amountField = new JTextField();
		amountField.setBounds(350, 150, 100, 20);
		Date = new JLabel("Confirm the transaction date");
		Date.setBounds(150, 250, 200, 20);
		year = new JLabel("Year");
		year.setBounds(50, 300, 100, 20);
		month = new JLabel("Month");
		month.setBounds(200, 300, 100, 20);
		day = new JLabel("Day");
		day.setBounds(350, 300, 100, 20);
		yearField = new JTextField();
		yearField.setBounds(50, 350, 100, 20);
		monthField = new JTextField();
		monthField.setBounds(200, 350, 100, 20);
		dayField = new JTextField();
		dayField.setBounds(350, 350, 100, 20);
		transfer = new JButton("Transfer");
		transfer.setBounds(150, 450, 200, 50);
		frame.add(from);
		frame.add(to);
		frame.add(amount);
		frame.add(fromaccount);
		frame.add(toaccount);
		frame.add(amountField);
		frame.add(Date);
		frame.add(year);
		frame.add(month);
		frame.add(day);
		frame.add(yearField);
		frame.add(monthField);
		frame.add(dayField);
		frame.add(transfer);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String facc = fromaccount.getItemAt(fromaccount.getSelectedIndex()).toString();//Use this id to search account information, also get the type of this account
		String ftype = "Checking/Saving"; //Get from the database
		String tacc = toaccount.getText(); //Use this id to search account information and type
		double amount = Double.parseDouble(amountField.getText());//Get amount of money
		//If this account exist,take the next step
		if() {
			//Get account type of the account which is going to receive money(Security account should only be his own)
			String ttype = "Checking/Saving/Security"; //Get from the database
			//Get userid of both accounts
			String fuid = "";//Get from the database
			String tuid = "";//Get from the database
			//Get both currency type and exchange rate of the two accounts
			String fcurtype = "";//Get from the database
			String tcurtype = "";//Get from the database
			double fcurrate = 0;//Get from the database
			double tcurrate = 0;//Get from the database
			//Get balance of the from account
			double balance = 0;//Get from the database
			//If fromaccount is checking
			if(ftype.equals("Checking")) {
				if(ttype.equals("Security")) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "Cannot transfer from a checking account to a security account!"); //Popup window
				}
				else {
					if(balance >= amount) {
						//Change the balance of the two accounts in the database
						//Use amount*fcurrate/tcurrate to calculate amount in different currencies
					}
					else {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f, "You do not have enough balance!"); //Popup window
					}
				}
			}
			else if(ftype.equals("Savings")) {
				//If the customer is trying to transfer money to a security account, it should only be his own
				if(ttype.equals("Security")) {
					if(fuid.equals(tuid)) {
						if(balance*fcurrate >= 500 && amount*fcurrate >= 1000 && (balance-amount)*fcurrate >= 2500) {
							//Change the balance of the two accounts in the database
							//Use amount*fcurrate/tcurrate to calculate amount in different currencies
						}
						else {
							JFrame f = new JFrame();
							JOptionPane.showMessageDialog(f, "Do not meet the requirement to transfer money to a security account!"); //Popup wind
						}
					}
					else {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f, "Cannot transfer to another customer's security account!"); //Popup window
					}
				}
				else {
					if(balance >= amount) {
						//Change the balance of the two accounts in the database
						//Use amount*fcurrate/tcurrate to calculate amount in different currencies
					}
					else {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f, "You do not have enough balance!"); //Popup window
					}
				}
			}
		}
		else {
			JFrame f = new JFrame();
			JOptionPane.showMessageDialog(f, "This account doesn't exist!"); //Popup window
		}
	}

}
