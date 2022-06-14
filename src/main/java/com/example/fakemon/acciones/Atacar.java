package com.example.fakemon.acciones;

import com.example.fakemon.acciones.Accion;
import com.example.fakemon.fakemons.Fakemon;

public class Atacar implements Accion {
    @Override
    public void actuar(Fakemon activo, Fakemon pasivo) {
        pasivo.receiveAttack(activo.getAttackDamage());
    }
}
