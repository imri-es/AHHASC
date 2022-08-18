package assignment;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;

public class Login implements ActionListener {

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == login_btn) {
            int loginLine = DataIO.login_search(tlogin.getText(), "usersdata.txt", 2);
            // 1stly checks if login in f text file. to do that it needs to check every single line and find it.
            if (loginLine >= 0) {
                if (DataIO.readattrbt(3, loginLine, "usersdata.txt").equals(tpasw.getText())) {
                    Assignment.l.getForm().setVisible(false);
                    switch (DataIO.readattrbt(1, loginLine, "usersdata.txt")) {
                        case "manager":
                            Assignment.m.getManagermenu().setVisible(true); break;
                        case "technician":
                            Assignment.t.getTechnicianmenu().setVisible(true); break;
                    }
                    tlogin.setText("");
                    tpasw.setText("");
                } else {
                    JOptionPane.showMessageDialog(login_btn, "Login or password is incorrect, please try again!");
                }
            } else {
                JOptionPane.showMessageDialog(login_btn, "Login or password is incorrect, please try again!");
            }
        }

    }

    private JFrame form;
    private JLabel title, login, password;
    private JTextField tlogin, tpasw;
    private JButton login_btn;

    public Login() {
        form = new JFrame();
        form.setTitle("Login");
        form.setBounds(700, 300, 700, 500);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setLayout(null);
        form.setResizable(false);


        title = new JLabel("Welcome to AHHASC!");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setSize(500, 70);
        title.setLocation(120, 15);
        form.add(title);


        login_btn = new JButton(" Login");
        login_btn.setFont(new Font("Arial", Font.PLAIN, 24));
        login_btn.setLocation(270, 300);
        login_btn.setSize(150, 50);
        form.add(login_btn);

        login_btn.addActionListener(this);

        login = new JLabel("Username");
        login.setFont(new Font("Arial", Font.BOLD, 24));
        login.setLocation(230, 100);
        login.setSize(150, 35);
        form.add(login);

        tlogin = new JTextField();
        tlogin.setFont(new Font("Arial", Font.PLAIN, 24));
        tlogin.setLocation(230, 140);
        tlogin.setSize(250, 35);
        form.add(tlogin);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.BOLD, 24));
        password.setLocation(230, 180);
        password.setSize(150, 35);
        form.add(password);

        tpasw = new JTextField();
        tpasw.setFont(new Font("Arial", Font.PLAIN, 24));
        tpasw.setLocation(230, 220);
        tpasw.setSize(250, 35);
        form.add(tpasw);

        form.setVisible(true);

    }

    public JFrame getForm() {
        return form;
    }
}


