package assignment;

import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerReg implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            if (tname.getText().isEmpty() || tmno.getText().isEmpty() || tadd.getText().isEmpty())
                JOptionPane.showMessageDialog(sub, "Please fill all the text fields!");
            else if (!DataIO.validation(tname.getText(), 1))
                JOptionPane.showMessageDialog(sub, "Name format is invalid, please try again");
            else if (!DataIO.validation(tmno.getText(), 3))
                JOptionPane.showMessageDialog(sub, "Mobile number format is invalid, please try again");
            else if(!DataIO.checkDateformat(tdob.getText()))
                JOptionPane.showMessageDialog(sub, "Invalid format of date, please try again");
            else {

                String data, data1, data2, data3;
                data = tcrmID.getText() + "$" + tname.getText() + "$" + tmno.getText() + "$";
                if (male.isSelected())
                    data1 = "male"
                            + "$";
                else
                    data1 = "female"
                            + "$";
                data2 = tdob.getText() + "$";

                data3 = tadd.getText() + "$";

                DataIO.write(data + data1 + data2 + data3 + "\n", "customers.txt");
                JOptionPane.showMessageDialog(sub, "Registration Successful!");
                JTextField username = new JTextField();
                JTextField password = new JPasswordField();
                Object[] message = {
                        "Username:", username,
                        "Password:", password
                };

                int option = JOptionPane.showConfirmDialog(null, "Would you like to create an application now?");
                Assignment.CR.getFormCmr().setVisible(false);
                if (option == JOptionPane.OK_OPTION)  {
                    new CustomerReg();
                    Assignment.a.getA().setVisible(true);
                }
                else{
                    Assignment.c.getc().setVisible(true);
                }


            }

        } else if (e.getSource() == reset) {
            String def = "";
            tname.setText(def);
            tadd.setText(def);
            tmno.setText(def);
            tdob.setText(def);

        }
        else if( e.getSource() == back){
            Assignment.CR.getFormCmr().setVisible(false);
            Assignment.c.getc().setVisible(true);
        }
    }

    private Container c;
    private JLabel title, res, name, mno, gender, dob, add, crmID, tcrmID;
    private JTextField tname, tmno, tdob;
    private JRadioButton male, female;
    private ButtonGroup gengp;
    private JButton reset, sub, back;
    private JTextArea tadd;
    private JFrame formCmr;

    public CustomerReg() {

        formCmr = new JFrame();
        formCmr.setTitle("Registration Form");
        formCmr.setBounds(300, 90, 900, 600);
        formCmr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formCmr.setResizable(false);

        c = formCmr.getContentPane();
        c.setLayout(null);

        back = new JButton( "Back");
        back.setFont(new Font("Arial", Font.PLAIN, 20));
        back.setSize(100, 20);
        back.setLocation(750, 490);
        back.addActionListener(this);
        c.add(back);

        title = new JLabel("New customer registration");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setSize(500, 50);
        title.setLocation(40, 10);
        c.add(title);



        // implement funciton that will create same length ID.
        crmID = new JLabel("Customer ID:");
        crmID.setFont(new Font("Arial", Font.BOLD, 28));
        crmID.setSize(250, 40);
        crmID.setLocation(300, 70);
        c.add(crmID);

        int number = 0;
        tcrmID = new JLabel("CID" + number);
        if (DataIO.numberOfLines("customers.txt") > 0){
            char [] temp0 = DataIO.readattrbt(1,DataIO.numberOfLines("customers.txt")-1,"customers.txt").toCharArray();
            temp0[0] = '0'; temp0[1] = '0'; temp0[2] = '0';
            number = Integer.parseInt(new String(temp0));
            number++;
            tcrmID = new JLabel("CID" + number);
        }

        tcrmID.setFont(new Font("Arial", Font.PLAIN, 28));
        tcrmID.setSize(200, 40);
        tcrmID.setLocation(500, 70);
        c.add(tcrmID);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 28));
        name.setSize(100, 30);
        name.setLocation(300, 125);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 24));
        tname.setSize(260, 40);
        tname.setLocation(440, 120);
        c.add(tname);

        mno = new JLabel("Phone number");
        mno.setFont(new Font("Arial", Font.PLAIN, 28));
        mno.setSize(200, 20);
        mno.setLocation(230, 200);
        c.add(mno);

        tmno = new JTextField();
        tmno.setFont(new Font("Arial", Font.PLAIN, 24));
        tmno.setSize(260, 40);
        tmno.setLocation(440, 190);
        c.add(tmno);

        gender = new JLabel("Gender");
        gender.setFont(new Font("Arial", Font.PLAIN, 28));
        gender.setSize(100, 20);
        gender.setLocation(300, 260);
        c.add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.PLAIN, 24));
        male.setSelected(true);
        male.setSize(100, 25);
        male.setLocation(470, 250);
        c.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.PLAIN, 24));
        female.setSelected(false);
        female.setSize(130, 25);
        female.setLocation(570, 250);
        c.add(female);

        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        dob = new JLabel("Date of birthday");
        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setSize(150, 20);
        dob.setLocation(270, 320);
        c.add(dob);

        dob = new JLabel();
        dob.setText("(in DD/MM/YYYY format)");
        dob.setFont(new Font("Arial", Font.PLAIN, 15));
        dob.setSize(250, 60);
        dob.setLocation(270, 315);
        c.add(dob);

        tdob = new JTextField();
        tdob.setFont(new Font("Arial", Font.PLAIN, 24));
        tdob.setSize(260, 40);
        tdob.setLocation(440, 310);
        c.add(tdob);

        add = new JLabel("Address");
        add.setFont(new Font("Arial", Font.PLAIN, 28));
        add.setSize(140, 20);
        add.setLocation(280, 400);
        c.add(add);

        tadd = new JTextArea();
        tadd.setFont(new Font("Arial", Font.PLAIN, 24));
        tadd.setSize(260, 75);
        tadd.setLocation(440, 370);
        tadd.setLineWrap(true);
        c.add(tadd);


        sub = new JButton("Next");
        sub.setFont(new Font("Arial", Font.PLAIN, 28));
        sub.setSize(160, 40);
        sub.setLocation(320, 470);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 28));
        reset.setSize(160, 40);
        reset.setLocation(520, 470);
        reset.addActionListener(this);
        c.add(reset);


        formCmr.setVisible(false);


    }

    public JFrame getFormCmr() {
        return formCmr;
    }


}
