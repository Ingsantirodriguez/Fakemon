package com.example.fakemon.fakemons;

import com.example.fakemon.batalla.Batalla;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

class TorneoTest extends TestCase {
    private Batalla batalla = new Batalla();    // Torneo es abstract, pero Batalla hereda

    @Test
    void instanciaBatalla(){
        String test = "BATALLA N° 1";
        assertEquals(batalla.getInstance(), test);

    }
}
