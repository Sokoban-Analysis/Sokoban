package com.zetcode.panel;

import com.zetcode.frame.PanelChange;
import com.zetcode.game.Player;
import com.zetcode.game.SoundSystem;
import com.zetcode.tool.MakeButton;
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

public class BoardUI extends JPanel implements ActionListener {

    private Board board;
    private MakeLabel timeLabel;
    private MakeLabel scoreLabel;
    private MakeLabel winnderLabel;
    private URL backButtonpath = getResource("resources/board/back.png");
    private URL backButtonDownpath = getResource("resources/board/down/back.png");
    private URL menuButtonpath = getResource("resources/board/menu.png");
    private URL menuButtonDownpath = getResource("resources/board/down/menu.png");
    private ImageIcon success = new ImageIcon(getResource("resources/board/success.png"));
    private ImageIcon failed = new ImageIcon(getResource("resources/board/failed.png"));
    private MakeButton backButton;
    private MakeButton menuButton;
    private SoundSystem soundBG;
    private SoundSystem resultSound;
    private int result;

    private URL getResource(String path){
        return getClass().getClassLoader().getResource(path);
    }

    public BoardUI(Board board){
        this.board = board;
        setOpaque(false);
        setLayout(null);
        result = RUNNABLE;
        initLabel();
        if(conMod){
            while(true){
                int key = replayFileReader.getNextKey();
                if(key == 0){
                    time = replayFileReader.getFinalTime();
                    break;
                }
                boardKeyListner.keyEventProcess(key);
            }
        }
        runTimer();
        System.out.println(board.panelChange.getContentPane().getComponents().length);
    }

    private void initLabel(){
        timeLabel = new MakeLabel(300, 89, 300, 70, 60f);
        timeLabel.add(this);
        scoreLabel = new MakeLabel(720, 89, 300, 70, 60f);
        scoreLabel.add(this);
        soundBG = new SoundSystem("/resources/background.wav");
        soundBG.loop();
        soundBG.play();
        backButton = new MakeButton(this, backButtonpath, backButtonDownpath);
        backButton.setup(1150, 591, 100, 100, this::actionPerformed);
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
            replayMod = false;
            soundBG.stop();
            scoreTimer.shutdown();
            board.isCompleted = true;
            board.panelChange.StopBoard();
            board.panelChange.changePanel();
            board.removeAll();
            board.validate();
            if(board.replayFileWriter != null) {
                board.replayFileWriter.setContinueFile();
            }
        }
    }


    public void setScore(){
        String scoreStr = "";
        try{
            InputStream in = getClass().getResourceAsStream("/resources/data/score.txt");
            InputStreamReader ir = new InputStreamReader(in);
            BufferedReader  reader = new BufferedReader(ir);
            scoreStr = reader.readLine();
            if(Integer.parseInt(scoreStr) < score){
                File file = new File("src/resources/data/score.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                System.out.println(String.valueOf(score));
                writer.write(String.valueOf(score));
                writer.close();
            }
        }catch (Exception e){}
    }

    private void runTimer(){
        JPanel panel = this;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                time = (time - 0.01);
                timeLabel.setText(String.format("%.2f", time));
                if(replayMod){
                   if(String.format("%.2f", time).equals(replayFileReader.getTime())){
                       boardKeyListner.keyEventProcess(replayFileReader.getNextKey());
                   }
                }else{
                    board.replayFileWriter.saveReplay(String.format("%.2f", time));
                }
                if(score < 0 ){
                    scoreLabel.setText("0");
                }else{
                    score--;
                    scoreLabel.setText(String.valueOf(score));
                }
                if(time < 0){
                    board.isCompleted = true;
                    timeLabel.setText("0.0");
                    soundBG.stop();
                    resultSound = new SoundSystem("/resources/failed.wav");
                    resultSound.play();
                    result = FAILED;
                    menuButton = new MakeButton(panel, menuButtonpath, menuButtonDownpath);
                    menuButton.setup(475, 422, 348, 108, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            board.panelChange.StopBoard();
                            board.panelChange.changePanel();
                            board.removeAll();
                            board.validate();
                        }
                    });
                    repaint();
                    scoreTimer.shutdown();
                }else if(board.finishedBags == board.nOfBags) {
                    if(modStatue == TwoPLAYER){
                        for(int key : board.twoBags.keySet()){
                            if(board.twoBags.get(key) == board.finishedBags){
                                winnderLabel = new MakeLabel(450, 212, 400, 70, 60f);
                                winnderLabel.setText("PLAYER " + key);
                                winnderLabel.add(panel);
                            }
                        }
                    }else{
                        setScore();
                    }
                    try {//게임 성공하면 효과음
                        board.isCompleted = true;
                        soundBG.stop();
                        resultSound = new SoundSystem("/resources/success.wav");
                        resultSound.play();
                        result = SUCCESS;
                        menuButton = new MakeButton(panel, menuButtonpath, menuButtonDownpath);
                        menuButton.setup(475, 422, 348, 108, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                board.panelChange.StopBoard();
                                board.panelChange.changePanel();
                                board.removeAll();
                                board.validate();
                            }
                        });
                        repaint();
                        scoreTimer.shutdown();
                    } catch (Exception e) {
                    }
                }
            }
        };
        scoreTimer.scheduleAtFixedRate(runnable, 0, 10, TimeUnit.MILLISECONDS);
    }
}
