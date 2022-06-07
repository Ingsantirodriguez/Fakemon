package com.example.fakemon.batalla;

import com.example.fakemon.fakemons.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Batalla {
    private HashMap<String, Fakemon> fakemons;
//    private String usrFakemon;
//    private String botFakemon;
    private Fakemon usrFakemon;
    private Fakemon botFakemon;

    public static String getTurno() {
        return Turno;
    }



    private static String Turno;

    public Batalla(){
        this.fakemons = new HashMap<>();
        this.usrFakemon = null;
        this.botFakemon = null;
        RandomTurno();
    }

    private void RandomTurno() {
        Random rd = new Random();
        int value = rd.nextInt(2);
        if (value == 0) {
            this.Turno = "user";
        } else {
            this.Turno = "bot";
        }
    }

    public void setUsrFakemon(String fakemon){
        this.usrFakemon = fakemons.get(fakemon);
    }

    public void setBotFakemon(String fakemon){
        this.botFakemon = fakemons.get(fakemon);
    }

    public Fakemon getUsrFakemon(){
            return usrFakemon;
    }

    public Fakemon getBotFakemon(){
            return botFakemon;
    }

    public void selecRandomFakemon(){
        fakemons.remove(this.usrFakemon.getName());     // elimino el user fakemon
        Random rd = new Random();                       // elijo un numero random
        int value = rd.nextInt(fakemons.size());        // entre 1 y 5
        int cont = 0;                                   //
        for(Fakemon f: fakemons.values()){              // recorro el hash map hasta que value==cont
            if(value==cont){
                this.setBotFakemon(f.getName());        // obtengo el fakemon bot (rival)
                fakemons.remove(f.getName());           // elimino el rival de la lista
                break;                                  // salgo del for
            }
            cont++;                                     // si no encontre el rival, sigo buscando
        }
    }

    public void emptyFakemons(){

        if (!fakemons.isEmpty()){
            // vaciar hash
        }
    }
    public void fillFakemons(){
        Fakemon[] val = {new Bulbasaur(), new Charmander(), new Jigglypuff()
                ,new Pidgey(), new Squirtle(), new Pikachu()};
        String[] key = {"Bulbasaur", "Charmander", "Jigglypuff", "Pidgey", "Squirtle", "Pikachu"};

        for(int i = 0; i<6; i++){
            fakemons.put(key[i], val[i]);
        }
    }

}
