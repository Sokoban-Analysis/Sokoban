package com.zetcode.listener;

import com.zetcode.game.Player;
import com.zetcode.panel.Board;
import com.zetcode.var.StaticVar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.zetcode.var.StaticVar.*;
import static com.zetcode.var.StaticVar.soko;

public class BoardKeyListner extends KeyAdapter{
    private Board board;
    private int player1;
    private int player2;
    public BoardKeyListner(Board board){
        this.board = board;
        if(modStatue == TwoPLAYER){
            System.out.println("두명이다");
            player1 = 1;
            player2 = 2;
        }else{
            player1 = 0;
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (board.isCompleted) {
            return;
        }
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                if (board.checkWallCollision(soko[0], LEFT_COLLISION, this.player1)) {
                    System.out.println("레프트 월");
                    return;
                }
                if (board.checkBagCollision(LEFT_COLLISION, this.player1)) {
                    System.out.println("레프트 백");
                    return;
                }
                soko[0].move(-SPACE, 0);
                break;
            case KeyEvent.VK_RIGHT:
                if (board.checkWallCollision(soko[0], RIGHT_COLLISION, this.player1)) {
                    System.out.println("레프트 월");
                    return;
                }
                if (board.checkBagCollision(RIGHT_COLLISION, this.player1)) {
                    System.out.println("라이트 백");
                    return;
                }
                soko[0].move(SPACE, 0);
                break;
            case KeyEvent.VK_UP:
                if (board.checkWallCollision(soko[0], TOP_COLLISION, this.player1)) {
                    System.out.println("탑 월");
                    return;
                }
                if (board.checkBagCollision(TOP_COLLISION, this.player1)) {
                    System.out.println("탑 백");
                    return;
                }
                soko[0].move(0, -SPACE);
                break;
            case KeyEvent.VK_DOWN:
                if (board.checkWallCollision(soko[0], BOTTOM_COLLISION,this.player1)) {
                    System.out.println("다운 월");
                    return;
                }
                if (board.checkBagCollision(BOTTOM_COLLISION, this.player1)) {
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

