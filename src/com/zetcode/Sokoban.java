package com.zetcode;

import java.awt.*;
import javax.swing.*;

public class Sokoban extends JFrame {

    private final int OFFSET = 30;

    public Sokoban() {
        initUI();
    }

    private void initUI() {
        
        Board board = new Board();
        add(board);

        setTitle("Sokoban");
        
        setSize(board.getBoardWidth() + OFFSET,
                board.getBoardHeight() + 2 * OFFSET);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//어플리케이션 종료 열려 있는 모든 윈도우 종료
        setLocationRelativeTo(null);//윈도우 창을 화면의 가운데에 띄우는 역할

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {//하고 있는 일 하고 나중에 이 밑에 것들을 호출해라
            
            Sokoban game = new Sokoban();
            game.setVisible(true);//game창 화면에 나타냄
        });
    }
}
