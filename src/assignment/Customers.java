package assignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Customers extends MouseAdapter implements ActionListener {
    String[] columnNames = {"Customer ID", "Name", "Number", "Gender",  "DOB", "Address"};
    String[][] table = new String[DataIO.numberOfLines("customers.txt")][6];
    TableModel tableModel = new DefaultTableModel(table, columnNames);
    JTable tableOut = new JTable(tableModel);
    JFrame f = new JFrame();
    public void mouseClicked(MouseEvent e){
        Point point = e.getPoint();
        int row = tableOut.rowAtPoint(point);
        int option = JOptionPane.showConfirmDialog(tableOut, "Delete selected row?(" + row + ")");
        if (option == JOptionPane.OK_OPTION)  {
            String cusLog = DataIO.readattrbt(1,row,"customers.txt");
            for (int i = 0; i < DataIO.numberOfLines("appointments.txt"); i++) {
                if(DataIO.readattrbt(2, i, "appointments.txt").equals(cusLog)){
                    DataIO.deleterow(i, "appointments.txt");
                    i--;
                }
            }
            DataIO.deleterow(row, "customers.txt");
            Assignment.c = new Customers();
            Assignment.c.getc().setVisible(true);
            f.dispose();
        }

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource() == y4) {
            String[][] table = new String[DataIO.numberOfLines("customers.txt")][6];
            TableModel tableModel = new DefaultTableModel(table, columnNames);
            JTable tableOut = new JTable(tableModel);
            UserAccounts.search(table, columnNames,"customers.txt", 7);
        }
        else if(actionEvent.getSource() == y5) {
            Assignment.c.getc().setVisible(false);
            Assignment.m.getManagermenu().setVisible(true);
        }
        else if(actionEvent.getSource() == y1){
            Assignment.c.getc().setVisible(false);
            Assignment.CR = new CustomerReg();
            Assignment.CR.getFormCmr().setVisible(true);
        }
        else if(actionEvent.getSource() == y3){
            table = new String[DataIO.numberOfLines("customers.txt")][8];
            DataIO.showTable(table, columnNames, 1, "customers.txt");
        }
        else if(actionEvent.getSource() == y6){
            Assignment.c.getc().setVisible(false);
            Assignment.l.getForm().setVisible(true);
        }
        else if(actionEvent.getSource() == y2){
            Assignment.c.getc().setVisible(false);
            for (int i = 1; i < 7; i++) {
                for (int j = 0; j < DataIO.numberOfLines("customers.txt"); j++) {
                    table[j][i-1] = DataIO.readattrbt(i, j, "customers.txt");
                }
            }

            JButton back = new JButton("Back");
            tableModel = new DefaultTableModel(table, columnNames);
            tableOut = new JTable(tableModel);
            tableOut.setBounds(700, 300, 700, 500);
            tableOut.addMouseListener(this);
            JScrollPane sp = new JScrollPane(tableOut);

            f.add(sp);
            f.add(back);
            f.setLocation(700, 300);
            f.setSize(700, 500);
            f.setVisible(true);
            f.setLayout(new FlowLayout());
            back.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Assignment.c.getc().setVisible(true);
                    f.dispose();
                }

            });

        }

    }
    private JFrame c;
    private Button y1, y2, y3, y4, y5, y6;
    public Customers(){
        c = new JFrame("Customers");
        c.setSize(700,500);
        c.setLocation(700,300);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLayout(null);

        y1 = new Button("Add");
        y2 = new Button("Delete");
        y3 = new Button("View/Edit");
        y4 = new Button("Search");
        y5 = new Button("Go Back");
        y6 = new Button("Log Out");


        y1.setBounds(50, 50, 250, 80);
        y2.setBounds(50, 150, 250, 80);
        y3.setBounds(50, 250, 250, 80);
        y4.setBounds(370, 50, 250, 80);
        y5.setBounds(370, 150, 250, 80);
        y6.setBounds(370, 250, 250, 80);

        y1.setFont(new Font("Arial", Font.BOLD, 24));
        y2.setFont(new Font("Arial", Font.BOLD, 24));
        y3.setFont(new Font("Arial", Font.BOLD, 24));
        y4.setFont(new Font("Arial", Font.BOLD, 24));
        y5.setFont(new Font("Arial", Font.BOLD, 24));
        y6.setFont(new Font("Arial", Font.BOLD, 24));

        y1.addActionListener(this);
        y2.addActionListener(this);
        y3.addActionListener(this);
        y4.addActionListener(this);
        y5.addActionListener(this);
        y6.addActionListener(this);

        c.add(y1);
        c.add(y2);
        c.add(y3);
        c.add(y4);
        c.add(y5);
        c.add(y6);

    }
    public JFrame  getc() { return c; }
}
