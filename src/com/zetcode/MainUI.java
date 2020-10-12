package com.zetcode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class MainUI extends JPanel implements ActionListener {
    private PanelChange change;
    private ImageIcon backImg = new ImageIcon("src/resources/mainBack.png");
    private ImageIcon playButtonImg = new ImageIcon("src/resources/playButton.png");
    private ImageIcon playButtonImgDown = new ImageIcon("src/resources/playButtonDown.png");
    private JButton playButton;

    public MainUI(PanelChange change) {
        setLayout(null);
        this.change = change;
        playButton = new JButton(playButtonImg);
        playButton.setBounds(500,432,298,128);
        playButton.setRolloverIcon(playButtonImgDown);
        playButton.setPressedIcon(playButtonImgDown);
        playButton.setBackground(Color.red);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.addActionListener(this);
        add(playButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==playButton){
            change.initBoard();
            change.changePanel();
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backImg.getImage(), 0,0, 1280,720,null);
    }
}
