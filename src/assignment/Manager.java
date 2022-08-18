package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager  implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Assignment.m.getManagermenu().setVisible(false);

        if(actionEvent.getSource() == y1)
            Assignment.UA.getUa().setVisible(true);
        else if (actionEvent.getSource() == y2)
            Assignment.c.getc().setVisible(true);
        else if (actionEvent.getSource() == y3)
            Assignment.a.getA().setVisible(true);
        else if (actionEvent.getSource() == y4)
            Assignment.An.getAn().setVisible(true);
        else if(actionEvent.getSource() == y6) {
            Assignment.m.getManagermenu().setVisible(false);
            Assignment.l.getForm().setVisible(true);
        }

    }
    private JFrame managermenu;
    private Button y1, y2, y3, y4, y5, y6;
    public Manager(){
        managermenu = new JFrame("Manager");
        managermenu.setSize(700,500);
        managermenu.setLocation(700,300);
        managermenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managermenu.setLayout(null);

        y1 = new Button("User accounts");
        y2 = new Button("Customers");
        y3 = new Button("Appointments");
        y4 = new Button("Payments");
        y6 = new Button("Log Out");

        y1.setBounds(50, 50, 250, 80);
        y2.setBounds(50, 150, 250, 80);
        y3.setBounds(370, 150, 250, 80);
        y4.setBounds(370, 50, 250, 80);
        y6.setBounds(200, 250, 250, 80);

        y1.setFont(new Font("Arial", Font.BOLD, 24));
        y2.setFont(new Font("Arial", Font.BOLD, 24));
        y3.setFont(new Font("Arial", Font.BOLD, 24));
        y4.setFont(new Font("Arial", Font.BOLD, 24));
        y6.setFont(new Font("Arial", Font.BOLD, 24));


        y1.addActionListener(this);
        y2.addActionListener(this);
        y3.addActionListener(this);
        y4.addActionListener(this);
        y6.addActionListener(this);

        managermenu.add(y1);
        managermenu.add(y2);
        managermenu.add(y3);
        managermenu.add(y4);
        managermenu.add(y6);

    }
    public JFrame getManagermenu() {
        return managermenu;
    }
}


