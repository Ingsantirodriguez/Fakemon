package com.example.fakemon.acciones;

import com.example.fakemon.fakemons.Fakemon;

public class Regenerar implements Accion {
    @Override
    public void actuar(Fakemon activo, Fakemon pasivo) {
        activo.regenerate();
    }
}
