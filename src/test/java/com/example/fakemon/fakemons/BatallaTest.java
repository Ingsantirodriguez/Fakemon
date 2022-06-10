package com.example.fakemon.fakemons;

import com.example.fakemon.batalla.Batalla;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BatallaTest {

    Batalla batalla=new Batalla();

    @Test
    void setUsrFakemonTest(){
        batalla.fillFakemons();
        batalla.setUsrFakemon("Pikachu");
        assertTrue(batalla.getUsrFakemon().getName().equals("Pikachu"));
    }

    @Test
    void setBotFakemonTest(){
        batalla.fillFakemons();
        batalla.setBotFakemon("Squirtle");
        assertTrue(batalla.getBotFakemon().getName().equals("Squirtle"));
    }

    @Test
    void emptyFakemonsTest(){
        batalla.fillFakemons();
        batalla.emptyFakemons();
        int i=batalla.num;
        assertEquals(0,i);
    }

    @Test
    void selectRandomFakemonTest(){
        boolean aux=true;
        batalla.fillFakemons();
        batalla.setUsrFakemon("Pikachu");
        batalla.selecRandomFakemon();
        if (batalla.getBotFakemon() instanceof Pikachu) {
            aux=false;
        }
        assertTrue(aux);
    }

    @Test
    void setWinnerTest(){
        boolean usr=true;
        batalla.fillFakemons();
        batalla.setUsrFakemon("Pikachu");
        batalla.setBotFakemon("Squirtle");
        batalla.setWinner(usr);
        assertTrue(batalla.usrWin);
        usr=false;
        batalla.setWinner(usr);
        assertFalse(batalla.usrWin);

    }

}
