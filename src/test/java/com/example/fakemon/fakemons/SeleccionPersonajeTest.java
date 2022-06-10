package com.example.fakemon.fakemons;
import com.example.fakemon.Controlador;
import javafx.event.ActionEvent;
import jdk.jfr.Event;
import jdk.jfr.EventType;
import org.junit.jupiter.api.Test;
import com.example.fakemon.ControladorSeleccionPersonaje;


import java.io.IOException;

import static com.example.fakemon.Controlador.*;
import static org.junit.jupiter.api.Assertions.*;
public class SeleccionPersonajeTest {
    @Test
    void seleccionBulbasur() throws IOException {
        ControladorSeleccionPersonaje controlador = new ControladorSeleccionPersonaje();
        Controlador.getBattle().emptyFakemons();
        Controlador.getBattle().fillFakemons();
        controlador.SeleccionasteBulbasaur();
        assertEquals(Controlador.getBattle().getUsrFakemon().getName(), "Bulbasaur");


    }
    @Test
    void seleccionPikachu() throws IOException {
        ControladorSeleccionPersonaje controlador = new ControladorSeleccionPersonaje();
        Controlador.getBattle().emptyFakemons();
        Controlador.getBattle().fillFakemons();
        controlador.SeleccionastePikachu();
        assertEquals(Controlador.getBattle().getUsrFakemon().getName(), "Pikachu");

    }

    @Test
    void seleccionCharmander() throws IOException {
        ControladorSeleccionPersonaje controlador = new ControladorSeleccionPersonaje();
        Controlador.getBattle().emptyFakemons();
        Controlador.getBattle().fillFakemons();
        controlador.SeleccionasteCharmander();
        assertEquals(Controlador.getBattle().getUsrFakemon().getName(), "Charmander");

    }

    @Test
    void seleccionPidgey() throws IOException {
        ControladorSeleccionPersonaje controlador = new ControladorSeleccionPersonaje();
        Controlador.getBattle().emptyFakemons();
        Controlador.getBattle().fillFakemons();
        controlador.SeleccionastePidgey();
        assertEquals(Controlador.getBattle().getUsrFakemon().getName(), "Pidgey");

    }

    @Test
    void seleccionSquirtle() throws IOException {
        ControladorSeleccionPersonaje controlador = new ControladorSeleccionPersonaje();
        Controlador.getBattle().emptyFakemons();
        Controlador.getBattle().fillFakemons();
        controlador.SeleccionasteSquirtle();
        assertEquals(Controlador.getBattle().getUsrFakemon().getName(), "Squirtle");

    }






}
