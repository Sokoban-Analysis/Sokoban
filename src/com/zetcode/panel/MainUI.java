package com.zetcode.panel;

import com.zetcode.frame.PanelChange;
import com.zetcode.tool.MakeButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI extends JPanel implements ActionListener {
    private PanelChange change;
    private ImageIcon backImg = new ImageIcon("src/resources/main/mainBack.png");
    private String playButtonPath = "src/resources/main/playButton.png";
    private String playButtonDownPath = "src/resources/main/playButtonDown.png";
    private MakeButton playbutton;

    public MainUI(PanelChange change) {
        setLayout(null);
        this.change = change;
        playbutton = new MakeButton(this, playButtonPath, playButtonDownPath);
        playbutton.setup(500,432,298,128, this::actionPerformed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==playbutton.getButton()){
            change.initMenuUI();
            change.changePanel();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backImg.getImage(), 0,0, 1280,720,null);
    }
}
