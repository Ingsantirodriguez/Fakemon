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

    public GameSounds(){
        homeMusic = "src/main/resources/com/example/fakemon/music/home-fakemon-sound.wav";
        selectionMusic =  "src/main/resources/com/example/fakemon/music/selection-fakemon-sound.wav";
        battleMusic = "path";
        endMusic = "path";
    }

    public void playMusic(String mode) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file;

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
        clip.start();
    }

    public void stopMusic(){
        clip.stop();
    }
}
