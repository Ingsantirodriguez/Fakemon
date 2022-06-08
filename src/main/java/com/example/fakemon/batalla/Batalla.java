package com.example.fakemon.batalla;

import com.example.fakemon.fakemons.*;

import java.util.HashMap;
import java.util.Random;

public class Batalla {
    private Torneo torneo;
    private HashMap<String, Fakemon> fakemons;
    private Fakemon usrFakemon;
    private Fakemon botFakemon;
    private float currentUsrLife;
    private float currentBotLife;

    private Boolean usrTurn;
    private Fakemon winner;

    public Batalla(){
        this.fakemons = new HashMap<>();
        this.usrFakemon = null;
        this.botFakemon = null;
        this.usrTurn = true;
        RandomTurno();
    }

    private void RandomTurno() {
        Random rd = new Random();
        int value = rd.nextInt(2);
        if (value == 0) {
            this.usrTurn = false;
        } else {
            this.usrTurn = true;
        }
    }

    public void setUsrTurn(Boolean t){
        this.usrTurn = t;
    }
    public void setUsrFakemon(String fakemon){
        this.usrFakemon = fakemons.get(fakemon);
        fakemons.remove(this.usrFakemon.getName());     // elimino el user fakemon de la lista
        this.currentUsrLife = usrFakemon.getCurrentLife();
        this.torneo = new Torneo(this.usrFakemon);
    }
    public void setWinner(Fakemon f){
        this.winner = f;
        this.torneo.setWinner(f.equals(this.usrFakemon));
        printResults();
        selecRandomFakemon();
        RandomTurno();
    }

    public void printResults(){
        System.out.println("\n*****  GANADOR: "+this.winner.getName()+"  *****");
        System.out.println("\nVida Restante: " + this.winner.getCurrentLife());
        System.out.println("\nAtaque: " + this.winner.getAttackDamage());
        System.out.println("\nBien ahi kpo");
    }

    public void setBotFakemon(String fakemon){
        this.botFakemon = fakemons.get(fakemon);
        this.currentBotLife = usrFakemon.getCurrentLife();
        this.torneo.nextBattle(this.botFakemon);
    }

    public Fakemon getUsrFakemon(){
            return usrFakemon;
    }

    public Fakemon getBotFakemon(){
            return botFakemon;
    }

    public Float getCurrentUsrLife(){ return this.currentUsrLife; }
    public Float getCurrentBotLife(){ return this.currentBotLife; }

    public Boolean usrTurno(){
        return usrTurn;
    }

    public void selecRandomFakemon(){
        Random rd = new Random();                       // elijo un numero random
        int value = rd.nextInt(fakemons.size());        // entre 1 y n
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
