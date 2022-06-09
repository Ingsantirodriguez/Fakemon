package com.example.fakemon;

import com.example.fakemon.fakemons.Fakemon;

public class Atacar implements Accion{
    @Override
    public void actuar(Fakemon activo, Fakemon pasivo) {
        pasivo.receiveAttack(activo.getAttackDamage());
    }
}
