package com.example.fakemon.fakemons;

public abstract class Fakemon {
    protected int currentLife;
    protected int incLife;
    protected int basicLife;
    protected int attackDamage;
    protected int originalAttackDamage;       // valor de daño reducido luego de ser debilitado por el enemigo
    protected int weakenDamage;         // valor de reduccion de ataque al enemigo
    protected String sound;
    protected String name;
    protected boolean weakened;
    protected boolean stronger;

    public Fakemon(){ }

    public abstract int getCurrentLife();
    public abstract int getBasicLife();
    public abstract String getSound();
    public abstract int getAttackDamage();
    public abstract String getName();
    public abstract Boolean isWeakened();
    public abstract Boolean isStronger();
    public abstract int weaken();         // debilito al enemigo
    public abstract void weakening(int d);  // recibe debilitacion del enemigo
    public abstract void regenerate();
    public abstract void receiveAttack(int a);    //
    public abstract void maximizeAttack();

}
