package com.example.fakemon;

import com.example.fakemon.fakemons.Fakemon;

public class Potenciar implements Accion{
    @Override
    public void actuar(Fakemon activo, Fakemon pasivo) {
        activo.maximizeAttack();
    }
}
