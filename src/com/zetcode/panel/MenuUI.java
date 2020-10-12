package com.zetcode.panel;

import com.zetcode.frame.PanelChange;
import com.zetcode.tool.MakeButton;
import com.zetcode.tool.MakeFont;
import com.zetcode.tool.MakeLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.zetcode.var.StaticVar.*;

public class MenuUI extends JPanel implements ActionListener {
    private PanelChange change;
    private MakeLabel makeLabel;
    private ImageIcon backImg = new ImageIcon(getClass().getClassLoader().getResource("resources/menu/menuBack.png"));
    private final URL[] levelButtonPath
            ={
            getResource("resources/menu/l1.png"),
            getResource("resources/menu/l2.png"),
            getResource("resources/menu/l3.png"),
            getResource("resources/menu/l4.png"),
            getResource("resources/menu/l4.png")
            };
    private final URL[] levelButtonPathDown
            ={
            getResource("resources/menu/down/l1.png"),
            getResource("resources/menu/down/l2.png"),
            getResource("resources/menu/down/l3.png"),
            getResource("resources/menu/down/l4.png"),
            getResource("resources/menu/down/l5.png")
            };
    private final URL[] modButtonPath
            ={
            getResource("resources/menu/continue.png"),
            getResource("resources/menu/1p.png"),
            getResource("resources/menu/2p.png"),
            getResource("resources/menu/replay.png")
    };
    private final URL[] modButtonPathDown
            ={
            getResource("resources/menu/down/continue.png"),
            getResource("resources/menu/down/1p.png"),
            getResource("resources/menu/down/2p.png"),
            getResource("resources/menu/down/replay.png")
    };

    private final URL[] modButtonPathPlayyer
            ={
            getResource("resources/menu/1pc.png"),
            getResource("resources/menu/2pc.png")
    };

    private MakeButton[] levelButton = new MakeButton[5];
    private MakeButton[] modButton = new MakeButton[4];

    private URL getResource(String path){
        return getClass().getClassLoader().getResource(path);
    }


    public MenuUI(PanelChange change) {
        setLayout(null);
        this.change = change;
        modStatue = OnePLAYER;
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
        modButton[1].setup(769, 377,348,108, this::actionPerformed);
        modButton[2] = new MakeButton(this,modButtonPath[2], modButtonPathDown[2]);
        modButton[2].setup(769, 477,348,108, this::actionPerformed);
        modButton[3] = new MakeButton(this,modButtonPath[3], modButtonPathDown[3]);
        modButton[3].setup(769, 577,348,108, this::actionPerformed);

        makeLabel = new MakeLabel(802, 167, 300,65, 60f);
        makeLabel.add(this);
        SetUpMod();
    }

    public void SetUpMod(){
        if(modStatue == OnePLAYER){
            modButton[1].getButton().setIcon(new ImageIcon(modButtonPathPlayyer[0]));
            modButton[2].getButton().setIcon(new ImageIcon(modButtonPath[2]));
        }else{
            modButton[2].getButton().setIcon(new ImageIcon(modButtonPathPlayyer[1]));
            modButton[1].getButton().setIcon(new ImageIcon(modButtonPath[1]));
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==levelButton[0].getButton()){
            change.initBoard(level5, 10);
            change.changePanel();
        }else if(e.getSource()==levelButton[1].getButton()){
            change.initBoard(level2, 10);
            change.changePanel();
        }else if(e.getSource()==levelButton[2].getButton()){
            change.initBoard(level3, 30);
            change.changePanel();
        }else if(e.getSource()==levelButton[3].getButton()){
            change.initBoard(level4, 40);
            change.changePanel();
        }else if(e.getSource()==levelButton[4].getButton()){
            change.initBoard(level5, 50);
            change.changePanel();
        }else if(e.getSource()==modButton[1].getButton()){
            modStatue = OnePLAYER;
            SetUpMod();
        }else if(e.getSource()==modButton[2].getButton()){
            modStatue = TwoPLAYER;
            SetUpMod();
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backImg.getImage(), 0,0, 1280,720,null);
        String scoreStr = "";
        File file = new File("src/resources/data/score.txt");
        try{
            FileReader ir = new FileReader(file);
            BufferedReader  reader = new BufferedReader(ir);
            scoreStr = reader.readLine();
        }catch(Exception e){}
        makeLabel.setText(scoreStr);
    }
}
