package com.example.fakemon;

import com.example.fakemon.fakemons.Fakemon;
import com.example.fakemon.fakemons.Pidgey;
import com.example.fakemon.fakemons.Pikachu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DebilitarTest {

    @Test
    void actuarTest() {
        Fakemon pikachu = new Pikachu();
        Fakemon pidgey = new Pidgey();
        int a = pidgey.getAttackDamage();
        new Debilitar().actuar(pikachu, pidgey);

        assertEquals(a- pikachu.weaken(), pidgey.getAttackDamage());
    }
}