package fancybank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BuyStock implements ActionListener {
	
	String id;
	JFrame frame;
	JLabel stockList;
	JComboBox stocks;
	JButton button;
	
	public BuyStock(String id) {
		this.id = id;
		frame = new JFrame("Sell Stocks");
		frame.setSize(500, 400);
		String[] attributes = {"ID", "Name", "Price"};
		//Get all the stocks in the database stock table
		//First get how many stocks there are
		int n = 10;//Change to number of stocks
		String[][] allStocks = new String[n][3];
		String[] ids = new String[n];
		for(int i = 0; i < n; i++) {
			String stockID = "";//Get from the database
			String name = "";//Get from the database
			double price = 0;//Get from the database
			String[] stockInfo = {stockID, name, Double.toString(price)};
			ids[i] = stockID;
			allStocks[i] = stockInfo;
		}
		JTable stocksInfo = new JTable(allStocks, attributes);
		stocksInfo.getColumnModel().getColumn(0).setPreferredWidth(60);
		stocksInfo.getColumnModel().getColumn(1).setPreferredWidth(60);
		stocksInfo.getColumnModel().getColumn(2).setPreferredWidth(60);
		stocksInfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane sp=new JScrollPane(stocksInfo);
		stockList = new JLabel("Choose a stock");
		stockList.setBounds(250, 50, 200, 20);
		stocks = new JComboBox(ids);
		stocks.setBounds(250, 100, 200, 20);
		button = new JButton("Sell");
		button.setBounds(250, 200, 200, 50);
		button.addActionListener(this);
		frame.add(sp);
		frame.add(stockList);
		frame.add(stocks);
		frame.add(button);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String sid = stocks.getItemAt(stocks.getSelectedIndex()).toString();
		//use this id to get the price of the stock
		double price = 0;//Get from the database
		//Use account id to get account balance
		double balance = 0;//Get from the database
		//If the account has enough balance to buy the stock, then buy it
		if(balance >= price) {
			//Change the balance of the account, and add the stock to the account
		}
		else {
			JFrame f = new JFrame();
			JOptionPane.showMessageDialog(f, "You do not have enough balance!"); //Popup wind
		}
	}

}
