package com.example.fakemon.fakemons;

import com.example.fakemon.Observable;
import com.example.fakemon.Observer;
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
    protected String imgPath;
    protected String imgCampeon;
    protected String imgPelea;
    protected boolean weakened;
    protected boolean stronger;
    protected int maximizeDamage;
    ArrayList<Observer> observers;    //lista de observadores

    public Fakemon (){
        observers = new ArrayList<>();
    }

    public int getIncDamage() {
        return maximizeDamage;
    }

    public int getIncLife() {
        return incLife;
    }

    public int getCurrentLife(){
        return currentLife;
    }
    public int getBasicLife(){
        return basicLife;
    }
    public int getLastLife() { return lastLife; }
    public String getSound(){
        return sound;
    }
    public String getImgPath(){ return imgPath; }
    public String getImgCampeon(){ return imgCampeon; }
    public String getImgPelea(){ return imgPelea; }
    public int getAttackDamage(){
        if (weakened){
            weakened = false;
        }
        if (stronger){
            stronger = false;
        }
        int ad = attackDamage;
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
    public void setLastLife(int lastLife) { this.lastLife = lastLife; }
    public void regenerate(){
        if(this.basicLife < this.currentLife+ getIncLife()){
            this.currentLife = this.basicLife;
        }
        else {
            currentLife += getIncLife();
        }
        for(Observer o : observers){
            o.actualizar();
        }

        System.out.println("regenerate..");
    }
    public void receiveAttack(int a){
        System.out.println("danio a aplicar " + a);
        System.out.println("vida antes: " + this.currentLife);
        this.currentLife = this.currentLife - a;
        System.out.println("nueva vida: " + this.currentLife);
        if(this.currentLife < 0){
            this.currentLife = 0;
        }
        for(Observer o : observers){
            o.actualizar();
        }
        System.out.println(observers.size());
    }
    public void maximizeAttack(){
        attackDamage = attackDamage + getIncDamage();
        stronger = true;
    }

    public void addObserver(Observer observer){
        observers.add(observer);
        System.out.println("observer " + observer.getClass().toString());
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.actualizar();
        }
    }

    public void resetFakemon(){
        this.currentLife = basicLife;
        this.attackDamage = originalAttackDamage;
        System.out.println("Vida: " + this.currentLife + "\nAtaque: " + this.attackDamage);
    }

}
