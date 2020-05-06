package Bank.CustomerPlatform;

import Bank.DAO.*;
import Bank.Utilities.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class CustomerRegister implements ActionListener {
	JFrame frame;
	JLabel username;
	JLabel password;
	JLabel fname;
	JLabel mname;
	JLabel lname;
	JLabel phone;
	JLabel email;
	JTextField usernameField;
	JPasswordField passwordField;
	JTextField fnameField;
	JTextField mnameField;
	JTextField lnameField;
	JTextField phoneField;
	JTextField emailField;
	JButton register;

	DbHelperPSQL dtbase;
	
	public CustomerRegister() {
		dtbase = new DbHelperPSQL();

		frame = new JFrame("Customer Register");
		frame.setSize(500, 600);
		username = new JLabel("Username");
		password = new JLabel("Password");
		fname = new JLabel("First Name");
		mname = new JLabel("Middle Name");
		lname = new JLabel("Last Name");
		phone = new JLabel("Phone Number");
		email = new JLabel("Email Address");
		username.setBounds(50, 50, 200, 20);
		password.setBounds(50, 100, 200, 20);
		fname.setBounds(50, 150, 200, 20);
		mname.setBounds(50, 200, 200, 20);
		lname.setBounds(50, 250, 200, 20);
		phone.setBounds(50, 300, 200, 20);
		email.setBounds(50, 350, 200, 20);
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		fnameField = new JTextField();
		mnameField = new JTextField();
		lnameField = new JTextField();
		phoneField = new JTextField();
		emailField = new JTextField();
		usernameField.setBounds(250, 50, 200, 20);
		passwordField.setBounds(250, 100, 200, 20);
		fnameField.setBounds(250, 150, 200, 20);
		mnameField.setBounds(250, 200, 200, 20);
		lnameField.setBounds(250, 250, 200, 20);
		phoneField.setBounds(250, 300, 200, 20);
		emailField.setBounds(250, 350, 200, 20);
		register = new JButton("Register");
		register.setBounds(250, 450, 200, 50);
		register.addActionListener(this);
		frame.add(username);
		frame.add(password);
		frame.add(fname);
		frame.add(mname);
		frame.add(lname);
		frame.add(phone);
		frame.add(email);
		frame.add(usernameField);
		frame.add(passwordField);
		frame.add(fnameField);
		frame.add(mnameField);
		frame.add(lnameField);
		frame.add(phoneField);
		frame.add(emailField);
		frame.add(register);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username =  usernameField.getText();//Get input username
		//Search the database to see if there is a same username
		// if() {
		// 	//If the username has not been occupied, create the customer account with input information.
		// 	String firstname = fnameField.getText();
		// 	String middlename = mnameField.getText();
		// 	String lastname = lnameField.getText();
		// 	String password = String.valueOf(passwordField.getPassword());
		// 	String phone = phoneField.getText();
		// 	String email = emailField.getText();
		// 	Customer customer = new Customer(username, firstname, middlename, lastname, password, phone, email);
		// 	//Add the customer to the database
			
		// 	new ChooseAction(username);
		// }
		// else {
		// 	JFrame f = new JFrame();
		// 	JOptionPane.showMessageDialog(f, "This username has already been used!"); //Popup window
		// }
		
		String firstname = fnameField.getText();
		String middlename = mnameField.getText();
		String lastname = lnameField.getText();
		String password = String.valueOf(passwordField.getPassword());
		String phone = phoneField.getText();
		String email = emailField.getText();
		Customer customer = new Customer(username, firstname, middlename, lastname, password, phone, email);
		//Add the customer to the database
		
		
		if(dtbase.insertCustomer(username, firstname, middlename, lastname, phone, email, password)) {
			new ChooseAction(username);
		}
		else {
			JFrame f = new JFrame();
			JOptionPane.showMessageDialog(f, "This username has already been used or invalid!"); //Popup window
		}
		
	}
	
}
