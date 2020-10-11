package com.zetcode;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundSystem {
    private Clip clip;
    private AudioInputStream stream;
    private File file;

    public SoundSystem(File file){
        this.file = file;
        InitSound();
    }

    private void InitSound(){
        try {
            stream = AudioSystem.getAudioInputStream(file);
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
