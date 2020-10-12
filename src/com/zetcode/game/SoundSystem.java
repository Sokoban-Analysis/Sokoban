package com.zetcode.game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

public class SoundSystem {
    private Clip clip;
    private AudioInputStream stream;
    private String filename;
    private InputStream bufferedIn;

    public SoundSystem(String filename){
        this.filename = filename;
        InitSound();
    }

    private void InitSound(){
        try {
            stream = AudioSystem.getAudioInputStream(getClass().getResource(filename));
            clip = AudioSystem.getClip();
            clip.open(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void play(){
        clip.start();
    }
    public void stop(){
        clip.stop();
        clip.close();
    }
    public void loop(){
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }


}
