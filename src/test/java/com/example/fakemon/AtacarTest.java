package com.example.fakemon;

import com.example.fakemon.fakemons.Fakemon;
import com.example.fakemon.fakemons.Pidgey;
import com.example.fakemon.fakemons.Pikachu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtacarTest {

    @Test
    void actuarTest() {
        Fakemon pikachu = new Pikachu();
        Fakemon pidgey = new Pidgey();
        new Atacar().actuar(pikachu, pidgey);

        assertEquals(pidgey.getBasicLife()-pikachu.getAttackDamage(), pidgey.getCurrentLife());
    }
}