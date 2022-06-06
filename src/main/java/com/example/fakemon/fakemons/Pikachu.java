package com.example.fakemon.fakemons;

public class Pikachu extends Fakemon{

    public Pikachu(){
        super();
        this.life = 100;
        this.sound = "path";
        this.attackDamage = 15;
        this.name = "Pikachu";
        this.stronger = false;
        this.weakened = false;
    }
    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public String getSound() {
        return this.sound;
    }

    @Override
    public int getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Boolean isWeakened() {
        return this.weakened;
    }

    @Override
    public Boolean isStronger() {
        return this.stronger;
    }
}