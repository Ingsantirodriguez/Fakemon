package com.example.fakemon;

import com.example.fakemon.batalla.Batalla;
import com.example.fakemon.batalla.Torneo;

public abstract class Controlador {
    protected static GameSounds sonido = new GameSounds();
    protected static Batalla battle = new Batalla();


    public static Batalla getBattle() {
        return battle;
    }

}
