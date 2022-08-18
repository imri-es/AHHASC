package assignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment extends MouseAdapter implements ActionListener {
    String[] columnNames = {"Appointment ID", "Feedback"};
    String[][] table = new String[DataIO.numberOfLines("payments.txt")][2];
    TableModel tableModel = new DefaultTableModel(table, columnNames);
    JTable tableOut = new JTable(tableModel);
    JTextArea tfeedback = new JTextArea();
    JComboBox aIDBox = new JComboBox();


    public void mouseClicked(MouseEvent e){
        Point point = e.getPoint();
        int row = tableOut.rowAtPoint(point);
        int option = JOptionPane.showConfirmDialog(tableOut, "Delete selected row?(" + row + ")");
        if (option == JOptionPane.OK_OPTION)  {
            DataIO.deleterow(row, "payment.txt");
        }

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Assignment.An.getAn().setVisible(false);
        if(actionEvent.getSource() == y4) {
            Assignment.m.getManagermenu().setVisible(true);
        }
        else if(actionEvent.getSource() == y5) {
            Assignment.l.getForm().setVisible(true);
        }
        else if(actionEvent.getSource() == y1){
            final JFrame f = new JFrame("Analysis");
            JLabel income = new JLabel("Income"),
                    tdyInc = new JLabel("Today:"),
                    WkInc = new JLabel("This week:"),
                    MthInc = new JLabel("This month:"),
                    YrInc =  new JLabel("This year:"),
                    NAp = new JLabel("Number of appointments"),
                    tdyNap = new JLabel("Today:"),
                    WkNap = new JLabel("This week:"),
                    MthNap = new JLabel("This month: "),
                    YrNap = new JLabel("This year:");

            f.setVisible(true);
            f.setBounds(700, 300, 700, 500);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setLayout(null);
            Date date = new Date();
            SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
            String stringtodayDate= DateFor.format(date);
            int todayIncome = 0, weekIncome = 0, monthIncome = 0, yearIncome = 0;
            int todayNumber = 0, weekNumber = 0, monthNumber = 0, yearNumber = 0;
            for (int i = 0; i < DataIO.numberOfLines("appointments.txt"); i++) {
                String lineDate = DataIO.readattrbt(8, i, "appointments.txt");
                long difference = DataIO.DifferenceDate(lineDate, stringtodayDate);
                if (difference == 0 ) {
                    todayIncome += Integer.parseInt(DataIO.readattrbt(7, i, "appointments.txt"));
                    todayNumber ++;
                }
                if(difference >= 0 && difference <169) {
                    weekIncome += Integer.parseInt(DataIO.readattrbt(7, i, "appointments.txt"));
                    weekNumber++;
                }
                if(difference >= 0 && difference < 720) {
                    monthIncome += Integer.parseInt(DataIO.readattrbt(7, i, "appointments.txt"));
                    monthNumber ++;
                }
                if(difference >= 0 && difference < 8640) {
                    yearIncome += Integer.parseInt(DataIO.readattrbt(7, i, "appointments.txt"));
                    yearNumber ++;
                }

            }
            JLabel tdyIncVal = new JLabel(String.valueOf(todayIncome)),
                    MthIncVal = new JLabel(String.valueOf(monthIncome)),
                    YrIncVal = new JLabel(String.valueOf(yearIncome)),
                    WkIncVal = new JLabel(String.valueOf(weekIncome)),
                    tdyNumVal = new JLabel(String.valueOf(todayNumber)),
                    MthNumVal = new JLabel(String.valueOf(monthNumber)),
                    YrNumVal = new JLabel(String.valueOf(yearNumber)),
                    WkNumVal = new JLabel(String.valueOf(weekNumber));
            JButton back = new JButton("Back");
            back.setBounds(400, 350, 160, 40);
            back.setFont(new Font("Arial", Font.PLAIN, 28));
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    f.dispose();
                    Assignment.An.getAn().setVisible(true);
                }
            });
            f.add(back);

            income.setBounds(70, 10, 300, 40);
            income.setFont(new Font("Arial", Font.BOLD, 36));
            f.add(income);

            tdyInc.setBounds(120, 50, 100, 24);
            tdyInc.setFont(new Font("Arial", Font.PLAIN, 24));
            f.add(tdyInc);

            tdyIncVal.setBounds(280, 50, 100, 20);
            tdyIncVal.setFont(new Font("Arial", Font.BOLD, 24));
            f.add(tdyIncVal);

            WkInc.setBounds(120, 80, 160, 20);
            WkInc.setFont(new Font("Arial", Font.PLAIN, 24));
            f.add(WkInc);

            WkIncVal.setBounds(280, 80, 100, 20);
            WkIncVal.setFont(new Font("Arial", Font.BOLD, 24));
            f.add(WkIncVal);

            MthInc.setBounds(120, 110, 160, 20);
            MthInc.setFont(new Font("Arial", Font.PLAIN, 24));
            f.add(MthInc);

            MthIncVal.setBounds(280, 110, 100, 20);
            MthIncVal.setFont(new Font("Arial", Font.BOLD, 24));
            f.add(MthIncVal);


            YrInc.setBounds(120, 140, 160, 24);
            YrInc.setFont(new Font("Arial", Font.PLAIN, 24));
            f.add(YrInc);

            YrIncVal.setBounds(280, 140, 100, 20);
            YrIncVal.setFont(new Font("Arial", Font.BOLD, 24));
            f.add(YrIncVal);





            NAp.setBounds(70, 180, 450, 40);
            NAp.setFont(new Font("Arial", Font.BOLD, 36));
            f.add(NAp);

            tdyNap.setBounds(120, 220, 100, 24);
            tdyNap.setFont(new Font("Arial", Font.PLAIN, 24));
            f.add(tdyNap);

            tdyNumVal.setBounds(280, 224, 100, 20);
            tdyNumVal.setFont(new Font("Arial", Font.BOLD, 24));
            f.add(tdyNumVal);

            WkNap.setBounds(120, 250, 160, 24);
            WkNap.setFont(new Font("Arial", Font.PLAIN, 24));
            f.add(WkNap);

            WkNumVal.setBounds(280, 254, 100, 20);
            WkNumVal.setFont(new Font("Arial", Font.BOLD, 24));
            f.add(WkNumVal);

            MthNap.setBounds(120, 280, 160, 24);
            MthNap.setFont(new Font("Arial", Font.PLAIN, 24));
            f.add(MthNap);

            MthNumVal.setBounds(280, 284, 100, 20);
            MthNumVal.setFont(new Font("Arial", Font.BOLD, 24));
            f.add(MthNumVal);


            YrNap.setBounds(120, 310, 160, 24);
            YrNap.setFont(new Font("Arial", Font.PLAIN, 24));
            f.add(YrNap);

            YrNumVal.setBounds(280, 314, 100, 20);
            YrNumVal.setFont(new Font("Arial", Font.BOLD, 24));
            f.add(YrNumVal);
        }

        else if(actionEvent.getSource() == y2){
            for (int i = 1; i < 3; i++) {
                for (int j = 0; j < DataIO.numberOfLines("payments.txt"); j++) {
                    table[j][i-1] = DataIO.readattrbt(i, j, "payments.txt");
                }
            }
            JButton back = new JButton("Back");
            back.setLocation(100, 200);
            final JFrame f = new JFrame();
            tableModel = new DefaultTableModel(table, columnNames);
            tableOut = new JTable(tableModel);
            tableOut.setBounds(30, 40, 400, 500);
            tableOut.addMouseListener(this);
            JScrollPane sp = new JScrollPane(tableOut);
            f.add(sp);
            f.add(back);
            f.setLocation(700, 300);
            f.setSize(700, 300);
            f.setLayout(new FlowLayout());
            f.setVisible(true);
            back.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Assignment.An.getAn().setVisible(true);
                    f.setVisible(false);
                }

            });


        }
        else if(actionEvent.getSource() == y3){
            Assignment.An.getAn().setVisible(true);
            String[][] table = new String[DataIO.numberOfLines("payments.txt")][2];
            TableModel tableModel = new DefaultTableModel(table, columnNames);
            JTable tableOut = new JTable(tableModel);
            UserAccounts.search(table, columnNames,"payments.txt", 3);
        }
    }
    private JFrame An;
    private Button y1, y2, y3, y4, y5;
    public Payment(){
        An = new JFrame("Payments");
        An.setSize(700,500);
        An.setLocation(700,300);
        An.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        An.setLayout(null);
        y1 = new Button("Analysis");
        y2 = new Button("Delete Payments");
        y3 = new Button("Search");
        y4 = new Button("Go back");
        y5 = new Button("Log Out");
        //this is the current object -> first

        y1.addActionListener(this);
        y2.addActionListener(this);
        y3.addActionListener(this);
        y4.addActionListener(this);
        y5.addActionListener(this);

        y1.setBounds(50, 50, 250, 80);
        y2.setBounds(50, 150, 250, 80);
        y3.setBounds(50, 250, 250, 80);
        y4.setBounds(370, 50, 250, 80);
        y5.setBounds(370, 150, 250, 80);

        y1.setFont(new Font("Arial", Font.BOLD, 24));
        y2.setFont(new Font("Arial", Font.BOLD, 24));
        y3.setFont(new Font("Arial", Font.BOLD, 24));
        y4.setFont(new Font("Arial", Font.BOLD, 24));
        y5.setFont(new Font("Arial", Font.BOLD, 24));



        An.add(y1);
        An.add(y2);
        An.add(y3);
        An.add(y4);
        An.add(y5);

    }
    public JFrame  getAn() { return An; }
}




