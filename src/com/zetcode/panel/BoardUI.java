package com.zetcode.panel;

import com.zetcode.frame.PanelChange;
import com.zetcode.game.SoundSystem;
import com.zetcode.tool.MakeButton;
import com.zetcode.tool.MakeLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.zetcode.var.StaticVar.*;

public class BoardUI extends JPanel implements ActionListener {

    private Board board;
    private MakeLabel timeLabel;
    private MakeLabel scoreLabel;
    private String backButtonpath = "src/resources/board/back.png";
    private String backButtonDownpath = "src/resources/board/down/back.png";
    private String menuButtonpath = "src/resources/board/menu.png";
    private String menuButtonDownpath = "src/resources/board/down/menu.png";
    private ImageIcon success = new ImageIcon("src/resources/board/success.png");
    private ImageIcon failed = new ImageIcon("src/resources/board/failed.png");
    private MakeButton backButton;
    private MakeButton menuButton;
    private SoundSystem soundBG;
    private SoundSystem resultSound;
    private int result = RUNNABLE;

    public BoardUI(Board board){
        this.board = board;
        setOpaque(false);
        setLayout(null);
        initLabel();
        runTimer();
    }

    private void initLabel(){
        timeLabel = new MakeLabel(330, 89, 200, 70, 60f);
        timeLabel.add(this);
        scoreLabel = new MakeLabel(760, 89, 300, 70, 60f);
        scoreLabel.add(this);
        soundBG = new SoundSystem(new File("src/resources/background.wav"));
        soundBG.play();
        backButton = new MakeButton(this, backButtonpath, backButtonDownpath);
        backButton.setup(1150, 591, 100, 100, this::actionPerformed);
        menuButton = new MakeButton(this, menuButtonpath, menuButtonDownpath);
        menuButton.setup(475, 422, 348, 108, this::actionPerformed);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(result == SUCCESS){
            g.drawImage(success.getImage(), 204,289, 888,108,this);
        }else if(result == FAILED){
            g.drawImage(failed.getImage(), 204,289, 888,108,this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==backButton.getButton()){
            board.panelChange.StopBoard();
            board.panelChange.changePanel();
        }else if(e.getSource()==menuButton.getButton()){
            board.panelChange.StopBoard();
            board.panelChange.changePanel();
        }
        menuButton = new MakeButton(this, menuButtonpath, menuButtonDownpath);
        menuButton.setup(475, 422, 348, 108, this::actionPerformed);
    }

    public void setScore(){
        File file = new File("src/resources/data/score.txt");
        String scoreStr = "";
        try{
            FileReader file_reader = new FileReader(file);
            BufferedReader br = new BufferedReader(file_reader);
            scoreStr = br.readLine();
            if(Integer.parseInt(scoreStr) < score[0]){
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                System.out.println(String.valueOf(score[0]));
                writer.write(String.valueOf(score[0]));
                writer.close();
            }
        }catch (Exception e){}
    }

    private void runTimer(){
        JPanel panel = this;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                time[0] = (time[0] - 0.1);
                timeLabel.setText(String.format("%.1f", time[0]));
                if(score[0] < 0 ){
                    scoreLabel.setText("0");
                }else{
                    score[0]--;
                    scoreLabel.setText(String.valueOf(score[0]));
                }
                if(time[0] < 0){
                    scoreTimer.shutdown();
                    timeLabel.setText("0.0");
                    resultSound = new SoundSystem(new File("src/resources/failed.wav"));
                    resultSound.play();
                    soundBG.stop();
                    result = FAILED;
                    repaint();
                    scoreTimer.shutdown();
                }else if(board.finishedBags == board.nOfBags) {
                    board.isCompleted = true;
                    try {//게임 성공하면 효과음
                        setScore();
                        resultSound = new SoundSystem(new File("src/resources/success.wav"));
                        resultSound.play();
                        soundBG.stop();
                        result = SUCCESS;
                        repaint();
                        scoreTimer.shutdown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        scoreTimer.scheduleAtFixedRate(runnable, 0, 100, TimeUnit.MILLISECONDS);
    }
}
