package assignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;

public class UserAccounts extends MouseAdapter implements ActionListener {
    JFrame f = new JFrame();
    public static void search(String[][] table, String[] columnNames, String datafile, int colN){
        JTextField searchInp = new JTextField();
        Object[] message = {
                "Enter a data to search:", searchInp
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        int tempnum = 0;
        for (int j = 0; j < DataIO.numberOfLines(datafile); j++) {
            for (int i = 1; i < colN; i++) {
                if(searchInp.getText().equals(DataIO.readattrbt(i, j, datafile))){
                    for (int k = 1; k < colN; k++)
                        table[tempnum][k-1] = DataIO.readattrbt(k, j, datafile);
                    tempnum++;
                    break;
                }
            }
        }
        if (option == JOptionPane.OK_OPTION) {
            DataIO.showTable(table, columnNames, 2, datafile);
        }
    }
    String[] columnNames = {"Role", "Login", "Password", "Name", "Number", "Gender",  "DOB", "Address"};
    String[][] table = new String[DataIO.numberOfLines("usersdata.txt")][8];
    TableModel tableModel = new DefaultTableModel(table, columnNames);
    JTable tableOut = new JTable(tableModel);
    public void mouseClicked(MouseEvent e){
        Point point = e.getPoint();
        int row = tableOut.rowAtPoint(point);
        int option = JOptionPane.showConfirmDialog(tableOut, "Delete selected row?(" + row + ")");
        if (option == JOptionPane.OK_OPTION)  {
            String usrLog = DataIO.readattrbt(2,row,"usersdata.txt");
            for (int i = 0; i < DataIO.numberOfLines("appointments.txt"); i++) {
                if(DataIO.readattrbt(3, i, "appointments.txt").equals(usrLog)){
                    DataIO.deleterow(i, "appointments.txt");
                    i--;
                }
            }
            DataIO.deleterow(row, "usersdata.txt");
            Assignment.UA = new UserAccounts();
            Assignment.UA.getUa().setVisible(true);
            f.dispose();
        }

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource() == y5) {
            Assignment.UA.getUa().setVisible(false);
            Assignment.m.getManagermenu().setVisible(true);
        }
        else if(actionEvent.getSource() == y6) {
            Assignment.UA.getUa().setVisible(false);
            Assignment.l.getForm().setVisible(true);
        }
        else if(actionEvent.getSource() == y4){
            String[][] table = new String[DataIO.numberOfLines("usersdata.txt")][8];
            TableModel tableModel = new DefaultTableModel(table, columnNames);
            JTable tableOut = new JTable(tableModel);
            search(table, columnNames,"usersdata.txt", 9);

        }
        else if(actionEvent.getSource() == y1){
            Assignment.UA.getUa().setVisible(false);
            Assignment.reg =  new Registration();
            Assignment.reg.getFormMgr().setVisible(true);
        }
        else if(actionEvent.getSource() == y3){
            String[][] table = new String[DataIO.numberOfLines("usersdata.txt")][8];
            DataIO.showTable(table, columnNames, 1, "usersdata.txt");
        }
        else if(actionEvent.getSource() == y2){
            Assignment.UA.getUa().setVisible(false);
            for (int i = 1; i < 9; i++) {
                for (int j = 0; j < DataIO.numberOfLines("usersdata.txt"); j++) {
                    table[j][i-1] = DataIO.readattrbt(i, j, "usersdata.txt");
                }
            }
            JButton back = new JButton("Go back");
            tableModel = new DefaultTableModel(table, columnNames);
            tableOut = new JTable(tableModel);
            tableOut.setBounds(30, 40, 400, 500);
            tableOut.addMouseListener(this);
            JScrollPane sp = new JScrollPane(tableOut);

            f.add(sp);
            f.add(back);
            f.setLayout(new FlowLayout());
            f.setLocation(700, 300);
            f.setSize(700, 500);
            f.setVisible(true);
            back.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Assignment.UA.getUa().setVisible(true);
                    f.dispose();
                }
            });


        }

    }

    private JFrame Ua;
    private Button y1, y2, y3, y4, y5, y6;

    public UserAccounts(){
        Ua = new JFrame();
        Ua.setTitle("User Accounts");
        Ua.setSize(700,500);
        Ua.setLocation(700,300);
        Ua.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Ua.setLayout(null);

        y1 = new Button("Add");
        y2 = new Button("Delete");
        y3 = new Button("View");
        y4 = new Button("Search");
        y5 = new Button("Go back");
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

        Ua.add(y1);
        Ua.add(y2);
        Ua.add(y3);
        Ua.add(y4);
        Ua.add(y5);
        Ua.add(y6);

        Ua.setVisible(false);
    }
    public JFrame  getUa() { return Ua; }


}
