package com.example.fakemon.batalla;

import com.example.fakemon.fakemons.Fakemon;

import java.util.ArrayList;

import static com.example.fakemon.DatosConfig.nombre;

public class Torneo {
    private Fakemon usrFakemon;
    private Fakemon botFakemon;
    private ArrayList<Fakemon> vsUsrWin;
    private ArrayList<Fakemon> vsUsrLoose;
    private int battle_n;
    private boolean usrWin;

    public Torneo(Fakemon usr){
        this.usrFakemon = usr;
        this.battle_n = 0;
    }

    public void nextBattle(Fakemon bot){
        this.botFakemon = bot;
        this.battle_n++;
    }

    public void setWinner(boolean usr){
        if(usr){
            this.vsUsrWin.add(this.botFakemon);
        }else{
            this.vsUsrLoose.add(this.botFakemon);
        }
        if(this.battle_n == 5){
            showResults();
        }
    }

    public void showResults(){
        System.out.println("\n*****  RESULTADOS  *****"
                            + "\n"
                            + "\nUsuario: " + nombre
                            + "\nFakemon: " + this.usrFakemon
                            + "\nVictorias: " + this.vsUsrWin.size()
                            + "\nDerrotas: " + this.vsUsrLoose.size()
        );
    }

}
