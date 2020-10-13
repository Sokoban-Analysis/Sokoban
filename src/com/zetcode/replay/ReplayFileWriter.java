package com.zetcode.replay;

import com.zetcode.game.SoundSystem;
import com.zetcode.tool.MakeButton;
import com.zetcode.tool.MakeLabel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static com.zetcode.var.StaticVar.*;
import static com.zetcode.var.StaticVar.scoreTimer;

public class ReplayFileWriter {

    private int keyCode;
    private boolean[] saveStatue = new boolean[3];
    private int player;
    private File[] file = new File[3];

    public ReplayFileWriter(String level, int duration){
        if(modStatue == ReplayMod) return;
        LocalDateTime dateTime = LocalDateTime.now();
        String time = dateTime.getYear() +"." + dateTime.getMonthValue() +"." + dateTime.getDayOfMonth()
                +"." + dateTime.getHour() +"." + dateTime.getMinute() +"." + dateTime.getSecond();
        if(modStatue == TwoPLAYER){
            file[1] = new File("src/resources/data/replay/"+ time +"-1.txt");
            file[2] = new File("src/resources/data/replay/"+ time +"-2.txt");
            try { file[1].createNewFile(); file[2].createNewFile();} catch (IOException e) { }
            saveStatue[1] = false;
            saveStatue[2] = false;
        }else{
            file[0] = new File("src/resources/data/replay/"+ time +"-0.txt");
            try { file[0].createNewFile();} catch (IOException e) { }
            saveStatue[0] = false;
            System.out.println(file[0].getName());
        }
        keyCode = 0;
        fileWrite(level.replaceAll("[^0-9]",""));
        fileWrite(String.valueOf(duration));
    }

    public void setPlayer(int player){
        if(modStatue == ReplayMod) return;
        this.player = player;
    }

    public void setKetCode(int keyCode){
        if(modStatue == ReplayMod) return;
        this.keyCode = keyCode;
        this.saveStatue[player] = true;
    }

    public void fileWrite(String str){
        try{
            FileWriter writer = new FileWriter(file[player], true);
            writer.write(str + "\n");
            writer.close();
            saveStatue[player] = false;
        }catch (Exception e){}
    }

    public void saveReplay(String time){
        if(modStatue == ReplayMod) return;
        if(saveStatue[player]){
            fileWrite(keyCode + "-"+ score + "-" + time);
        }
    }
}
