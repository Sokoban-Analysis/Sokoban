package com.zetcode;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.zetcode.StaticVar.*;

public class BoardKeyListner extends KeyAdapter{
    private Board board;
    private Player soko;
    public BoardKeyListner(Board board){
        this.board = board;
        this.soko = board.soko;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (board.isCompleted) {
            return;
        }
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                System.out.println("확인");
                if (board.checkWallCollision(soko, LEFT_COLLISION)) {
                    return;
                }
                if (board.checkBagCollision(LEFT_COLLISION)) {
                    return;
                }
                soko.move(-SPACE, 0);
                break;
            case KeyEvent.VK_RIGHT:
                if (board.checkWallCollision(soko, RIGHT_COLLISION)) {
                    return;
                }
                if (board.checkBagCollision(RIGHT_COLLISION)) {
                    return;
                }
                soko.move(SPACE, 0);
                break;
            case KeyEvent.VK_UP:
                if (board.checkWallCollision(soko, TOP_COLLISION)) {
                    return;
                }
                if (board.checkBagCollision(TOP_COLLISION)) {
                    return;
                }
                soko.move(0, -SPACE);
                break;
            case KeyEvent.VK_DOWN:
                if (board.checkWallCollision(soko, BOTTOM_COLLISION)) {
                    return;
                }
                if (board.checkBagCollision(BOTTOM_COLLISION)) {
                    return;
                }
                soko.move(0, SPACE);
                break;
            case KeyEvent.VK_R:
                board.restartLevel();
                break;
            default:
                break;
        }
        board.repaint();
    }
}

