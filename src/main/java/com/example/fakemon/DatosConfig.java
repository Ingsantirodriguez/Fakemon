package com.example.fakemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DatosConfig {

    public static String nombre = "";
    public static String genero = "Femenino";
    public static int volumen = 50;

    public static String fakemonElegido ;
    public static String fakemonOponente ;

    public static ArrayList<String> fakemons = new ArrayList<>();

    public static void selecRandomFakemon(){
        fakemons.remove(fakemonElegido);
        Random rd = new Random();
        int value = rd.nextInt(fakemons.size());
        fakemonOponente = fakemons.get(value);
        System.out.println(fakemonOponente);
        fakemons.remove(value);
    }

    public static void emptyFakemons(){
        if (!fakemons.isEmpty()){
            for (int i=0; i<fakemons.size();){
                fakemons.remove(i);
            }
        }

    }

    public static void fillFakemons(){
        String[] f = {"Pikachu", "Bulbasaur", "Charmander", "Jigglypuff", "Pidgey", "Squirtle"};
        fakemons.addAll(Arrays.asList(f));
    }

    public static String getNombre() {
        return nombre;
    }
}
