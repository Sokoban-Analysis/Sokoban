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
    private boolean saveStatue;
    private File file;

    public ReplayFileWriter(String level, int duration){
        if(replayMod) return;
        LocalDateTime dateTime = LocalDateTime.now();
        String time = dateTime.getYear() +"." + dateTime.getMonthValue() +"." + dateTime.getDayOfMonth()
                +"." + dateTime.getHour() +"." + dateTime.getMinute() +"." + dateTime.getSecond();
        if(modStatue == TwoPLAYER){
            file = new File("src/resources/data/replay/"+ time +"-2.txt");
        }else{
            file = new File("src/resources/data/replay/"+ time +"-1.txt");
        }
        try { file.createNewFile();} catch (IOException e) { }
        saveStatue = false;
        System.out.println(file.getName());
        keyCode = 0;
        fileWrite(level.replaceAll("[^0-9]",""));
        fileWrite(String.valueOf(duration));
    }


    public void setKetCode(int keyCode){
        if(replayMod) return;
        this.keyCode = keyCode;
        this.saveStatue = true;
    }

    public void fileWrite(String str){
        try{
            FileWriter writer = new FileWriter(file, true);
            writer.write(str + "\n");
            writer.close();
            saveStatue = false;
        }catch (Exception e){}
    }

    public void saveReplay(String time){
        if(replayMod) return;
        if(saveStatue){
            fileWrite(keyCode + "-"+ score + "-" + time);
        }
    }
}
