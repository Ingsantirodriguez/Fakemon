package com.example.fakemon.fakemons;

public enum ListFakemons {
    Bulbasaur, Charmander, Jigglypuff, Pidgey, Pikachu, Squirtle;

    public static Boolean contains(String s){
        for(ListFakemons f: ListFakemons.values()){
            if(f.toString().equals(s))
                return true;
        }
        return false;
    }
}
