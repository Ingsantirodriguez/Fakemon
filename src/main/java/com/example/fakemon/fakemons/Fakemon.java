package com.example.fakemon.fakemons;

import com.example.fakemon.Observable;
import com.example.fakemon.Observer;
import com.sun.javafx.collections.ElementObservableListDecorator;
import com.sun.javafx.collections.ObservableSetWrapper;

import java.util.ArrayList;

public abstract class Fakemon implements Observable {


    protected int currentLife;
    protected int incLife;
    protected int basicLife;
    protected int lastLife;
    protected int attackDamage;
    protected int originalAttackDamage;       // valor de daño reducido luego de ser debilitado por el enemigo
    protected int weakenDamage;         // valor de reduccion de ataque al enemigo
    protected String sound;
    protected String name;
    protected boolean weakened;
    protected boolean stronger;
    //lista de observadores
    ArrayList<Observer> observers = new ArrayList<>();

    public Fakemon (){



    }

    public int getCurrentLife(){
        return currentLife;
    }
    public int getBasicLife(){
        return basicLife;
    }
    public int getLastLife(){return lastLife;}
    public void setLastLife(int lastLife){this.lastLife = lastLife;}
    public String getSound(){
        return sound;
    }
    public int getAttackDamage(){
        if (weakened){
            weakened = false;
        }
        if (stronger){
            stronger = false;
        }
        int ad = attackDamage;
        attackDamage = originalAttackDamage;
        return ad;

    }
    public String getName(){
        return name;
    }
    public Boolean isWeakened(){
        return weakened;
    }
    public Boolean isStronger(){
        return stronger;
    }
    public int weaken(){    // debilito al enemigo
        return weakenDamage;
    }
    public void weakening(int d) {  // recibe debilitacion del enemigo
        if (d < attackDamage) {
            attackDamage -= d;
        }
        weakened = true;
    }
    public void regenerate(){
        if(this.basicLife < this.currentLife+this.incLife){
            this.currentLife = this.basicLife;
            for(Observer o : observers){
                o.actualizar();
            }
        }
        else currentLife += incLife;
        System.out.println("regenerate..");
    }
    public void receiveAttack(int a){
        this.currentLife -= a;


        if(this.currentLife < 0){
            this.currentLife = 0;
        }
        for(Observer o : observers){
            o.actualizar();
        }
        System.out.println(observers.size());
    }
    public void maximizeAttack(){
        attackDamage += 10;
        stronger = true;
    }


    public void addObserver(Observer observer){

        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.actualizar();
        }
    }


}
