package Bank.CustomerPlatform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DealStock implements ActionListener {
	
	String username;
	JFrame frame;
	JLabel accList;
	JComboBox accField;
	JButton sell;
	JButton buy;
	
	public DealStock(String username) {
		this.username = username;
		frame = new JFrame("Deal with stocks");
		frame.setSize(500, 400);
		//Search the database using this username to retrieve all security accounts id that this customer has
		//Use these ids to form a String array to create the combox
		//First get how many security accounts this customer has
		int n = 10;//Change it to the number of security accounts
		String[] ids = new String[n];
		for(int i = 0; i < n; i++) {
			ids[i] = "";//Get from the database
		}
		accList = new JLabel("Choose a security account");
		accList.setBounds(25, 100, 200, 20);
		accField = new JComboBox(ids);
		accField.setBounds(25, 150, 200, 20);
		sell = new JButton("Sell Stocks");
		sell.setBounds(275, 100, 200, 50);
		sell.addActionListener(this);
		buy = new JButton("Buy Stocks");
		buy.setBounds(275, 250, 200, 50);
		buy.addActionListener(this);
		frame.add(accList);
		frame.add(accField);
		frame.add(sell);
		frame.add(buy);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String id = accField.getItemAt(accField.getSelectedIndex()).toString();
		if(e.getSource() == sell) {
			new SellStock(id);
		}
		else if(e.getSource() == buy) {
			new BuyStock(id);
		}
		
	}
}
