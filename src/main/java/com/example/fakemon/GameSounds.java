package com.example.fakemon;
import com.example.fakemon.fakemons.Fakemon;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class GameSounds {
    private Clip clip;
    private File file;
    private Boolean musicOn = false;
    private String currentMusic = "none";
    FloatControl fc;
    private boolean mute=false;
    private float previousVolume=0;
    private float currentVolume=0;
    private HashMap<String, String> mode;

    public GameSounds(){
        mode = new HashMap<>(){{
           put("home", "src/main/resources/com/example/fakemon/music/home-fakemon-sound.wav");
           put("selection", "src/main/resources/com/example/fakemon/music/selection-fakemon-sound.wav");
        }};
    }

    public void playMusic(String mode)  {
        String url = "";
        currentMusic = mode;
        musicOn = true;

        if (this.mode.containsKey(mode)) {
            url = this.mode.get(mode);
            this.startMusic(url);
        } else {
            System.out.println("No hay sonido para el modo '"+mode+"'.");
        }
    }

    public void playMusic(Fakemon f){
        String url = f.getSound();
        currentMusic = f.getName();
        musicOn = true;
        this.startMusic(url);
    }

    private void startMusic(String url){
        if(!mute){
            try {
                file = new File(url);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                fc=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                fc.setValue(currentVolume);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
                e.printStackTrace();
            }finally {
                System.out.println("ERROR. No se inicio la musica.");
            }
        }
    }

    public String getMusicOn() {

        return currentMusic;
    }
    public void stopMusic(){
        clip.stop();
        clip.flush();
        clip.close();
        musicOn = false;
    }

    public Boolean musicOn(){
        return musicOn;
    }

    public void volumeUp(){
        currentVolume+=4.0f;
        if(currentVolume>6.0f){
            currentVolume=6.0f;
        }
        fc.setValue(currentVolume);
    }

    public void volumeDown(){
        currentVolume-=4.0f;
        if(currentVolume<-80.0f){
            currentVolume=-80.0f;
        }
        fc.setValue(currentVolume);
    }

    public void muteSound(){
        if(!mute) {
            mute=true;
            previousVolume = currentVolume;
            currentVolume=-80.0f;
            fc.setValue(currentVolume);
        }
        else{
            mute=false;
            currentVolume=previousVolume;
            fc.setValue(currentVolume);
        }
    }

    public float getCurrentVolume(){
        return currentVolume;
    }

    public boolean isMute(){
        return mute;
    }
}
