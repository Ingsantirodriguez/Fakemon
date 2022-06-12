package com.example.fakemon;

import com.example.fakemon.fakemons.Pikachu;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class GameSoundsTest {

    GameSounds sound = new GameSounds();

    @Test
    void playMusicTest() {
        sound.playMusic("selection");
        // lo siguiente se debe cumplir
        assertTrue(sound.musicOn());
        assertEquals("selection", sound.getMusicOn());
        sound.playMusic("idk");
    }

//    @Test
//    void startMusicTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Method method = GameSounds.class.getDeclaredMethod("startMusic", String.class);
//        method.setAccessible(true);
//        method.invoke(sound, "fake-path");
//    }

    @Test
    void playMusicTest2() {
        sound.playMusic(new Pikachu());
        // lo siguiente se debe cumplir
        assertTrue(sound.musicOn());
        assertEquals("Pikachu", sound.getMusicOn());
    }

    @Test
    void getMusicOnTest() {
        // pruebas solo al instanciar la clase y ejecutar su constructor
        assertEquals("none", sound.getMusicOn());
        // indico modo de pantalla para reproducir la musica especificada y verifico
        sound.playMusic("home");
        assertEquals("home", sound.getMusicOn());
        // verifico que la musica en reproduccion no sea "selection"
        assertNotEquals("selection", sound.getMusicOn());
        // verifico que la musica se apague
        sound.stopMusic();
        assertFalse(sound.musicOn());
    }

    @Test
    void stopMusicTest() {
        sound.playMusic("home");
        assertTrue(sound.musicOn());
        sound.stopMusic();
        assertFalse(sound.musicOn());
    }

    @Test
    void musicOnTest() {
        // verifico que la musica este encendida
        assertFalse(sound.musicOn());
        sound.playMusic("home");
        assertTrue(sound.musicOn());
    }

    @Test
    void volumeUpTest() {
        // el sonido llega hasta 6, se incrementa cada 4. Primero, tengo que enceder musica
        sound.playMusic("home");
        for(int i=0; i<10; i++)
            sound.volumeUp();

        assertEquals(6.0, sound.getCurrentVolume());
    }

    @Test
    void volumeDownTest() {
        // el sonido llega hasta 6, se incrementa cada 4. Primero, tengo que enceder musica
        sound.playMusic("home");

        for(int i=0; i<10; i++)
            sound.volumeDown();

        assertEquals(-40.0, sound.getCurrentVolume());

        for(int i=0; i<30; i++)
            sound.volumeDown();
    }

    @Test
    void muteSoundTest() {
        sound.playMusic("home");
        assertFalse(sound.isMute());
        sound.muteSound();
        assertTrue(sound.isMute());
        sound.muteSound();
        sound.muteSound();
    }

    @Test
    void getCurrentVolumeTest(){
        assertEquals(0.0, sound.getCurrentVolume());
        sound.playMusic("home");
        sound.volumeUp();
        assertEquals(4.0, sound.getCurrentVolume());
    }

    @Test
    void isMuteTest(){
        assertFalse(sound.isMute());
        sound.playMusic("home");
        sound.muteSound();
        assertTrue(sound.isMute());
    }


}
