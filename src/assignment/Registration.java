package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Registration implements ActionListener{

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == sub) {
            if(tlogin.getText().isEmpty() || tpas1.getText().isEmpty() || tpas2.getText().isEmpty() || tname.getText().isEmpty() || tmno.getText().isEmpty() || tadd.getText().isEmpty())
                JOptionPane.showMessageDialog(sub, "Please fill all the text fields!");
            else if (DataIO.login_search(tlogin.getText(), "usersdata.txt", 2) >= 0)
                JOptionPane.showMessageDialog(sub , "Username is already taken, please try another!");
            else if(!tpas1.getText().equals(tpas2.getText()))
                JOptionPane.showMessageDialog(sub, "Passwords are not same! Try again!");
            else if(!DataIO.validation(tlogin.getText(), 2))
                JOptionPane.showMessageDialog(sub, "Username format is invalid, please try again");
            else if(!DataIO.validation(tpas1.getText(), 4))
                JOptionPane.showMessageDialog(sub, "Password format is invalid, please try again");
            else if(!DataIO.validation(tname.getText(), 1))
                JOptionPane.showMessageDialog(sub, "Name format is invalid, please try again");
            else if(!DataIO.validation(tmno.getText(), 3))
                JOptionPane.showMessageDialog(sub, "Mobile number format is invalid, please try again");
            else if(!DataIO.checkDateformat(tdob.getText()))
                JOptionPane.showMessageDialog(sub, "Invalid format of date, please try again");

            else {
                if (term.isSelected()) {
                    String data, data1, data2, data3;
                    if(tech.isSelected()){
                        data = "technician$" + tlogin.getText() + "$" + tpas1.getText() + "$" + tname.getText() + "$"
                                + tmno.getText() + "$";
                    }
                    else
                        data = "manager$" + tlogin.getText() + "$" + tpas1.getText() + "$" + tname.getText() + "$"
                                + tmno.getText() + "$";
                    if (male.isSelected())
                        data1 = "male"
                                + "$";
                    else
                        data1 = "female"
                                + "$";
                    data2 = tdob.getText() + "$";

                    data3 = tadd.getText() + "$";

                    DataIO.write(data+data1+data2+data3+"\n", "usersdata.txt");
                    JOptionPane.showMessageDialog(sub, "Registration Successful!");
                    Assignment.reg.getFormMgr().setVisible(false);
                    Assignment.UA = new UserAccounts();
                    Assignment.UA.getUa().setVisible(true);

                }
                else
                    JOptionPane.showMessageDialog(sub, "Please accept terms and conditions");
            }

        }

        else if (e.getSource() == reset) {
            String def = "";
            tname.setText(def);
            tadd.setText(def);
            tmno.setText(def);
            term.setSelected(false);
            tdob.setText(def);
            tlogin.setText(def);
            tpas1.setText(def);
            tpas2.setText(def);

        }
        else if(e.getSource() == back) {
            Assignment.reg.getFormMgr().setVisible(false);
            Assignment.UA.getUa().setVisible(true);
        }
    }
    private Container c;
    private JLabel title, res, name, mno, gender, dob, add, role, login, pas1, pas2;
    private JTextField tname, tmno, tlogin, tpas1, tpas2, tdob;
    private JRadioButton male, female, manager, tech;
    private ButtonGroup gengp, rolegp;
    private JCheckBox term;
    private JButton reset, sub, back;
    private JTextArea tadd;
    private JFrame formMgr;

    public Registration(){

        formMgr = new JFrame();
        formMgr.setTitle("Registration Form");
        formMgr.setBounds(700, 300, 700, 600);
        formMgr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formMgr.setResizable(false);

        c = formMgr.getContentPane();
        c.setLayout(null);

        title = new JLabel("New user registration");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setSize(400, 50);
        title.setLocation(40, 10);
        c.add(title);

        login = new JLabel("Username");
        login.setFont(new Font("Arial", Font.PLAIN, 20));
        login.setSize(100, 20);
        login.setLocation(330, 170);
        c.add(login);

        tlogin = new JTextField();
        tlogin.setFont(new Font("Arial", Font.PLAIN, 20));
        tlogin.setSize(200, 30);
        tlogin.setLocation(330, 190);
        c.add(tlogin);

        pas1 = new JLabel("Password");
        pas1.setFont(new Font("Arial", Font.PLAIN, 20));
        pas1.setSize(100, 20);
        pas1.setLocation(330, 230);
        c.add(pas1);

        tpas1 = new JTextField();
        tpas1.setFont(new Font("Arial", Font.PLAIN, 20));
        tpas1.setSize(200, 30);
        tpas1.setLocation(330, 250);
        c.add(tpas1);



        pas2 = new JLabel("Re-enter password");
        pas2.setFont(new Font("Arial", Font.PLAIN, 20));
        pas2.setSize(250, 20);
        pas2.setLocation(330, 290);
        c.add(pas2);

        tpas2 = new JTextField();
        tpas2.setFont(new Font("Arial", Font.PLAIN, 20));
        tpas2.setSize(200, 30);
        tpas2.setLocation(330, 310);
        c.add(tpas2);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(70, 70);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(200, 20);
        tname.setLocation(70, 90);
        c.add(tname);

        mno = new JLabel("Phone Number");
        mno.setFont(new Font("Arial", Font.PLAIN, 20));
        mno.setSize(150, 20);
        mno.setLocation(70, 120);
        c.add(mno);

        tmno = new JTextField();
        tmno.setFont(new Font("Arial", Font.PLAIN, 15));
        tmno.setSize(200, 20);
        tmno.setLocation(70, 140);
        c.add(tmno);

        gender = new JLabel("Gender");
        gender.setFont(new Font("Arial", Font.PLAIN, 20));
        gender.setSize(100, 20);
        gender.setLocation(70, 170);
        c.add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.PLAIN, 18));
        male.setSelected(true);
        male.setSize(75, 20);
        male.setLocation(70, 195);
        c.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.PLAIN, 18));
        female.setSelected(false);
        female.setSize(90, 20);
        female.setLocation(145, 195);
        c.add(female);

        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        role = new JLabel("Select role:");
        role.setFont(new Font("Arial", Font.PLAIN, 20));
        role.setSize(100, 20);
        role.setLocation(330, 70);
        c.add(role);

        manager = new JRadioButton("Manager");
        manager.setFont(new Font("Arial", Font.PLAIN, 20));
        manager.setSelected(true);
        manager.setSize(120, 25);
        manager.setLocation(330, 90);
        c.add(manager);

        tech = new JRadioButton("Technician");
        tech.setFont(new Font("Arial", Font.PLAIN, 20));
        tech.setSelected(false);
        tech.setSize(120, 25);
        tech.setLocation(450, 90);
        c.add(tech);

        rolegp = new ButtonGroup();
        rolegp.add(manager);
        rolegp.add(tech);

        dob = new JLabel();
        dob.setText("Date of birthday");
        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setSize(250, 30);
        dob.setLocation(70, 225);
        c.add(dob);

        dob = new JLabel();
        dob.setText("(in DD/MM/YYYY format)");
        dob.setFont(new Font("Arial", Font.PLAIN, 15));
        dob.setSize(250, 60);
        dob.setLocation(70, 230);
        c.add(dob);

        tdob = new JTextField();
        tdob.setFont(new Font("Arial", Font.PLAIN, 15));
        tdob.setSize(200, 20);
        tdob.setLocation(70, 270);
        c.add(tdob);

        add = new JLabel("Address");
        add.setFont(new Font("Arial", Font.PLAIN, 20));
        add.setSize(100, 20);
        add.setLocation(70, 300);
        c.add(add);

        tadd = new JTextArea();
        tadd.setFont(new Font("Arial", Font.PLAIN, 15));
        tadd.setSize(200, 75);
        tadd.setLocation(70, 320);
        tadd.setLineWrap(true);
        c.add(tadd);

        term = new JCheckBox("Accept Terms And Conditions.");
        term.setFont(new Font("Arial", Font.PLAIN, 20));
        term.setSize(350, 20);
        term.setLocation(200, 420);
        c.add(term);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 20));
        sub.setSize(150, 50);
        sub.setLocation(180, 450);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 20));
        reset.setSize(140, 50);
        reset.setLocation(350, 450);
        reset.addActionListener(this);
        c.add(reset);

        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 20));
        back.setSize(100, 30);
        back.setLocation(550, 490);
        back.addActionListener(this);
        c.add(back);



        formMgr.setVisible(false);


    }
    public JFrame getFormMgr() { return formMgr; }


}
