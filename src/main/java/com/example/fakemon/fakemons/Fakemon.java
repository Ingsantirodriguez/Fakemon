package com.example.fakemon.fakemons;

public abstract class Fakemon {
    protected int currentLife;
    protected int incLife;
    protected int basicLife;
    protected int attackDamage;
    protected int basicAttackDamage;
    protected int originalAttackDamage;       // valor de daño reducido luego de ser debilitado por el enemigo
    protected int weakenDamage;         // valor de reduccion de ataque al enemigo
    protected String sound;
    protected String name;
    protected boolean weakened;
    protected boolean stronger;
    protected int incAttack;

    public Fakemon(){ }

    private int getBasicAttackDamage(){
        return basicAttackDamage;
    }
    private int getIncAttack(){
        return incAttack;
    }
    public int getCurrentLife(){
        return currentLife;
    }
    public int getBasicLife(){
        return basicLife;
    }
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
        }
        else currentLife += incLife;
        System.out.println("regenerate..");
    }
    public void receiveAttack(int a){
        this.currentLife -= a;

        if(this.currentLife < 0){
            this.currentLife = 0;
        }
    }
    public void maximizeAttack(){
        attackDamage += incAttack;
        stronger = true;
    }

}
