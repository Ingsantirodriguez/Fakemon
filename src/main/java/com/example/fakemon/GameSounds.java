package com.example.fakemon;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GameSounds {
    private String homeMusic;
    private String battleMusic;
    private String endMusic;
    private String selectionMusic;
    private Clip clip;
    private File file;
    private Boolean musicOn = false;
    private String currentMusic = "none";
    FloatControl fc;
    boolean mute=false;
    private float previousVolume=0;
    private float currentVolume=0;

    public GameSounds(){
        homeMusic = "src/main/resources/com/example/fakemon/music/home-fakemon-sound.wav";
        selectionMusic =  "src/main/resources/com/example/fakemon/music/selection-fakemon-sound.wav";
        battleMusic = "path";
        endMusic = "path";
    }

    public void playMusic(String mode) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        currentMusic = mode;
        musicOn = true;
        switch(mode){
            case "home":
                file = new File(homeMusic);
                break;
            case "battle":
                file  = new File(battleMusic);
                break;
            case "end":
                file  = new File(endMusic);
                break;
            case "selection":
                file = new File(selectionMusic);
                break;
            default:
                file  = new File(homeMusic);
                break;
        }
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        fc=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        clip.start();
    }

    public String getMusicOn() {

        return currentMusic;


    }
    public void stopMusic(){
        clip.stop();
        clip.flush();
        clip.close();
        musicOn = false;
        System.out.println("clip no es null");


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
        }else{
            mute=false;
            currentVolume=previousVolume;
            fc.setValue(currentVolume);
        }
    }
}
