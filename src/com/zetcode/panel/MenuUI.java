package com.zetcode.panel;

import com.zetcode.frame.PanelChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUI extends JPanel implements ActionListener {
    private PanelChange change;
    private ImageIcon backImg = new ImageIcon("src/resources/menu/menuBack.png");
    private final String[] levelButtonPath
            ={
            "src/resources/menu/l1.png",
            "src/resources/menu/l2.png",
            "src/resources/menu/l3.png",
            "src/resources/menu/l4.png",
            "src/resources/menu/l5.png",
            };
    private final String[] levelButtonPathDown
            ={
            "src/resources/menu/down/l1.png",
            "src/resources/menu/down/l2.png",
            "src/resources/menu/down/l3.png",
            "src/resources/menu/down/l4.png",
            "src/resources/menu/down/l5.png",
    };
    private final String[] modButtonPath
            ={
            "src/resources/menu/continue.png",
            "src/resources/menu/1p.png",
            "src/resources/menu/2p.png",
    };
    private final String[] modButtonPathDown
            ={
            "src/resources/menu/down/continue.png",
            "src/resources/menu/down/1p.png",
            "src/resources/menu/down/2p.png",
    };

    private MakeButton[] levelButton = new MakeButton[5];
    private MakeButton[] modButton = new MakeButton[3];

    public MenuUI(PanelChange change) {
        setLayout(null);
        this.change = change;
        System.out.println(levelButtonPath[0]);
        levelButton[0] = new MakeButton(this,levelButtonPath[0], levelButtonPathDown[0]);
        levelButton[0].setup(80, 277,288,108, this::actionPerformed);
        levelButton[1] = new MakeButton(this,levelButtonPath[1], levelButtonPathDown[1]);
        levelButton[1].setup(362, 277,288,108, this::actionPerformed);
        levelButton[2] = new MakeButton(this,levelButtonPath[2], levelButtonPathDown[2]);
        levelButton[2].setup(80, 388,288,108, this::actionPerformed);
        levelButton[3] = new MakeButton(this,levelButtonPath[3], levelButtonPathDown[3]);
        levelButton[3].setup(362, 388,288,108, this::actionPerformed);
        levelButton[4] = new MakeButton(this,levelButtonPath[4], levelButtonPathDown[4]);
        levelButton[4].setup(80, 499,288,108, this::actionPerformed);

        modButton[0] = new MakeButton(this,modButtonPath[0], modButtonPathDown[0]);
        modButton[0].setup(769, 277,348,108, this::actionPerformed);
        modButton[1] = new MakeButton(this,modButtonPath[1], modButtonPathDown[1]);
        modButton[1].setup(769, 399,348,108, this::actionPerformed);
        modButton[2] = new MakeButton(this,modButtonPath[2], modButtonPathDown[2]);
        modButton[2].setup(769, 511,348,108, this::actionPerformed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backImg.getImage(), 0,0, 1280,720,null);
    }

}
