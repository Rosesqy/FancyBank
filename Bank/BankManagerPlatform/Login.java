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

public class Login extends JDialog implements ActionListener {
    // TODO fix event Dispatching
    private final JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private DbConnectionHelper dbcon;
    private Connection connection;

    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            // TODO: handle excception
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    Login frame = new Login();
                    frame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Login(){
        dbcon = new DbConnectionHelper();
        connection = dbcon.getConnection();
        setUndecorated(true);
        setBounds(500,500,700,300);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new CardLayout(0,0));
        setModalityType(ModalityType.APPLICATION_MODAL);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(105,105,105));
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(Color.WHITE));
        panel_1.setOpaque(false);
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(320,40 ,350,200);
        panel.add(panel_1);
        panel_1.setLayout(null);

        // username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(10,40,100,25);
        usernameLabel.setForeground(Color.WHITE);
        panel_1.add(usernameLabel);
        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        usernameField = new JTextField();
        usernameField.setBackground(new Color(245,245,245));
        usernameField.setBounds(100,40,200,25);
        panel_1.add(usernameField);
        usernameField.setHorizontalAlignment(SwingConstants.LEFT);
        usernameField.setForeground(new Color(112,128,144));
        usernameField.setColumns(10);

        // password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,90,100,25);
        passwordLabel.setForeground(Color.WHITE);
        panel_1.add(passwordLabel);
        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(245,245,245));
        passwordField.setBounds(100,90,200,25);
        panel_1.add(passwordField);
        usernameField.setHorizontalAlignment(SwingConstants.LEFT);
        usernameField.setForeground(new Color(112,128,144));
        usernameField.setColumns(10);

        // Buttons
        loginButton = new JButton("Login");
        loginButton.setBorder(new LineBorder(new Color(245,245,245)));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(30,140, 100,25);
        panel_1.add(loginButton);
        loginButton.addActionListener(this);
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);

        registerButton = new JButton("Register New Account");
        registerButton.setBorder(new LineBorder(new Color(245,245,245)));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBounds(150,140,150,25);
        panel_1.add(registerButton);
        registerButton.addActionListener(this);
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel logo = new JLabel("");
        logo.setHorizontalAlignment(SwingConstants.LEFT);
        //TODO Fix this Image Icon Resource Issue
        logo.setIcon(new ImageIcon("BankManager.png", "Bank Manager"));
        logo.setBounds(0,0,300,300);
        panel.add(logo);

        JLabel bmpLabel = new JLabel("Bank Manager Platform");
        bmpLabel.setForeground(Color.WHITE);
        bmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bmpLabel.setBounds(395,10,200,30);
        panel.add(bmpLabel);


        JLabel closeLabel = new JLabel("Exit");
        closeLabel.setBorder(new LineBorder(new Color(245,245,245)));
        closeLabel.setForeground(Color.WHITE);
        closeLabel.setBounds(320,250,350,25);
        panel.add(closeLabel);
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
                JOptionPane.showMessageDialog(this, "Login succesful");
            }
            else{
                JOptionPane.showMessageDialog(this, "LoginFailed");
            }
        }
        else if (e.getSource()== registerButton){
            try{
                ManagerRegistration reg = new ManagerRegistration();
                reg.PopUp();
//                wait();
//                dispatchEvent()
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