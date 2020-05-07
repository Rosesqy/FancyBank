package Bank.BankManagerPlatform;



import Bank.DAO.Person.BankManager;
import Bank.Utilities.DbConnectionHelper;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManagerRegistration extends JFrame implements ActionListener {
    private final JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DbConnectionHelper dbcon;
    private Connection connection;
    private Statement st;
    private ResultSet set;

    public ManagerRegistration(){
        dbcon = new DbConnectionHelper();
        connection = dbcon.getConnection();

        setBounds(900,400 ,812,400);
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(130,130,130));
        contentPane.setLayout(null);


        JLabel bmrpLabel = new JLabel("Register New Bank Manager");
        bmrpLabel.setBounds(100,38,700,42);
        bmrpLabel.setForeground(Color.WHITE);
        bmrpLabel.setFont(new Font("Roboto", Font.PLAIN,36));
        bmrpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(bmrpLabel);

        // username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(51,117,109,28);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Roboto", Font.PLAIN,20));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBackground(new Color(245,245,245));
        usernameField.setBounds(210,100,552,43);
        usernameField.setFont(new Font("Roboto", Font.PLAIN,20));
        contentPane.add(usernameField);
        usernameField.setHorizontalAlignment(SwingConstants.LEFT);
        usernameField.setColumns(10);

        // password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50,224,106,28);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Roboto", Font.PLAIN,20));
        contentPane.add(passwordLabel);
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(245,245,245));
        passwordField.setBounds(209,209,552,43);
        contentPane.add(passwordField);
        usernameField.setHorizontalAlignment(SwingConstants.LEFT);
        usernameField.setColumns(10);

        JButton registerButton = new JButton("Register Bank Manager");
        registerButton.setBorder(new LineBorder(new Color(245,245,245)));
        registerButton.setFont(new Font("Roboto", Font.PLAIN,20));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBounds(278,292, 256,59);
        contentPane.add(registerButton);
        registerButton.addActionListener(this);
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        BankManager newBankManager = new BankManager(usernameField.getText(),passwordField.getText());
        long insertkey = insertBankManager(newBankManager);
        System.out.println(insertkey);
        if (insertkey>0){
            JOptionPane.showMessageDialog(this, "Registration successful");
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(this, "Registration failed");
        }
    }

    private long insertBankManager(BankManager newBankManager){
        String query = "insert into manager (username, salt, password) VALUES (?, ?, ?)";
        long id = 0;
        try{
            PreparedStatement pstmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, newBankManager.getUserid());
            pstmt.setBytes(2, newBankManager.getSalt());
            pstmt.setBytes(3, newBankManager.getPassword());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return id;
    }
}