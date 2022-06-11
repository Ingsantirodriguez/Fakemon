package com.example.fakemon;

import com.example.fakemon.fakemons.Fakemon;

public class Debilitar implements Accion{
    @Override
    public void actuar(Fakemon activo, Fakemon pasivo) {
        pasivo.weakening(activo.weaken());
    }

}
