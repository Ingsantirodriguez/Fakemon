package com.example.fakemon.fakemons;

import com.example.fakemon.Atacar;
import com.example.fakemon.Debilitar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FakemonTest {
    Fakemon pikachu = new Pikachu();
    Fakemon squirtle = new Squirtle();
    @Test
    void recieveAttackTest(){
        int attackDamage = 30;
        pikachu.receiveAttack(attackDamage);
        assertEquals(pikachu.getBasicLife()-attackDamage,pikachu.getCurrentLife());
    }

    @Test
    void regenerateFullLifeTest(){
        int attackDamage = 3;
        pikachu.receiveAttack(attackDamage);
        pikachu.regenerate();
        assertEquals(pikachu.getBasicLife(),pikachu.getCurrentLife());
    }

    @Test
    void strongerTest(){
        pikachu.maximizeAttack();
        assertTrue(pikachu.isStronger());
        assertEquals(pikachu.getAttackDamage(),pikachu.getAttackDamage()+10);
    }

    @Test
    void weakerTest(){
        int weak = 5;
        pikachu.weakening(weak);
        assertTrue(pikachu.weakened);
        assertEquals(pikachu.getAttackDamage(),pikachu.getAttackDamage()-weak);
    }

    @Test
    void normalAttackAfterStrongAttack(){
        int beforeAttack = pikachu.getAttackDamage();
        pikachu.maximizeAttack();
        new Atacar().actuar(pikachu,squirtle);
        assertEquals(beforeAttack, pikachu.getAttackDamage());
    }

    @Test
    void normalAttackAfterWeakAttack(){
        int beforeAttack = pikachu.getAttackDamage();
        new Debilitar().actuar(squirtle,pikachu);
        new Atacar().actuar(pikachu,squirtle);
        assertEquals(beforeAttack, pikachu.getAttackDamage());
    }
}