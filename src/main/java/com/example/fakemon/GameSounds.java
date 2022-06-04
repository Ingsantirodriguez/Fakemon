package com.example.fakemon;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GameSounds {
    private String homeMusic;
    private String pikachuSound;
    private String battleMusic;
    private String endMusic;
    private String selectionMusic;
    private Clip clip;
    private File file;
    private float currentVolume;
    private float previousVolume;
    private FloatControl fc;
    private boolean isMuted;

    public GameSounds(){
        homeMusic = "src/main/resources/com/example/fakemon/music/home-fakemon-sound.wav";
        selectionMusic =  "src/main/resources/com/example/fakemon/music/selection-fakemon-sound.wav";
        battleMusic = "path";
        endMusic = "path";
        pikachuSound = "src/main/resources/com/example/fakemon/music/pikachu-sound.wav";
        currentVolume=0.0f;
        previousVolume=0.0f;
        isMuted=false;
    }

    public void playMusic(String mode) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

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
            case "pikachu":
                file = new File(pikachuSound);
                break;
            default:
                file  = new File(homeMusic);
                break;
        }
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        fc=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
    }

    public void subirVolumen(){
        currentVolume+=4.0f;
        if(currentVolume>6.0f){
            currentVolume=6.0f;
        }
        fc.setValue(currentVolume);
    }

    public void bajarVolumen(){
        currentVolume-=4.0f;
        if(currentVolume<-80.0f){
            currentVolume=-80.0f;
        }
        fc.setValue(currentVolume);
    }

    public void Mutear(){
        if(!isMuted){
            isMuted=true;
            previousVolume=currentVolume;
            clip.stop();
        }else{
            isMuted=false;
            currentVolume=previousVolume;
            clip.start();
            fc.setValue(currentVolume);
        }
    }

    public void stopMusic(){
        clip.stop();
    }
}
