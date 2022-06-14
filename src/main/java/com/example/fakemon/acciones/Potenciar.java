package com.example.fakemon.acciones;

import com.example.fakemon.acciones.Accion;
import com.example.fakemon.fakemons.Fakemon;

public class Potenciar implements Accion {
    @Override
    public void actuar(Fakemon activo, Fakemon pasivo) {
        activo.maximizeAttack();
    }
}
