package assignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DataIO<absract> {
    public static String readattrbt(int attribute_index, int line_num, String datafile) {
        try {
            Scanner a = new Scanner(new File(datafile));
            while (line_num != 0) {
                String line = a.nextLine();
                line_num --;
            }
            String line = a.nextLine();
            int num_begin = 0, num_end = 0;
                char[] output_st = line.toCharArray();;
                for (int i = 0; i < line.length(); i++) {
                    if (output_st[i] == 36) {

                        num_begin = num_end;
                        num_end = i + 1;
                        attribute_index -= 1;

                    }
                    if (attribute_index <= 0)
                        break;
                }
                StringBuilder attrbt_out = new StringBuilder();
                for (int i = num_begin; i < num_end - 1; i++) {
                    attrbt_out.append(output_st[i]);
                }
                return attrbt_out.toString();

        } catch (Exception e) {
            System.out.println("Error in read method ... readattrbt");
            return null;
        }
    }

    public static int login_search(String login_inp, String datafile, int atrbIx) {
        String login = login_inp.toString();
        for (int i = 0; i < numberOfLines(datafile); i++) {
            if(readattrbt(atrbIx, i, datafile).equals(login_inp))
                return i;
        }
        return -1;
    }

    public static int numberOfLines(String datafile){
        try {
            Scanner a = new Scanner(new File(datafile));
            int num_of_lines = 0;
            while (a.hasNext()) {
                a.nextLine();
                num_of_lines++;
            }
            return num_of_lines;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int numberOfdata(String datafile, int atrb, String atrbVal){
        int num = 0;
        for (int i = 0; i < numberOfLines(datafile); i++) {
            if (readattrbt(atrb, i, datafile).equals(atrbVal)){
                num++;
            }
        }
        return num;
    }
    public static void write(String file_inp, String datafile){
        try {
            Files.write(Paths.get(datafile), file_inp.getBytes(), new StandardOpenOption[]{StandardOpenOption.APPEND});
        } catch (Exception e) {
            System.out.println("Error in write method");
        }
    }

    public static boolean validation(String dataToCheck, int validationType){
        String allowedStr = new String();
        switch (validationType){
            case 1: allowedStr = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM "; break;
            case 2: allowedStr = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890_."; break;
            case 3: allowedStr = "1234567890+()- "; break;
            case 4: allowedStr = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890_.!@%&*- "; break;
        }

        char [] allowedChr = allowedStr.toCharArray();
        char [] dataChar = dataToCheck.toCharArray();
        int pass = 0;
        for (int i = 0; i < dataChar.length; i++) {
            pass = 0;
            for (int j = 0; j < allowedChr.length; j++) {
                if (dataChar[i] == allowedChr[j])
                    pass = 1;
            }
            if (pass == 0){
                return false;
            }
        }
        return true;
    }
    public static void showTable(String[][] table, String[] columnNames, int type, String datafile){
        if (type == 1){
            for (int i = 1; i < 9; i++) {
                for (int j = 0; j < DataIO.numberOfLines(datafile); j++) {
                    table[j][i-1] = DataIO.readattrbt(i, j, datafile);
                }
            }
        }

        final JFrame f = new JFrame();
        TableModel tableModel = new DefaultTableModel(table, columnNames);
        JTable tableOut = new JTable(tableModel);
        tableOut.setBounds(30, 40, 400, 500);
        sienxc
        JScrollPane sp = new JScrollPane(tableOut);
        final JButton back = new JButton("Back");
        final int[] a = {0};
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
                f.dispose();
            }
        });

    }

    public static void deleterow(int row, String datafile) {
        try{
            Scanner a = new Scanner(new File(datafile));
            int number = 0;
            while (number != numberOfLines(datafile)) {

                if(number != row) {
                    String line = a.nextLine();
                    write(line + "\n", "tempfile.txt");
                }
                else
                    a.nextLine();
                number ++;
            }
            Scanner b = new Scanner(new File("tempfile.txt"));
            emptyFile(datafile);
            while(b.hasNext()){
                String templine = b.nextLine();
                write(templine + "\n", datafile);
            }
            emptyFile("tempfile.txt");
        }
        catch (Exception e){
            System.out.println("error");
        }


    }

    public static boolean checkDateformat(String StringDate){
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        DateFor.setLenient(false);
        try{
            Date date = DateFor.parse(StringDate);
            return true;
        }catch (ParseException e) {

            return false;
        }
    }


    public static long DifferenceDate(String date1, String date2){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateF1 = sdf.parse(date1);
            Date dateF2 = sdf.parse(date2);
            long difference_In_Time
                    = dateF2.getTime() - dateF1.getTime();
            long difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60));
            return difference_In_Hours;

        }catch (Exception e){
            System.out.println("error");
            return 0;
        }
    }


    private static void emptyFile(String datafile) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(datafile);
        writer.print("");
        writer.close();
        }
}
