package com.zetcode.frame;

import com.zetcode.listener.BoardKeyListner;
import com.zetcode.panel.Board;
import com.zetcode.panel.BoardUI;
import com.zetcode.panel.MainUI;
import com.zetcode.panel.MenuUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static com.zetcode.var.StaticVar.*;

public class PanelChange extends JFrame {
    public Board board = null;
    public MainUI mainUi = null;
    public MenuUI menuUI = null;
    private CardLayout cards = new CardLayout();

    public PanelChange() {
        /*        setUndecorated(true); 프레임 없애기*/
        setSize(1280, 755);
        getContentPane().setLayout(cards);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //어플리케이션 종료 열려 있는 모든 윈도우 종료
        setLocationRelativeTo(null);//윈도우 창을 화면의 가운데에 띄우는 역할
        initMainUI();
    }

    public void initBoard(String level, int timeto){
        StopBoard();
        board = new Board(level, this ,timeto);
        boardKeyListner = new BoardKeyListner(board);
        board.setLayout(new BorderLayout());
        board.add(new BoardUI(board), BorderLayout.CENTER);
        getContentPane().add("board", board);
        getContentPane().addKeyListener(boardKeyListner);
        getContentPane().setFocusable(true);
        setTitle("Sokoban - Board");
        System.out.println(getContentPane().getComponents().length);
    }

    public void StopBoard(){
        if(getContentPane().getComponents().length > 2){
            getContentPane().remove(2);
        }
    }

    public void initMainUI(){
        mainUi = new MainUI(this);
        getContentPane().add("main", mainUi);
        setTitle("Sokoban - MainUI");
        System.out.println(getContentPane().getComponents().length);
    }

    public void initMenuUI(){
        menuUI = new MenuUI(this);
        getContentPane().add("menu", menuUI);
        setTitle("Sokoban - MenuUI");
        System.out.println(getContentPane().getComponents().length);
    }

    public void changePanel(){
        cards.next(getContentPane());
    }
}
