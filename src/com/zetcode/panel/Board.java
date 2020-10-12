package com.zetcode.panel;

import com.zetcode.frame.PanelChange;
import com.zetcode.game.*;
import com.zetcode.listener.BoardKeyListner;
import com.zetcode.tool.MakeLabel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.zetcode.var.StaticVar.*;

public class Board extends JPanel{

    private Map<Integer,ArrayList<Wall>> walls = new HashMap<>();
    private Map<Integer,ArrayList<Baggage>> baggs = new HashMap<>();
    private Map<Integer,ArrayList<Area>> areas = new HashMap<>();
    private int player;
    public int finishedBags = 0;
    public int nOfBags;

    private String level;
    private int timeto;
    private ImageIcon backImg = new ImageIcon(getClass().getClassLoader().getResource("resources/board/background.png"));

    public boolean isCompleted = false;
    public PanelChange panelChange;

    public Board(String level, PanelChange panelChange, int timeto) {//생성자
        setOpaque(false);
        setLayout(null);
        player = 0;
        score[0] = 0;
        time[0] = timeto;
        this.timeto = timeto;
        this.level = level;
        this.panelChange = panelChange;
        scoreTimer = Executors.newScheduledThreadPool(0);
        if(modStatue == TwoPLAYER){
            setDraw();
        }else{
            setOffsetPosition();
            initWorld();
            nOfBags = baggs.size();
        }
    }


    public void setDraw(){
        this.player = OnePLAYER;
        setOffsetPosition();
        initWorld();
        nOfBags = baggs.size();
        this.player = TwoPLAYER;
        setOffsetPosition();
        initWorld();
        nOfBags = baggs.size();
    }



    private void setOffsetPosition(){
        int maxOffset = 0;
        Xoffset = 0;
        Yoffset = 0;
        for (int i = 0; i < level.length(); i++) {
            char item = level.charAt(i);//level의 i번째 인덱스 가져오기
            switch (item) {
                case '\n':
                    Yoffset += SPACE;
                    if(Xoffset > maxOffset){
                        maxOffset = Xoffset;
                    }
                    Xoffset = 0;
                    break;
                default:
                    Xoffset += SPACE;
                    break;
            }
        }
        Xoffset = maxOffset/2;
        Yoffset = Yoffset/2;
    }

    private void initWorld() {

        walls.put(player, new ArrayList<>());
        baggs.put(player, new ArrayList<>());
        areas.put(player, new ArrayList<>());

        int x = 640 - Xoffset;
        if(player == OnePLAYER){
            x = 640 - 2*Xoffset - SPACE;
            System.out.println("one : " + x);
        }else if(player == TwoPLAYER){
            x = 640 + SPACE;
            System.out.println("two : " + x);
        }
        int y = 360 - Yoffset;

        Wall wall;
        Baggage b;
        Area a;

        for (int i = 0; i < level.length(); i++) {
            char item = level.charAt(i);//level의 i번째 인덱스 가져오기
            switch (item) {
                case '\n':
                    y += SPACE;
                    x = 640 - Xoffset;
                    if(player == OnePLAYER){
                        x = 640 - 2*Xoffset - SPACE;
                        System.out.println("one : " + x);
                    }else if(player == TwoPLAYER){
                        x = 640 + SPACE;
                        System.out.println("two : " + x);
                    }
                    break;
                case '#'://벽
                    wall = new Wall(x, y);
                    walls.get(player).add(wall);//walls 배열에 wall 추가
                    x += SPACE;
                    break;
                case '$'://옮겨야하는 공
                    b = new Baggage(x, y);//초기화??
                    baggs.get(player).add(b);
                    x += SPACE;
                    break;
                case '.':
                    a = new Area(x, y);//공을 이 여섯칸짜리 Area에다 옮겨야 함
                    areas.get(player).add(a);
                    x += SPACE;
                    break;
                case '@'://플레이어(공 옮기는 역할)
                    if(player == TwoPLAYER){
                        soko[1] = new Player(x, y);
                    }else{
                        soko[0] = new Player(x, y);
                    }
                    x += SPACE;
                    break;
                case ' '://공백
                    x += SPACE;
                    break;
                default:
                    break;
            }
        }
    }

