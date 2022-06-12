package com.example.fakemon.batalla;

import com.example.fakemon.fakemons.Fakemon;
import java.util.ArrayList;
import static com.example.fakemon.DatosConfig.nombre;

public abstract class Torneo{
    protected Fakemon usrFakemon;
    protected Fakemon botFakemon;
    protected ArrayList<Fakemon> vsUsrWin;
    protected ArrayList<Fakemon> vsUsrLoose;
    protected int battle_n;

    public Torneo(){
        super();
        this.vsUsrWin = new ArrayList<>();
        this.vsUsrLoose = new ArrayList<>();
        this.battle_n = 1;
    }

    public void nextBattle(){
        this.battle_n++;
    }
    public void showResults(){
        System.out.println("\n*****  RESULTADOS  *****"
                            + "\n"
                            + "\nUsuario: " + nombre
                            + "\nFakemon: " + this.usrFakemon
                            + "\nVictorias: " + this.vsUsrWin.size() + "(vs {" + vsUsrWin.stream().toString() + "})"
                            + "\nDerrotas: " + this.vsUsrLoose.size() + "(vs {" + vsUsrLoose.stream().toString() + "})" 
        );
    }

    public String getInstance(){
        return "BATALLA N° " + this.battle_n;
    }

}
