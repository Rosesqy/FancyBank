package Bank.BankManagerPlatform;

import Bank.DAO.BankManagerAccount;
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

        setBounds(320,50 ,350,200);
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(105,105,105));
        contentPane.setLayout(null);


        JLabel bmrpLabel = new JLabel("Bank Manager Registration Platform");
        bmrpLabel.setBounds(0,0,400,25);
        bmrpLabel.setForeground(Color.WHITE);
        contentPane.add(bmrpLabel);
        bmrpLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(10,40,100,25);
        usernameLabel.setForeground(Color.WHITE);
        contentPane.add(usernameLabel);
        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        usernameField = new JTextField();
        usernameField.setBackground(new Color(245,245,245));
        usernameField.setBounds(100,40,200,25);
        contentPane.add(usernameField);
        usernameField.setHorizontalAlignment(SwingConstants.LEFT);
        usernameField.setForeground(new Color(112,128,144));
        usernameField.setColumns(10);

        // password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,90,100,25);
        passwordLabel.setForeground(Color.WHITE);
        contentPane.add(passwordLabel);
        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(245,245,245));
        passwordField.setBounds(100,90,200,25);
        contentPane.add(passwordField);
        usernameField.setHorizontalAlignment(SwingConstants.LEFT);
        usernameField.setForeground(new Color(112,128,144));
        usernameField.setColumns(10);

        JButton registerButton = new JButton("Register Bank Manager");
        registerButton.setBorder(new LineBorder(new Color(245,245,245)));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBounds(40,140, 250,25);
        contentPane.add(registerButton);
        registerButton.addActionListener(this);
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        BankManagerAccount newBankManager = new BankManagerAccount(usernameField.getText(),passwordField.getText());
        long insertkey = insertBankManager(newBankManager);
        System.out.println(insertkey);
        if (insertkey>0){
            JOptionPane.showMessageDialog(this, "Registration successful");
        }
        else{
            JOptionPane.showMessageDialog(this, "Registration failed");
        }
    }

    private long insertBankManager(BankManagerAccount newBankManager){
        String query = "insert into manager (username, salt, password) VALUES (?, ?, ?)";
        long id = 0;
        try{
            PreparedStatement pstmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1,newBankManager.getUserid());
            pstmt.setBytes(2,newBankManager.getSalt());
            pstmt.setBytes(3,newBankManager.getPassword());

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

    public static void PopUp() {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            // TODO: handle excception
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    ManagerRegistration frame = new ManagerRegistration();
                    frame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            // TODO: handle excception
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    ManagerRegistration frame = new ManagerRegistration();
                    frame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}