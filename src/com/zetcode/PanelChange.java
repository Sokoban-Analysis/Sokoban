package com.zetcode;

import javax.swing.*;
import java.awt.*;

import static com.zetcode.StaticVar.*;

public class PanelChange extends JFrame {
    public Board board = null;
    public MainUI mainUi = null;
    private CardLayout cards = new CardLayout();


    public PanelChange(){
        setSize(1280, 720);
        getContentPane().setLayout(cards);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //어플리케이션 종료 열려 있는 모든 윈도우 종료
        setLocationRelativeTo(null);//윈도우 창을 화면의 가운데에 띄우는 역할
        initMainUI();
    }


    public void initBoard(){
        board = new Board(level1);
        getContentPane().add("board",board);
        getContentPane().addKeyListener(new BoardKeyListner(board));
        getContentPane().setFocusable(true);
        setTitle("Sokoban - Board");
    }

    public void initMainUI(){
        mainUi = new MainUI(this);
        getContentPane().add("main", mainUi);
        setTitle("Sokoban - MainUI");
    }

    public void changePanel(){
        cards.next(getContentPane());
    }
}
