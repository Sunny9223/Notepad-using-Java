package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
    JTextArea area;
    String text;
    Notepad(){
        setTitle("Notepad");
        setBounds(40,100,1000,400);
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem newdoc = new JMenuItem("New");
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newdoc.addActionListener(this);
        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);

        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        exit.addActionListener(this);

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);
        JMenu edit = new JMenu("Edit");
        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        cut.addActionListener(this);

        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectAll.addActionListener(this);

        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectAll);
        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About my Notepad");
        about.addActionListener(this);

        help.add(about);
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);
        setJMenuBar(menubar);
        area = new JTextArea();
        JScrollPane pane;
        pane = new JScrollPane(area);
        add(pane, BorderLayout.CENTER);

        pane.setBorder(BorderFactory.createEmptyBorder());
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("New")){
            area.setText("");
        }
        else if(ae.getActionCommand().equals("Open")){
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter retrict = new FileNameExtensionFilter("only.txt", "txt");
            chooser.addChoosableFileFilter(retrict);
            int action = chooser.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File file = chooser.getSelectedFile();
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            }catch (Exception e){}
        }
        else if(ae.getActionCommand().equals("Save")){
            JFileChooser saveas = new JFileChooser("D:/Docs");
            saveas.setApproveButtonText("Save");
            int action = saveas.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File filename = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outfile = null;
            try{
                outfile = new BufferedWriter(new FileWriter(filename));
                area.write(outfile);
            }
            catch(Exception e){}
        }
        else if(ae.getActionCommand().equals("Print")){
            try{
                area.print();
            }
            catch (Exception e){}
        }
        else if(ae.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        else if(ae.getActionCommand().equals("Copy")){
            text = area.getSelectedText();
        }
        else if(ae.getActionCommand().equals("paste")){
            area.insert(text, area.getCaretPosition());
        }
        else if (ae.getActionCommand().equals("Cut")){
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        }
        else if(ae.getActionCommand().equals("Select All")){
            area.selectAll();
        }
        else if (ae.getActionCommand().equals("About my Notepad")){
            new About().setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Notepad().setVisible(true);
    }
}