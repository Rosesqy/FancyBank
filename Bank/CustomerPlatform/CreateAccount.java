package Bank.CustomerPlatform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Bank.Utilities.DbHelperPSQL;

public class CreateAccount implements ActionListener {
	DbHelperPSQL dtbase;

	String username;
	String userID;
	JFrame frame;
	JLabel ChooseType;
	JComboBox accountType;
	JLabel ChooseCurrency;
	JComboBox CurrencyType;
	JButton button;
	JLabel openDate;
	JLabel year;
	JLabel month;
	JLabel day;
	JTextField yearField;
	JTextField monthField;
	JTextField dayField;
	
	public CreateAccount(String username) {
		this.username = username;
		//Then search the database to acquire the right userID
		
		frame = new JFrame("Create an Account");
		frame.setSize(500, 400);
		ChooseType = new JLabel("Choose account type");
		ChooseType.setBounds(25, 50, 200, 20);
		String[] type = {"Checking", "Savings", "Security"};
		accountType = new JComboBox(type);
		accountType.setBounds(25, 100, 200, 20);
		ChooseCurrency = new JLabel("Choose currency type");
		ChooseCurrency.setBounds(25, 150, 200, 20);
		String[] cur = {"1. $ USD", "2. € EUR", "3. ￥ YEN", "4. ￡ GBP", "5. ¥ RMB"};
		CurrencyType = new JComboBox(cur);
		CurrencyType.setBounds(25, 200, 200, 20);
		openDate = new JLabel("Confirm the open date");
		openDate.setBounds(250, 50, 200, 20);
		year = new JLabel("Year");
		year.setBounds(250, 100, 50, 20);
		yearField = new JTextField();
		yearField.setBounds(300, 100, 50, 20);
		month = new JLabel("Month");
		month.setBounds(250, 150, 50, 20);
		monthField = new JTextField();
		monthField.setBounds(300, 150, 50, 20);
		day = new JLabel("Day");
		day.setBounds(250, 200, 50, 20);
		dayField = new JTextField();
		dayField.setBounds(300, 200, 50, 20);
		button = new JButton("Create");
		button.setBounds(150, 300, 200, 50);
		button.addActionListener(this);
		frame.add(ChooseType);
		frame.add(accountType);
		frame.add(ChooseCurrency);
		frame.add(CurrencyType);
		frame.add(openDate);
		frame.add(year);
		frame.add(yearField);
		frame.add(month);
		frame.add(monthField);
		frame.add(day);
		frame.add(dayField);
		frame.add(button);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String accType = accountType.getItemAt(accountType.getSelectedIndex()).toString();
		String curType = CurrencyType.getItemAt(CurrencyType.getSelectedIndex()).toString();
		//Also use username to search the database for userid
		
		
		//If this customer chooses to create a security account, check if he meets the requirement
		if(accType.equals("Security")) {
			//Use the username to search the database for savings accounts this customer has
			//See if any one of his saving accounts has a deposit of more than $5000
			if() {
				//If he meets the requirement, create a security and store information in the database
			}
			else {
				JFrame f = new JFrame();
				JOptionPane.showMessageDialog(f, "Do not meet the requirement to create a security account!"); //Popup window
			}
		}
		else {
			switch(curType) {
			case "1. $ USD":
				//Create a new account using chosen account type and currency type. Store the information in the database
				break;
			case "2. € EUR":
				//Create a new account using chosen account type and currency type. Store the information in the database
				break;
			case "3. ￥ YEN":
				//Create a new account using chosen account type and currency type. Store the information in the database
				break;
			case "4. ￡ GBP":
				//Create a new account using chosen account type and currency type. Store the information in the database
				break;
			case "5. ¥ RMB":
				//Create a new account using chosen account type and currency type. Store the information in the database
				break;
			}
		}
	}

}
