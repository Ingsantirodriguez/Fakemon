package com.example.fakemon;

import com.example.fakemon.fakemons.Fakemon;
import com.example.fakemon.fakemons.Pidgey;
import com.example.fakemon.fakemons.Pikachu;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class PotenciarTest {

    @Test
    void actuarTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Fakemon pikachu = new Pikachu();
        Fakemon pidgey = new Pidgey();
        int a = pikachu.getAttackDamage();
        Method method = Fakemon.class.getDeclaredMethod("getIncAttack");
        method.setAccessible(true);
        int b = (Integer) method.invoke(pikachu);
        new Potenciar().actuar(pikachu, pidgey);
        assertEquals(a+b, pikachu.getAttackDamage());
    }
}