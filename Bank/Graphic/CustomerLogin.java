package Bank.Graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Bank.DbHelperPSQL;

//import Bank.PasswordEncryptionService;

public class CustomerLogin implements ActionListener {
	JFrame frame;
	JLabel username;
	JLabel password;
	JTextField usernameField;
	JPasswordField passwordField;
	JButton login;
	DbHelperPSQL dbcon;
	Connection connection;
	
	public CustomerLogin(){
		dbcon = new DbHelperPSQL();
		connection = dbcon.getConnection();
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
		//if (authenticateUser(usernameField.getText(),String.valueOf(passwordField.getPassword()))) {}
			
		
		
	}
	
//	private boolean authenticateUser(String username, String password) {
//		String query = "select * from customer where userid=?";
//		try{
//            PreparedStatement pstmt = connection.prepareStatement(query);
//            pstmt.setString(1,username);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()){
//                byte[] salt = rs.getBytes("salt");
//                byte[] encryptedPassword = rs.getBytes("password");
//                PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
//                return passwordEncryptionService.authenticate(password,encryptedPassword,salt);
//            }
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        return false;
//    }
	
}
