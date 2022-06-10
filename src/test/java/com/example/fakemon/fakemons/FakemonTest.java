package com.example.fakemon.fakemons;

import com.example.fakemon.Atacar;
import com.example.fakemon.Debilitar;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FakemonTest extends TestCase {

    @Test
    void recieveAttackTest(){
        Fakemon fakemon = new Pikachu();
        int attackDamage = 30;
        fakemon.receiveAttack(attackDamage);
        assertEquals(fakemon.getBasicLife()-attackDamage,fakemon.getCurrentLife());
    }

    @Test
    void regenerateFullLifeTest(){
        Fakemon fakemon = new Pikachu();
        int attackDamage = 3;
        fakemon.receiveAttack(attackDamage);
        fakemon.regenerate();
        assertEquals(fakemon.getBasicLife(),fakemon.getCurrentLife());
    }

    @Test
    void strongerTest(){
        Fakemon fakemon = new Pikachu();
        fakemon.maximizeAttack();
        assertTrue(fakemon.isStronger());
        assertEquals(fakemon.getAttackDamage(),fakemon.getAttackDamage()+10);
    }

    @Test
    void weakerTest(){
        Fakemon fakemon = new Pikachu();
        int weak = 5;
        fakemon.weakening(weak);
        assertTrue(fakemon.weakened);
        assertEquals(fakemon.getAttackDamage(),fakemon.getAttackDamage()-weak);
    }

    @Test
    void normalAttackAfterStrongAttack(){
        Fakemon pikachu = new Pikachu();
        Fakemon squirtle = new Squirtle();
        int beforeAttack = pikachu.getAttackDamage();
        pikachu.maximizeAttack();
        new Atacar().actuar(pikachu,squirtle);
        assertEquals(beforeAttack, pikachu.getAttackDamage());
    }

    @Test
    void normalAttackAfterWeakAttack(){
        Fakemon pikachu = new Pikachu();
        Fakemon squirtle = new Squirtle();
        int beforeAttack = pikachu.getAttackDamage();
        new Debilitar().actuar(squirtle,pikachu);
        new Atacar().actuar(pikachu,squirtle);
        assertEquals(beforeAttack, pikachu.getAttackDamage());
    }
}