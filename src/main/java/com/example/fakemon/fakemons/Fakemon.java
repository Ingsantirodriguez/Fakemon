package com.example.fakemon.fakemons;

public abstract class Fakemon {
    protected int life;
    protected int attackDamage;
    protected String sound;
    protected String name;
    protected boolean weakened;
    protected boolean stronger;

    public Fakemon(){

    }

    public abstract int getLife();
    public abstract String getSound();
    public abstract int getAttackDamage();
    public abstract String getName();
    public abstract Boolean isWeakened();
    public abstract Boolean isStronger();

}
