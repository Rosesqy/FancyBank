package Bank.BankManagerPlatform;

import Bank.Utilities.DbConnectionHelper;
import Bank.Utilities.PasswordEncryptionService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BankManagerPlatform extends JFrame implements ActionListener {
    private final JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private DbConnectionHelper dbcon;
    private Connection connection;

    public BankManagerPlatform(){
        dbcon = new DbConnectionHelper();
        connection = dbcon.getConnection();
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(196,196,196));
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(Color.WHITE));
        panel_1.setBackground(new Color(130,130,130));
        panel_1.setBounds(365,277 ,829,431);
        panel_1.setLayout(null);
        contentPane.add(panel_1);

        // username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(63,90,109,28);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBackground(new Color(245,245,245));
        usernameField.setBounds(213,83,552,43);
        usernameField.setHorizontalAlignment(SwingConstants.LEFT);
        usernameField.setFont(new Font("Roboto", Font.PLAIN,20));
        usernameField.setColumns(10);
        panel_1.add(usernameField);

        // password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(63,200,106,28);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel_1.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(245,245,245));
        passwordField.setBounds(213,192,552,43);
        passwordField.setHorizontalAlignment(SwingConstants.LEFT);
        passwordField.setFont(new Font("Roboto", Font.PLAIN,20));
        passwordField.setColumns(10);
        panel_1.add(passwordField);

        // Buttons
        loginButton = new JButton("Login");
        loginButton.setBorder(new LineBorder(new Color(245,245,245)));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Roboto", Font.PLAIN,20));
        loginButton.setBounds(512,310, 256,59);
        loginButton.addActionListener(this);
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1.add(loginButton);

        registerButton = new JButton("Register New Account");
        registerButton.setBorder(new LineBorder(new Color(245,245,245)));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Roboto", Font.PLAIN,20));
        registerButton.setBounds(223,310,237,59);
        registerButton.addActionListener(this);
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1.add(registerButton);

        JLabel logo = new JLabel("");
        logo.setHorizontalAlignment(SwingConstants.LEFT);
        //TODO Fix this Image Icon Resource Issue
        logo.setIcon(new ImageIcon("BankManager.png", "Bank Manager"));
        logo.setBounds(108,81,142,142);
        contentPane.add(logo);


        JLabel bmpLabel = new JLabel("Bank Manager Platform");
        bmpLabel.setForeground(Color.WHITE);
        bmpLabel.setFont(new Font("Roboto", Font.PLAIN,72));
        bmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bmpLabel.setBounds(307,134,945,100);
        contentPane.add(bmpLabel);


        JLabel closeLabel = new JLabel("Exit");
        closeLabel.setBorder(new LineBorder(new Color(245,245,245)));
        closeLabel.setForeground(Color.WHITE);
        closeLabel.setBackground(new Color(130,130,130));
        closeLabel.setOpaque(true);
        closeLabel.setFont(new Font("Roboto", Font.PLAIN,20));
        closeLabel.setBounds(365,767,829,52);
        contentPane.add(closeLabel);
        closeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.exit(0);
            }
        });
        closeLabel.setHorizontalAlignment(SwingConstants.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== loginButton){
            if (authenticateUser(usernameField.getText(),passwordField.getText())){
                JOptionPane.showMessageDialog(this, "Login successful");
                new ManagerDashboard().setVisible(true);
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(this, "LoginFailed");
            }
        }
        else if (e.getSource()== registerButton){
            try{
                new ManagerRegistration().setVisible(true);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private boolean authenticateUser(String username, String password){
        String query = "select * from manager where username=?";
        try{
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                byte[] salt = rs.getBytes("salt");
                byte[] encryptedPassword = rs.getBytes("password");
                PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
                return passwordEncryptionService.authenticate(password,encryptedPassword,salt);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}