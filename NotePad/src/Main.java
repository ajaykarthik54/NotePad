import javax.swing.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;

class note extends JFrame implements ActionListener{

    JFrame f;
    JTextArea t;

    note(){
        f=new JFrame("Notepad");
        t=new JTextArea();
        JMenuBar mb=new JMenuBar();

        // File Menu
        JMenu file=new JMenu("File");

        JMenuItem f1=new JMenuItem("New");
        JMenuItem f2=new JMenuItem("Open");
        JMenuItem f3=new JMenuItem("Save");
        JMenuItem f4=new JMenuItem("Print");

        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

         // Edit MEnu
        JMenu edit=new JMenu("Edit");

        JMenuItem f11=new JMenuItem("Cut");
        JMenuItem f22=new JMenuItem("Copy");
        JMenuItem f33=new JMenuItem("Paste");


        f11.addActionListener(this);
        f22.addActionListener(this);
        f33.addActionListener(this);

        edit.add(f11);
        edit.add(f22);
        edit.add(f33);

         JMenuItem close=new JMenuItem("Close");
         close.addActionListener(this);

        mb.add(file);
        mb.add(edit);
        mb.add(close);

        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(1280,720);
        f.show();
    }

    public void actionPerformed(ActionEvent e){
      //Extracting the Command into string
        String s=e.getActionCommand();
        switch(s){
            case "New":
                t.setText("");
                break;
            case "Save":
                //Creating the Object of JFileChooser class with Staring Path of C:
                JFileChooser j=new JFileChooser("C:");

                int r=j.showSaveDialog(null);
                if(r==0){
                  // Declare a File Object
                    File fi=new File(j.getSelectedFile().getAbsolutePath());
                    try {
                        FileWriter fw = new FileWriter(fi);
                        BufferedWriter bw=new BufferedWriter(fw);
                        bw.write(t.getText());
                        bw.flush();
                        bw.close();
                    }
                    catch(IOException ex){
                        throw new RuntimeException(ex);
                    }

                }else
                JOptionPane.showMessageDialog(f,"User Cancelled Saving ");

                break;
            case "Open":
                JFileChooser jo=new JFileChooser("C:");

                int ro=jo.showOpenDialog(null);
                if(ro==0){
                    // Declare a File Object
                    File fi=new File(jo.getSelectedFile().getAbsolutePath());
                    try {
                        FileReader fw = new FileReader(fi);
                        BufferedReader bw=new BufferedReader(fw);
                        String s1="",s2="";
                        s1=bw.readLine();
                        while((s2=bw.readLine())!=null){
                            s1=s1+"\n"+s2;
                        }
                        t.setText(s1);
                        bw.close();
                    }
                    catch(IOException ex){
                        throw new RuntimeException(ex);
                    }

                }else
                    JOptionPane.showMessageDialog(f,"User Cancelled Saving ");
                break;
            case "Print":
                try {
                    t.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Cut":
                t.cut();
                break;
            case "Copy":
                t.copy();
                break;
            case "Paste":
                t.paste();
                break;
            case "Close":
                f.setVisible(false);
                break;
        }
    }


    public static void main(String[] args) {
        note  n=new note();
    }
}