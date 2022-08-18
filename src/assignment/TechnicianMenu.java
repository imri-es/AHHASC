package assignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TechnicianMenu implements ActionListener {

    String names = ("");
    String[][] table = new String[DataIO.numberOfLines("appointments.txt")][8];
    String[] columnNames = {"Appointment ID", "Customer ID", "Technician Name", "Name of appliance",  "Appliance type", "Payment method", "Appointment Date", "HuI"};
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Assignment.m.getManagermenu().setVisible(false);
//CHANGE TO APPOINTMENTS TAB @IMRAN
        if(actionEvent.getSource() == y1) {
            technicianmenu.setVisible(false);
            Assignment.PC.getJ().setVisible(true);
        }
        else if (actionEvent.getSource() == y2) {
            namesearch.setVisible(true);;
            
        }
        else if (actionEvent.getSource() == y3){
            Assignment.l.getForm().setVisible(true);
            technicianmenu.setVisible(false);
        }
        else if (actionEvent.getSource() == y4){
            int k = 0;
            String names = name.getText();
            for (int i = 0; i < DataIO.numberOfLines("appointments.txt"); i++) {
                String login = DataIO.readattrbt(3,i,"appointments.txt");
                if (login.equals(names)){
                   k = i;
                   for (int l = 1; l < 9; l++) {
                        table[k][l-1] = DataIO.readattrbt(l, k, "appointments.txt");
                   }


                }

            }
            namesearch.setVisible(false);
            final JFrame f = new JFrame();
            TableModel tableModel = new DefaultTableModel(table, columnNames);
            JTable tableOut = new JTable(tableModel);
            tableOut.setBounds(30, 40, 400, 500);
            JScrollPane sp = new JScrollPane(tableOut);
            final JButton back = new JButton("Back");
            f.add(back);
            back.setLocation(100, 200);
            f.setLayout(new FlowLayout());
            f.add(sp);
            f.setLocation(700, 300);
            f.setSize(700, 500);
            f.setVisible(true);
            back.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    f.setVisible(false);
                }

            });


        }
    }

    private JFrame technicianmenu, namesearch, f;
    private Button y1, y2, y3, y4;
    private TextField name;
    private JTable tableOut;
    private JScrollPane sp;
    private JButton back;
    private TableModel tableModel;
    public TechnicianMenu(){
        technicianmenu = new JFrame();
        technicianmenu.setSize(700,500);
        technicianmenu.setLocation(700,300);
        technicianmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        technicianmenu.setLayout(null);
        technicianmenu.setVisible(false);
        y1 = new Button("Payment");
        y2 = new Button("Appointment management");
        y3 = new Button("Logout");
        y4 = new Button("Enter");

        y1.setBounds(160, 50, 350, 80);
        y2.setBounds(160, 150, 350, 80);
        y3.setBounds(160, 250, 350, 80);
        y4.setBounds(370, 50, 250, 80);

        y1.setFont(new Font("Arial", Font.BOLD, 24));
        y2.setFont(new Font("Arial", Font.BOLD, 24));
        y3.setFont(new Font("Arial", Font.BOLD, 24));
        y4.setFont(new Font("Arial", Font.BOLD, 24));

        y1.addActionListener(this);
        y2.addActionListener(this);
        y3.addActionListener(this);
        y4.addActionListener(this);

        technicianmenu.add(y1);
        technicianmenu.add(y2);
        technicianmenu.add(y3);
        
        namesearch = new JFrame();
        namesearch.setTitle("Enter Your Name");
        namesearch.setSize(500,150);
        namesearch.setLocation(700,300);
        namesearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        namesearch.setLayout(new FlowLayout());
        namesearch.setVisible(false);

        name = new TextField();
        name.setFont(new Font("Arial", Font.PLAIN, 15));
        name.setSize(200, 50);
        name.setLocation(250, 150);
        
        name.setColumns(20);
      
        
        namesearch.add(name);
        namesearch.add(y4);

        
        

    }
    
    public JFrame getTechnicianmenu() {
        return technicianmenu;
    }
}


