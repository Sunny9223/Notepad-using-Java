package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame implements ActionListener {
    JButton b1;
    About(){
        setBounds(400,100,550,450);
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/windows.png"));
        Image i2 = i1.getImage().getScaledInstance(350,80, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(60,40,400,80);
        add(l1);
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/notepad.png"));
        Image i5 = i4.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(50,180,70,70);
        add(l2);
        JLabel l3 = new JLabel("<html><p style='color:red;'>Notepad Clone</p><br>This is a Notepad Clone running on Windows 10. <br>It can run seemlessly on any operating system because,<br> it is made on Java.<br></html>");
        l3.setFont(new Font("SAN SARIF", Font.PLAIN, 14));
        l3.setBounds(150,50,500,300);
        add(l3);
        b1 = new JButton("OK");
        b1.setBounds(400,300,100,20);
        b1.addActionListener(this);
        add(b1);
    }

    public static void main(String[] args) {
        new About().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }
}
