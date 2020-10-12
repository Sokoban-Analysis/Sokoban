package com.zetcode.panel;

import com.zetcode.frame.PanelChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUI extends JPanel implements ActionListener {
    private PanelChange change;
    private ImageIcon backImg = new ImageIcon("src/resources/menu/menuBack.png");
    private JButton playButton;


    public MenuUI(PanelChange change) {
        setLayout(null);
        this.change = change;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==playButton){
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backImg.getImage(), 0,0, 1280,720,null);
    }

}