    private void buildWorld(Graphics g) {
        g.setColor(new Color(250, 240, 170));
        g.drawImage(backImg.getImage(), 0, 0, 1280, 720, null);

        ArrayList<Actor> world = new ArrayList<>();
        world.addAll(walls.get(player));
        world.addAll(areas.get(player));
        world.addAll(baggs.get(player));
        if(player == TwoPLAYER){
            world.add(soko[1]);
        }else{
            world.add(soko[0]);
        }

        for (int i = 0; i < world.size(); i++) {
            Actor item = world.get(i);//Actor의 item 객체에 Actor 배열인 world를 채움
            if (item instanceof Player || item instanceof Baggage) {//item이 Player를 참조하는지 true/false 반환
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
        }
    }

    private void buildWorld2Pc(Graphics g) {
        g.setColor(new Color(250, 240, 170));
        g.drawImage(backImg.getImage(), 0, 0, 1280, 720, null);

        for(int i = 1; i < 3; i++){
            ArrayList<Actor> world = new ArrayList<>();
            world.addAll(walls.get(i));
            world.addAll(areas.get(i));
            world.addAll(baggs.get(i));
            if(i == TwoPLAYER){
                world.add(soko[1]);
            }else{
                world.add(soko[0]);
            }
            for (int j = 0; j < world.size(); j++) {
                Actor item = world.get(j);//Actor의 item 객체에 Actor 배열인 world를 채움
                if (item instanceof Player || item instanceof Baggage) {//item이 Player를 참조하는지 true/false 반환
                    g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
                } else {
                    g.drawImage(item.getImage(), item.x(), item.y(), this);
                }
            }
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(modStatue == TwoPLAYER){
            buildWorld2Pc(g);
        }else{
            buildWorld(g);
        }

    }

    public boolean checkWallCollision(Actor actor, int type, int player) {
        System.out.println(player);
        switch (type) {
            case LEFT_COLLISION:
                for (int i = 0; i < walls.get(player).size(); i++) {
                    Wall wall = walls.get(player).get(i);
                    if (actor.isLeftCollision(wall)) {
                        return true;
                    }
                }
                return false;
            case RIGHT_COLLISION:
                for (int i = 0; i < walls.get(player).size(); i++) {
                    Wall wall = walls.get(player).get(i);
                    if (actor.isRightCollision(wall)) {
                        return true;
                    }
                }
                return false;
            case TOP_COLLISION:
                for (int i = 0; i < walls.get(player).size(); i++) {
                    Wall wall = walls.get(player).get(i);
                    if (actor.isTopCollision(wall)) {
                        return true;
                    }
                }
                return false;
            case BOTTOM_COLLISION:
                for (int i = 0; i < walls.get(player).size(); i++) {
                    Wall wall = walls.get(player).get(i);
                    if (actor.isBottomCollision(wall)) {
                        return true;
                    }
                }
                return false;
            default:
                break;
        }
        return false;
    }

    public boolean checkBagCollision(int type, int player) {
        score[0] += 50;
        switch (type) {
            case LEFT_COLLISION:
                for (int i = 0; i < baggs.get(player).size(); i++) {
                    Baggage bag = baggs.get(player).get(i);
                    if (soko[0].isLeftCollision(bag)) {
                        for (int j = 0; j < baggs.get(player).size(); j++) {
                            Baggage item = baggs.get(player).get(j);
                            if (!bag.equals(item)) {
                                if (bag.isLeftCollision(item)) {
                                    return true;
                                }
                            }
                            if (checkWallCollision(bag, LEFT_COLLISION, player)) {
                                return true;
                            }
                        }
                        bag.move(-SPACE, 0);
                        isCompleted();
                    }
                }
                return false;
            case RIGHT_COLLISION:
                for (int i = 0; i < baggs.get(player).size(); i++) {
                    Baggage bag = baggs.get(player).get(i);
                    if (soko[0].isRightCollision(bag)) {
                        for (int j = 0; j < baggs.get(player).size(); j++) {
                            Baggage item = baggs.get(player).get(j);
                            if (!bag.equals(item)) {
                                if (bag.isRightCollision(item)) {
                                    return true;
                                }
                            }
                            if (checkWallCollision(bag, RIGHT_COLLISION, player)) {
                                return true;
                            }
                        }
                        bag.move(SPACE, 0);
                        isCompleted();
                    }
                }
                return false;
            case TOP_COLLISION:
                for (int i = 0; i < baggs.size(); i++) {
                    Baggage bag = baggs.get(player).get(i);
                    if (soko[0].isTopCollision(bag)) {
                        for (int j = 0; j < baggs.get(player).size(); j++) {
                            Baggage item = baggs.get(player).get(j);
                            if (!bag.equals(item)) {
                                if (bag.isTopCollision(item)) {
                                    return true;
                                }
                            }
                            if (checkWallCollision(bag, TOP_COLLISION, player)) {
                                return true;
                            }
                        }
                        bag.move(0, -SPACE);
                        isCompleted();
                    }
                }
                return false;
            case BOTTOM_COLLISION:
                for (int i = 0; i < baggs.size(); i++) {
                    Baggage bag = baggs.get(player).get(i);
                    if (soko[0].isBottomCollision(bag)) {
                        for (int j = 0; j < baggs.size(); j++) {
                            Baggage item = baggs.get(player).get(j);
                            if (!bag.equals(item)) {
                                if (bag.isBottomCollision(item)) {
                                    return true;
                                }
                            }
                            if (checkWallCollision(bag,BOTTOM_COLLISION, player)) {
                                return true;
                            }
                        }
                        bag.move(0, SPACE);
                        isCompleted();
                    }
                }
                break;
            default:
                break;
        }
        return false;
    }
    public void isCompleted() {//게임 끝
        nOfBags = baggs.size();
        finishedBags = 0;
        for (int i = 0; i < nOfBags; i++) {
            Baggage bag = baggs.get(player).get(i);
            for (int j = 0; j < nOfBags; j++) {
                Area area =  areas.get(player).get(j);
                if (bag.x() == area.x() && bag.y() == area.y()) {//짐이 area 좌표의 위치와 같으면(잘 놨으면)
                    finishedBags += 1;
                    score[0] += 1000;
                }
            }
        }
    }
    public void restartLevel() {
        score[0] = 0;
        time[0] = timeto;
        areas.clear();
        baggs.clear();
        walls.clear();
        if(modStatue == TwoPLAYER){
            setDraw();
        }else{
            setOffsetPosition();
            initWorld();
            nOfBags = baggs.size();
        }
        if (isCompleted) {
            isCompleted = false;
        }
    }
}