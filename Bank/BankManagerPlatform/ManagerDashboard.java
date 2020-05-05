package Bank.BankManagerPlatform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerDashboard extends JFrame implements ActionListener {
    private final JPanel contentPane;
    public ManagerDashboard(){

        setBounds(400,400 ,350,200);
        setUndecorated(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(190,190,190));
        contentPane.setLayout(null);

        JLabel logo = new JLabel("");
        logo.setHorizontalAlignment(SwingConstants.LEFT);
        //TODO Fix this Image Icon Resource Issue
        logo.setIcon(new ImageIcon("BankManager.png", "Bank Manager"));
        logo.setBounds(108,81,142,142);
        contentPane.add(logo);


        JLabel bmpLabel = new JLabel("DashBoard");
        bmpLabel.setForeground(Color.WHITE);
        bmpLabel.setFont(new Font("Roboto", Font.PLAIN,72));
        bmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bmpLabel.setBounds(307,134,945,76);
        contentPane.add(bmpLabel);


        JPanel monitorPane = new JPanel();
        monitorPane.setBackground(new Color(130,130,130));
        monitorPane.setBounds(380,277,345,163);
        monitorPane.setLayout(null);
        contentPane.add(monitorPane);

        JLabel monitorLabel = new JLabel("Monitor");
        monitorLabel.setBounds(23,17,106,35);
        monitorLabel.setForeground(Color.WHITE);
        monitorLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        monitorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        monitorPane.add(monitorLabel);

        JPanel managePane = new JPanel();
        managePane.setBackground(new Color(130,130,130));
        managePane.setBounds(380,506,345,205);
        managePane.setLayout(null);
        contentPane.add(managePane);

        JLabel manageLabel = new JLabel("Manage");
        manageLabel.setBounds(23,17,106,35);
        manageLabel.setForeground(Color.WHITE);
        manageLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        manageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        managePane.add(manageLabel);


        JPanel monetaryPane = new JPanel();
        monetaryPane.setBackground(new Color(130,130,130));
        monetaryPane.setBounds(872,277,345,163);
        monetaryPane.setLayout(null);
        contentPane.add(monetaryPane);

        JLabel monetaryLabel = new JLabel("Monetary Assets");
        monetaryLabel.setBounds(23,17,200,35);
        monetaryLabel.setForeground(Color.WHITE);
        monetaryLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        monetaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        monetaryPane.add(monetaryLabel);

        JPanel stockPane = new JPanel();
        stockPane.setBackground(new Color(130,130,130));
        stockPane.setBounds(872,506,345,205);
        stockPane.setLayout(null);
        contentPane.add(stockPane);

        JLabel stockLabel = new JLabel("Stock");
        stockLabel.setBounds(23,17,106,35);
        stockLabel.setForeground(Color.WHITE);
        stockLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        stockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        stockPane.add(stockLabel);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
