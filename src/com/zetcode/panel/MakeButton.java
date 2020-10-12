package com.zetcode.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MakeButton {

    private JPanel panel;
    private JButton button;
    private ImageIcon buttonImg;
    private ImageIcon buttonImgDown;

    public MakeButton(JPanel panel, String path1, String path2){
        this.panel = panel;
        buttonImg = new ImageIcon(path1);
        buttonImgDown = new ImageIcon(path2);
        button = new JButton(buttonImg);
    }
    public void setup(int x, int y, int width, int height,ActionListener listener){
        button.setBounds(x,y,width,height);
        button.setRolloverIcon(buttonImgDown);
        button.setPressedIcon(buttonImgDown);
        button.setBackground(Color.red);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(listener);
        panel.add(button);
    }

    public JButton getButton(){
        return  this.button;
    }

}
