import com.sun.source.tree.TryTree;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;


public class Login extends JDialog implements ActionListener {
    private final JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DbHelperPSQL dbcon;
    Connection connection;
    Statement st;
    ResultSet set;

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
        dbcon = new DbHelperPSQL();
        connection = dbcon.getCon();
        setUndecorated(true);
        setBounds(500,400,700,300);

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
        panel_1.setBounds(320,50 ,350,200);
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


        JButton loginButton = new JButton("Login");
        loginButton.setBorder(new LineBorder(new Color(245,245,245)));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(50,140, 100,25);
        panel_1.add(loginButton);
        loginButton.addActionListener(this);
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel closeLabel = new JLabel("Close");
        closeLabel.setBorder(new LineBorder(new Color(245,245,245)));
        closeLabel.setForeground(Color.WHITE);
        closeLabel.setBounds(200,140,100,25);
        panel_1.add(closeLabel);
        closeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.exit(0);
            }
        });
        closeLabel.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel bmpLabel = new JLabel("Bank Manager Platform");
        bmpLabel.setForeground(Color.WHITE);
        bmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bmpLabel.setBounds(395,20,200,30);
        panel.add(bmpLabel);

        JLabel logo = new JLabel("");
        logo.setHorizontalAlignment(SwingConstants.LEFT);
        logo.setIcon(new ImageIcon("BankManager.png"));
        logo.setBounds(0,0,290,299);
        panel.add(logo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String query = "select * from bank_managers where userid ='"+usernameField.getText()+"' and password = '"+passwordField.getText()+"'";
            st = connection.createStatement();
            set = st.executeQuery(query);
            if (set.next()){
                JOptionPane.showMessageDialog(this, "Login succesful");
            }
            else{
                JOptionPane.showMessageDialog(this, "LoginFailed");
            }
        }catch (Exception o){
        }
    }
}