package com.zetcode.listener;

import com.zetcode.game.Player;
import com.zetcode.panel.Board;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.zetcode.var.StaticVar.*;
import static com.zetcode.var.StaticVar.soko;

public class BoardKeyListner extends KeyAdapter{
    private Board board;
    public BoardKeyListner(Board board){
        this.board = board;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (board.isCompleted) {
            return;
        }
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                if (board.checkWallCollision(soko[0], LEFT_COLLISION)) {
                    System.out.println("레프트 월");
                    return;
                }
                if (board.checkBagCollision(LEFT_COLLISION)) {
                    System.out.println("레프트 백");
                    return;
                }
                soko[0].move(-SPACE, 0);
                break;
            case KeyEvent.VK_RIGHT:
                if (board.checkWallCollision(soko[0], RIGHT_COLLISION)) {
                    System.out.println("레프트 월");
                    return;
                }
                if (board.checkBagCollision(RIGHT_COLLISION)) {
                    System.out.println("라이트 백");
                    return;
                }
                soko[0].move(SPACE, 0);
                break;
            case KeyEvent.VK_UP:
                if (board.checkWallCollision(soko[0], TOP_COLLISION)) {
                    System.out.println("탑 월");
                    return;
                }
                if (board.checkBagCollision(TOP_COLLISION)) {
                    System.out.println("탑 백");
                    return;
                }
                soko[0].move(0, -SPACE);
                break;
            case KeyEvent.VK_DOWN:
                if (board.checkWallCollision(soko[0], BOTTOM_COLLISION)) {
                    System.out.println("다운 월");
                    return;
                }
                if (board.checkBagCollision(BOTTOM_COLLISION)) {
                    System.out.println("다운 백");
                    return;
                }
                soko[0].move(0, SPACE);
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

