package assignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointments extends MouseAdapter implements ActionListener {
    String[] columnNames = {"Appointment ID", "Customer ID", "Technician Name", "Name of appliance",  "Appliance type", "Payment method", "Price", "Date"};
    String[][] table = new String[DataIO.numberOfLines("appointments.txt")][8];
    TableModel tableModel = new DefaultTableModel(table, columnNames);
    JTable tableOut = new JTable(tableModel);
    JFrame f = new JFrame();
    public void mouseClicked(MouseEvent e){
        Point point = e.getPoint();
        int row = tableOut.rowAtPoint(point);
        int option = JOptionPane.showConfirmDialog(tableOut, "Delete selected row?(" + row + ")");
        if (option == JOptionPane.OK_OPTION)  {
            DataIO.deleterow(row, "appointments.txt");
            Assignment.a = new Appointments();
            Assignment.a.getA().setVisible(true);
            f.dispose();
        }
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == y5) {
            Assignment.a.getA().setVisible(false);
            Assignment.m.getManagermenu().setVisible(true);
        }
        else if(actionEvent.getSource() == y6) {
            Assignment.a.getA().setVisible(false);
            Assignment.l.getForm().setVisible(true);
        }
        else if(actionEvent.getSource() == y1){
            Assignment.AC = new AppointmentCreation();
            Assignment.AC.getACr().setVisible(true);
            Assignment.a.getA().setVisible(false);
        }

        else if(actionEvent.getSource() == y2){
            Assignment.a.getA().setVisible(false);
            for (int i = 1; i < 9; i++) {
                for (int j = 0; j < DataIO.numberOfLines("appointments.txt"); j++) {
                    table[j][i-1] = DataIO.readattrbt(i, j, "appointments.txt");
                }
            }

            JButton back  = new JButton("Back");
            tableModel = new DefaultTableModel(table, columnNames);
            tableOut = new JTable(tableModel);
            tableOut.setBounds(30, 40, 400, 500);
            tableOut.addMouseListener(this);
            JScrollPane sp = new JScrollPane(tableOut);

            f.add(sp);
            f.add(back);
            f.setLocation(700, 300);
            f.setSize(700, 500);
            f.setLayout(new FlowLayout());
            f.setVisible(true);
            back.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Assignment.a.getA().setVisible(true);
                    f.dispose();
                }
            });
        }
        else if(actionEvent.getSource() == y3) {
            Assignment.a.getA().setVisible(false);
            Assignment.a.getB().setVisible(true);

        }
        else if(actionEvent.getSource() == y4){
            String[][] table = new String[DataIO.numberOfLines("appointments.txt")][8];
            TableModel tableModel = new DefaultTableModel(table, columnNames);
            JTable tableOut = new JTable(tableModel);
            UserAccounts.search(table, columnNames,"appointments.txt", 9);
        }
        if (actionEvent.getSource() == y8) {

            JPanel jPanel = new JPanel();
            JScrollPane scrollPane = new JScrollPane(jPanel);
            final JFrame f = new JFrame("View by technicians");
            JButton back = new JButton("Go Back");
            back.setLocation(10, 10);
            jPanel.add(back);
            jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
            f.setLocation(700, 300);
            f.setSize(700, 500);

            for (int i = 0; i < DataIO.numberOfLines("usersdata.txt"); i++) {
                String login = DataIO.readattrbt(1,i,"usersdata.txt");
                if (login.equals("technician")){
                    login = DataIO.readattrbt(2,i,"usersdata.txt");
                    String techname = DataIO.readattrbt(4,i,"usersdata.txt");
                    String[][] table = new String[DataIO.numberOfdata("appointments.txt", 3, login)][8];
                    for (int k = 1; k < 9; k++) {
                        int linenum = 0;
                        for (int j = 0; j < DataIO.numberOfLines("appointments.txt"); j++) {
                            if (DataIO.readattrbt(3, j, "appointments.txt").equals(login)) {
                                table[linenum][k - 1] = DataIO.readattrbt(k, j, "appointments.txt");
                                linenum++;
                            }

                        }
                    }

                    JLabel Jtechname = new JLabel(techname);
                    TableModel tableModel = new DefaultTableModel(table, columnNames);
                    tableOut = new JTable(tableModel);
                    JScrollPane sp = new JScrollPane(tableOut);
                    sp.setPreferredSize(new Dimension(300, 200));
                    jPanel.add(Jtechname);
                    jPanel.add(sp);

                }
            }
            f.getContentPane().add(scrollPane);
            f.setVisible(true);
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    f.dispose();
                }
            });
        }
        else if( actionEvent.getSource() == y7) {
            int linenum = 0;
            Date date  = new Date();
            SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
            String stringtodayDate= DateFor.format(date);
            for (int j = 0; j < DataIO.numberOfLines("appointments.txt"); j++) {
                long diff = DataIO.DifferenceDate(stringtodayDate, DataIO.readattrbt(8, j, "appointments.txt"));
                if (diff >= 0 && diff < 169) {
                    for (int k = 1; k < 9; k++) {
                        table[linenum][k - 1] = DataIO.readattrbt(k, j, "appointments.txt");
                    }
                    linenum++;
                }
            }

            final JFrame f = new JFrame("Following week");
            JButton back = new JButton("Go Back");
            tableModel = new DefaultTableModel(table, columnNames);
            tableOut = new JTable(tableModel);
            tableOut.setBounds(30, 40, 400, 500);
            tableOut.addMouseListener(this);
            JScrollPane sp = new JScrollPane(tableOut);

            f.add(sp);
            f.add(back);
            f.setLocation(700, 300);
            f.setSize(700, 500);
            f.setLayout(new FlowLayout());
            f.setVisible(true);
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    f.dispose();
                }
            });

        }
        else if(actionEvent.getSource() == y9){
            Assignment.a.getA().setVisible(true);
            Assignment.a.getB().setVisible(false);
        }

    }
    private JFrame A, B;
    private Button y1, y2, y3, y4, y5, y6, y7, y8, y9;
    public Appointments(){
        A = new JFrame("Appointments");
        A.setSize(700,500);
        A.setLocation(700,300);
        A.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        A.setLayout(null);

        B = new JFrame("View");
        B.setSize(700,500);
        B.setLocation(700,300);
        B.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        B.setLayout(null);

        y1 = new Button("Add");
        y2 = new Button("Delete");
        y3 = new Button("View");
        y4 = new Button("Search");
        y5 = new Button("Go back");
        y6 = new Button("Log Out");
        y7 = new Button("Following week");
        y8 = new Button("View by technicians");
        y9 = new Button("Back");

        y1.setBounds(50, 50, 250, 80);
        y2.setBounds(50, 150, 250, 80);
        y3.setBounds(50, 250, 250, 80);
        y4.setBounds(370, 50, 250, 80);
        y5.setBounds(370, 150, 250, 80);
        y6.setBounds(370, 250, 250, 80);
        y7.setBounds(220, 50, 250, 80);
        y8.setBounds(220, 150, 250, 80);
        y9.setBounds(220, 250, 250, 80);

        y1.setFont(new Font("Arial", Font.BOLD, 24));
        y2.setFont(new Font("Arial", Font.BOLD, 24));
        y3.setFont(new Font("Arial", Font.BOLD, 24));
        y4.setFont(new Font("Arial", Font.BOLD, 24));
        y5.setFont(new Font("Arial", Font.BOLD, 24));
        y6.setFont(new Font("Arial", Font.BOLD, 24));
        y7.setFont(new Font("Arial", Font.BOLD, 24));
        y8.setFont(new Font("Arial", Font.BOLD, 24));
        y9.setFont(new Font("Arial", Font.BOLD, 24));

        y1.addActionListener(this);
        y2.addActionListener(this);
        y3.addActionListener(this);
        y4.addActionListener(this);
        y5.addActionListener(this);
        y6.addActionListener(this);
        y7.addActionListener(this);
        y8.addActionListener(this);
        y9.addActionListener(this);

        A.add(y1);
        A.add(y2);
        A.add(y3);
        A.add(y4);
        A.add(y5);
        A.add(y6);
        B.add(y7);
        B.add(y8);
        B.add(y9);

    }
    public JFrame  getA() { return A; }
    public JFrame  getB() { return B; }
}
