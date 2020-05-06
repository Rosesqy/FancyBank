package Bank.CustomerPlatform;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShowAccountInfo {
	String username;
	JFrame frame1;
	JFrame frame2;
	
	public ShowAccountInfo(String username) {
		frame1 = new JFrame("Checking and Savings Accounts Information");
		frame2 = new JFrame("Security Accounts Information");
		this.username = username;
		String[] csAttributes = {"ID", "Currency", "Balance", "Opendate", "Loan"}; //This is to show information of checking and saving accounts
		String[] seAttributes = {"ID", "Balance", "Realized Profit", "Unrealized profit"};
		//Search the database and use the username to retrieve all accounts information
		//For checking and savings accounts, retrieve account ID, currency type, balance, the open date(Can use toString Method to return a string form)
		//The loan(only show how much money he owns to the bank)
		//For the security accounts, get account id, balance and realized and unrealized profit
		
		//First get how many savings and checking accounts this customer has
		int m = 10;//Number of checking and savings accounts this customer has
		int n = 10;//Number of security accounts this customer has
		String[][] csInfo = new String[m][5];
		String[][] seInfo = new String[n][4];
		for(int i = 0; i < m; i++) {
			String id = "";//Get from the database
			String currency = "";//Get from the database, only type, use symbol like $
			double balance = 0;//Get from the database
			int year = 0;//Get from the database
			int month = 0;//Get from the database
			int day = 0;//Get from the database
			String opendate = year + "-" + month + "-" + day;
			double loan = 0;//Get from the database
			String[] CS = {id, currency, Double.toString(balance), opendate, Double.toString(loan)};
			csInfo[i] = CS;
		}
		for(int i = 0; i < n; i++) {
			String id = "";//Get from the database
			double balance = 0;//Get from the database
			double realize = 0;//Get from the database
			double unrealize = 0;//Get from the database
			String[] Se = {id, Double.toString(balance), Double.toString(realize), Double.toString(unrealize)};
			seInfo[i] = Se;
		}
		JTable CSAccounts = new JTable(csInfo, csAttributes);
		JTable SeAccounts = new JTable(seInfo, seAttributes);
		JScrollPane csPane = new JScrollPane(CSAccounts);
		JScrollPane sePane = new JScrollPane(SeAccounts);
		frame2.add(sePane);
		frame1.setSize(500, 400);
		frame2.setSize(500, 400);
		frame1.setVisible(true);
		frame2.setVisible(true);
		
	}
}
