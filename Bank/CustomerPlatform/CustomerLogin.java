package Bank.CustomerPlatform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CustomerLogin implements ActionListener {
	JFrame frame;
	JLabel username;
	JLabel password;
	JTextField usernameField;
	JPasswordField passwordField;
	JButton login;
	
	public CustomerLogin() {
		frame = new JFrame("Customer Login");
		frame.setSize(500, 400);
		username = new JLabel("Username");
		password = new JLabel("Password");
		username.setBounds(50, 50, 200, 20);
		password.setBounds(50, 150, 200, 20);
		usernameField = new JTextField();
		usernameField.setBounds(250, 50, 200, 20);
		passwordField = new JPasswordField();
		passwordField.setBounds(250, 150, 200, 20);
		login = new JButton("Log in");
		login.setBounds(250, 250, 200, 50);
		login.addActionListener(this);
		frame.add(username);
		frame.add(password);
		frame.add(usernameField);
		frame.add(passwordField);
		frame.add(login);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new ChooseAction();
		
	}
}
