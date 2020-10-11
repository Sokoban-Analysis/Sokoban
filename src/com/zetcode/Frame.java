package com.zetcode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Frame f = new Frame();//main 함수에 넣어야함

public class Frame extends JFrame implements ActionListener{
    private JButton btn1, btn2, btn3, btn4, btn5;
    private JPanel panel;
    private JLabel label;

    public Frame(){
        this.setTitle("레벨 선택");
        this.setSize(300,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        label = new JLabel();
        btn1 = new JButton("레벨1");
        btn2 = new JButton("레벨2");
        btn3 = new JButton("레벨3");
        btn4 = new JButton("레벨4");
        btn5 = new JButton("레벨5");

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        panel.setBackground(Color.darkGray);
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        panel.add(btn5);
        panel.add(label);

        this.add(panel);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn1) {

        }else if(e.getSource()==btn2) {

        }else if(e.getSource()==btn3) {

        }else if(e.getSource()==btn4) {

        }else {

        }
    }

    public static void main(String[] args) {
        new Frame();
    }
}