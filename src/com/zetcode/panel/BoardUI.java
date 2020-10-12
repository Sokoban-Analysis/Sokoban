package com.zetcode.panel;

import com.zetcode.frame.PanelChange;
import com.zetcode.tool.MakeButton;
import com.zetcode.tool.MakeLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private MakeButton backButton;

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
        board.getScoreLabel(scoreLabel.getLabel());
        backButton = new MakeButton(this, backButtonpath, backButtonDownpath);
        backButton.setup(1150, 591, 100, 100, this::actionPerformed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==backButton.getButton()){
            board.panelChange.StopBoard();
            board.panelChange.changePanel();
        }
    }



    private void runTimer(){
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
                    board.isCompleted();
                }
            }
        };
        scoreTimer.scheduleAtFixedRate(runnable, 0, 100, TimeUnit.MILLISECONDS);
    }
}
