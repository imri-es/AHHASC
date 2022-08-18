package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentCollection {
    private JFrame j = new JFrame("Collect payment");
    public PaymentCollection(){

        String[] tchNameArr = new String[DataIO.numberOfLines("appointments.txt")];
        for (int i = 0; i < DataIO.numberOfLines("appointments.txt"); i++) {
            tchNameArr[i] = DataIO.readattrbt(1,i, "appointments.txt");
        }

        JLabel aID = new JLabel("Appointment ID:"),  feedback = new JLabel("Feedback: ");
        final JTextArea tfeedback = new JTextArea();
        JButton collect = new JButton("Collect payment"), back = new JButton("Back");
        final JComboBox aIDBox = new JComboBox(tchNameArr);

        j.setBounds( 700, 300 , 700, 300);
        j.setLayout(null);
        j.setVisible(false);

        aID.setFont(new Font("Arial", Font.BOLD, 20));
        aID.setBounds(100, 30, 160, 40);

        feedback.setFont(new Font("Arial", Font.BOLD, 20));
        feedback.setBounds(100, 90, 160, 40);


        aIDBox.setFont(new Font("Arial", Font.PLAIN, 20));
        aIDBox.setBounds(270, 37, 70, 25);


        tfeedback.setFont(new Font("Arial", Font.PLAIN, 20));
        tfeedback.setBounds(100, 130, 250, 40);
        tfeedback.setLineWrap(true);

        j.add(aID);
        j.add(aIDBox);

        j.add(feedback);
        j.add(tfeedback);

        back.setBounds(450, 130, 160, 40);
        back.setFont(new Font("Arial", Font.BOLD, 20));
        j.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Assignment.PC.getJ().setVisible(false);
                Assignment.PC = new PaymentCollection();
                Assignment.t.getTechnicianmenu().setVisible(true);
            }
        });

        collect.setBounds(400, 30, 250, 50);
        collect.setFont(new Font("Arial", Font.BOLD, 24));
        collect.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (tfeedback.getText().isEmpty()){
                    JOptionPane.showMessageDialog(new JButton(), "Please fill feedback field");
                    return;
                }
                else if(DataIO.login_search((String)aIDBox.getSelectedItem(), "payments.txt", 1) >0 ){
                    JOptionPane.showMessageDialog(new JButton(), "Payment is already done for this appointment");
                    return;
                }
                Date date = new Date();
                SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
                String stringtodayDate= DateFor.format(date);
                int g = DataIO.login_search((String)aIDBox.getSelectedItem(), "appointments.txt", 1);
                if (DataIO.DifferenceDate(stringtodayDate, DataIO.readattrbt(8, g, "appointments.txt")) < 0 ) {
                    DataIO.write(aIDBox.getSelectedItem() + "$" + tfeedback.getText() + "$\n", "payments.txt");
                    JOptionPane.showMessageDialog(new JButton(), "Payment collected!");
                    Assignment.PC.getJ().setVisible(false);
                    Assignment.PC = new PaymentCollection();
                    Assignment.t.getTechnicianmenu().setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(new JButton(),"You cannot collect payment before appointment happen");

            }
        });
        j.add(collect);
    }
    public JFrame getJ() { return j; }

}
