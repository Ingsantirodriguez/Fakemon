package com.example.fakemon;

import com.example.fakemon.batalla.Batalla;
import com.example.fakemon.batalla.Torneo;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class Controlador {
    protected static GameSounds sonido=GameSounds.getInstance();
    protected static Batalla battle = new Batalla();

    public abstract void initialize(URL location, ResourceBundle resources);
}
