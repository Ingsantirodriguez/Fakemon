package com.example.fakemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DatosConfig {

    public static String nombre = "";
    public static String genero = "Femenino";
    public static double volumen = 50;

    public static String fakemonElegido ;
    public static String fakemonOponente ;

    public static ArrayList<String> fakemons = new ArrayList<>();

    public static String getNombre(){
        return nombre;
    }

    public static String getGenero(){
        return genero;
    }

    public static double getVolumen(){
        return volumen;
    }

    public static void setNombre(String name){
        nombre = name;
    }

    public static void setGenero(String gender){
        genero = gender;
    }

    public static void setVolumen(double volume){
        volumen = volume;
    }

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
            fakemons.clear();
        }
    }

    public static void fillFakemons(){
        String[] f = {"Pikachu", "Bulbasaur", "Charmander", "Jigglypuff", "Pidgey", "Squirtle"};
        fakemons.addAll(Arrays.asList(f));
    }
}
