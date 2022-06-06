package com.example.fakemon.batalla;

import com.example.fakemon.fakemons.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Batalla {
    private ArrayList<Fakemon> fakemons;
    private String usrFakemon;
    private String botFakemon;

    public Batalla(){
        this.fakemons = new ArrayList<>();
        this.usrFakemon = "none";
        this.botFakemon = "none";
    }

    public void setUsrFakemon(String fakemon){
        this.usrFakemon = fakemon;
    }

    public void setBotFakemon(String fakemon){
        this.botFakemon = fakemon;
    }

    public String getUsrFakemon(){
            return usrFakemon;
    }

    public String getBotFakemon(){
            return botFakemon;
    }

    public void selecRandomFakemon(){
        fakemons.removeIf(f -> f.getName().equals(usrFakemon));
        Random rd = new Random();
        int value = rd.nextInt(fakemons.size());
        botFakemon = fakemons.get(value).getName();
        System.out.println(botFakemon);
        fakemons.remove(value);
    }

    public void emptyFakemons(){
        if (!fakemons.isEmpty()){
            for (int i=0; i<fakemons.size();){
                fakemons.remove(i);
            }
        }
    }
    public void fillFakemons(){
        Fakemon[] f = {new Bulbasaur(), new Charmander(), new Jigglypuff()
                ,new Pidgey(), new Squirtle(), new Pikachu()};
        fakemons.addAll(Arrays.asList(f));
    }

}
