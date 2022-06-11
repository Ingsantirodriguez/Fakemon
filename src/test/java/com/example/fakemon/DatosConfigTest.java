package com.example.fakemon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.example.fakemon.DatosConfig.*;

class DatosConfigTest {

    @Test
    void fillFakemonsTest(){
        DatosConfig.fillFakemons();
        assertEquals(6, fakemons.size());
    }

    @Test
    void emptyFakemonsTest(){
        DatosConfig.fillFakemons();
        DatosConfig.emptyFakemons();
        assertEquals(0, fakemons.size());
    }

}