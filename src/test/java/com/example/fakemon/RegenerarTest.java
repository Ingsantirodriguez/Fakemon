package com.example.fakemon;

import com.example.fakemon.fakemons.Fakemon;
import com.example.fakemon.fakemons.Pidgey;
import com.example.fakemon.fakemons.Pikachu;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class RegenerarTest {

    @Test
    void actuarTest() throws NoSuchFieldException {

        /********
         *
         *  No terminado: no puedo leer campo protegido incLife
         *  mañana sigo xd
         */

        Fakemon pikachu = new Pikachu();
        Fakemon pidgey = new Pidgey();
        Field incLife = Fakemon.class.getDeclaredField("incLife");
        incLife.setAccessible(true);
        int l = incLife.getModifiers();
        System.out.println("hola" + l);
        new Regenerar().actuar(pikachu, pidgey);
        //assertEquals(pikachu, pikachu.getAttackDamage());
    }
}