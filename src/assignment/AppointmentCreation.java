package assignment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentCreation implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        Date date  = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringtodayDate= DateFor.format(date);
        if (e.getSource() == sub) {
            if (tname.getText().isEmpty() || tmno.getText().isEmpty() || tprice.getText().isEmpty())
                JOptionPane.showMessageDialog(sub, "Please fill all necessary text fields!");
            else if (!DataIO.validation(tname.getText(), 4))
                JOptionPane.showMessageDialog(sub, "Name of appliance format is invalid, please try again");
            else if (!DataIO.validation(tmno.getText(), 4))
                JOptionPane.showMessageDialog(sub, "Appliance type format is invalid, please try again");
            else if (!DataIO.validation(tadd.getText(), 4))
                JOptionPane.showMessageDialog(sub, "Comment format is invalid, please try again");
            else if (!DataIO.validation(tprice.getText(), 3))
                JOptionPane.showMessageDialog(sub, "Price format is invalid, please try again");
            else if(!DataIO.checkDateformat(tdob.getText()))
                JOptionPane.showMessageDialog(sub, "Invalid format of date, please try again");
            else if(DataIO.DifferenceDate(stringtodayDate, tdob.getText()) <=0 )
                JOptionPane.showMessageDialog(sub, "Application date cannot be today or in the past!");
            else {

                String data, data1, data2, data3, login = "";

                int x = 0;
                for (int i = 0; i < DataIO.numberOfLines("usersdata.txt"); i++) {
                    if (DataIO.readattrbt(1,i, "usersdata.txt").equals("technician")){
                        if (x == tchNameBox.getSelectedIndex()){
                            login = DataIO.readattrbt(2,i,"usersdata.txt");
                            break;
                        }
                        x++;
                    }
                }


                data = tAID.getText() + "$" + crmIDBox.getSelectedItem() + "$" + login + "$" + tname.getText() + "$" + tmno.getText() + "$";
                if (cash.isSelected())
                    data1 = "cash"
                            + "$" ;
                else
                    data1 = "card"
                            + "$";
                data2 =  tprice.getText() + "$" + tdob.getText() + "$";

                data3 = tadd.getText() + "$" /*+ "Not paid"*/;

                DataIO.write(data + data1 + data2 + data3 + "\n", "appointments.txt");
                JOptionPane.showMessageDialog(sub, "Appointment created successfully!");
                Assignment.AC.getACr().setVisible(false);
                Assignment.a.getA().setVisible(true);

            }

        } else if (e.getSource() == reset) {
            String def = "";
            tname.setText(def);
            tadd.setText(def);
            tmno.setText(def);
            tdob.setText(def);
            tprice.setText(def);


        }
        else if( e.getSource() == back){
            Assignment.AC.getACr().setVisible(false);
            Assignment.a.getA().setVisible(true);
        }
    }

    private Container c;
    private JLabel title, name, mno, AID, dob, add, crmID, tcrmID, tchName, tAID, paymentMethod, price;
    private JTextField tname, tmno, tprice, tdob;
    private JRadioButton cash, card;
    private ButtonGroup gengp;
    private JComboBox crmIDBox, tchNameBox;
    private JButton reset, sub, back;
    private JTextArea tadd;
    private JFrame ACr;

    private String[] customerIDs = new String[DataIO.numberOfLines("customers.txt")];
    private String[] tchNameArr = new String[DataIO.numberOfdata("usersdata.txt", 1, "technician")];



    public AppointmentCreation() {

        ACr = new JFrame();
        ACr.setTitle("Registration Form");
        ACr.setBounds(300, 90, 900, 600);
        ACr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ACr.setResizable(false);

        c = ACr.getContentPane();
        c.setLayout(null);

        back = new JButton( "Back");
        back.setFont(new Font("Arial", Font.PLAIN, 20));
        back.setSize(100, 20);
        back.setLocation(700, 30);
        back.addActionListener(this);
        c.add(back);


        AID = new JLabel("Application ID:");
        AID.setFont(new Font("Arial", Font.PLAIN, 20));
        AID.setSize(150, 20);
        AID.setLocation(450, 100);
        c.add(AID);

        int number = 0;
        tAID = new JLabel("AID" + number);
        if (DataIO.numberOfLines("appointments.txt") > 0){
            char [] temp0 = DataIO.readattrbt(1,DataIO.numberOfLines("appointments.txt")-1,"appointments.txt").toCharArray();
            temp0[0] = '0'; temp0[1] = '0'; temp0[2] = '0';
            number = Integer.parseInt(new String(temp0));
            number++;
            tAID = new JLabel("AID" + number);
        }

        tAID.setFont(new Font("Arial", Font.BOLD, 20));
        tAID.setSize(100, 20);
        tAID.setLocation(600, 100);
        c.add(tAID);

        title = new JLabel("New appointment creation");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setSize(500, 40);
        title.setLocation(40, 30);
        c.add(title);



        // implement funciton that will create same length ID.
        crmID = new JLabel("Customer ID:");
        crmID.setFont(new Font("Arial", Font.PLAIN, 20));
        crmID.setSize(150, 20);
        crmID.setLocation(450, 150);
        c.add(crmID);

        for (int i = 0; i < DataIO.numberOfLines("customers.txt"); i++) {
            customerIDs[i] = DataIO.readattrbt(1,i, "customers.txt");
        }
        crmIDBox = new JComboBox(customerIDs);
        crmIDBox.setFont(new Font("Arial", Font.PLAIN, 20));
        crmIDBox.setSize(100, 25);
        crmIDBox.setLocation(600, 145);
        c.add(crmIDBox);

        tchName = new JLabel("Select technician:");
        tchName.setFont(new Font("Arial", Font.PLAIN, 20));
        tchName.setSize(200, 20);
        tchName.setLocation(450, 200);
        c.add(tchName);
        int x = 0;
        for (int i = 0; i < DataIO.numberOfLines("usersdata.txt"); i++) {
            if (DataIO.readattrbt(1,i, "usersdata.txt").equals("technician")){
                tchNameArr[x] = DataIO.readattrbt(4,i, "usersdata.txt");
                x++;
            }
        }
        tchNameBox = new JComboBox(tchNameArr);
        tchNameBox.setFont(new Font("Arial", Font.PLAIN, 20));
        tchNameBox.setSize(200, 25);
        tchNameBox.setLocation(630, 195);
        c.add(tchNameBox);

        int og = tchNameBox.getSelectedIndex();


        name = new JLabel("Name of Appliance");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(200, 20);
        name.setLocation(120, 100);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(200, 20);
        tname.setLocation(120, 130);
        c.add(tname);

        mno = new JLabel("Appliance type");
        mno.setFont(new Font("Arial", Font.PLAIN, 20));
        mno.setSize(200, 20);
        mno.setLocation(120, 170);
        c.add(mno);

        tmno = new JTextField();
        tmno.setFont(new Font("Arial", Font.PLAIN, 15));
        tmno.setSize(200, 20);
        tmno.setLocation(120, 200);
        c.add(tmno);

        paymentMethod = new JLabel("Payment method");
        paymentMethod.setFont(new Font("Arial", Font.PLAIN, 20));
        paymentMethod.setSize(200, 20);
        paymentMethod.setLocation(120, 240);
        c.add(paymentMethod);

        card = new JRadioButton("Card");
        card.setFont(new Font("Arial", Font.PLAIN, 15));
        card.setSelected(true);
        card.setSize(75, 20);
        card.setLocation(120, 270);
        c.add(card);

        cash = new JRadioButton("Cash");
        cash.setFont(new Font("Arial", Font.PLAIN, 15));
        cash.setSelected(false);
        cash.setSize(80, 20);
        cash.setLocation(195, 270);
        c.add(cash);

        gengp = new ButtonGroup();
        gengp.add(cash);
        gengp.add(card);


        dob = new JLabel("Appointment date");
        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setSize(200, 20);
        dob.setLocation(120, 310);
        c.add(dob);

        dob = new JLabel("(in DD/MM/YYYY format)");
        dob.setFont(new Font("Arial", Font.PLAIN, 15));
        dob.setSize(200, 15);
        dob.setLocation(120, 330);
        c.add(dob);

        tdob = new JTextField();
        tdob.setFont(new Font("Arial", Font.PLAIN, 15));
        tdob.setSize(200, 20);
        tdob.setLocation(120, 350);
        c.add(tdob);

        add = new JLabel("Any comments to technician:");
        add.setFont(new Font("Arial", Font.PLAIN, 20));
        add.setSize(300, 20);
        add.setLocation(450, 300);
        c.add(add);

        tadd = new JTextArea();
        tadd.setFont(new Font("Arial", Font.PLAIN, 15));
        tadd.setSize(290, 75);
        tadd.setLocation(450, 340);
        tadd.setLineWrap(true);
        c.add(tadd);

        price = new JLabel("Price:");
        price.setFont(new Font("Arial", Font.PLAIN, 20));
        price.setSize(100, 20);
        price.setLocation(450, 250);
        c.add(price);

        tprice = new JTextField();
        tprice.setFont(new Font("Arial", Font.PLAIN, 20));
        tprice.setSize(150, 20);
        tprice.setLocation(550, 250);
        c.add(tprice);


        sub = new JButton("Create");
        sub.setFont(new Font("Arial", Font.PLAIN, 24));
        sub.setSize(160, 40);
        sub.setLocation(250, 445);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 24));
        reset.setSize(160, 40);
        reset.setLocation(450, 445);
        reset.addActionListener(this);
        c.add(reset);


        ACr.setVisible(false);


    }

    public JFrame getACr() {
        return ACr;
    }


}
