package com.zetcode.panel;

import com.zetcode.frame.PanelChange;
import com.zetcode.replay.ReplayFileReader;
import com.zetcode.tool.MakeButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

import static com.zetcode.var.StaticVar.*;
import static com.zetcode.var.StaticVar.replayFileReader;

public class ReplayListUI extends JPanel implements ActionListener {

    private PanelChange change;
    private ImageIcon backImg = new ImageIcon(getClass().getClassLoader().getResource("resources/menu/replayUI.png"));
    public ReplayListUI(PanelChange change){
        final JScrollPane scroll = new JScrollPane(this);
        this.change = change;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        scroll.setMaximumSize(new Dimension(300, 300));
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setBounds(0,0,1280,720);
        scroll.createVerticalScrollBar();
        for(int i = 0; i < 10; i++){
            MakeButton backButton = new MakeButton(this, getResource("resources/menu/down/continue.png"),
                    getResource("resources/menu/down/continue.png"));
            backButton.setup(1150, 591, 100, 100, this::actionPerformed);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
    private URL getResource(String path){
        return getClass().getClassLoader().getResource(path);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backImg.getImage(), 0,0, 1280,720,null);
    }

}
