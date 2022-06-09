package com.example.fakemon.fakemons;

public class Charmander extends Fakemon{
    public Charmander(){
        super();
        this.sound = "src/main/resources/com/example/fakemon/music/charmander-sound.wav";
        this.name = "Charmander";
        this.basicLife = 100;
        this.currentLife = this.basicLife;
        this.attackDamage = 15;
        this.weakenDamage = 5;
        this.originalAttackDamage = this.attackDamage;
        this.incLife = 5;
        this.weakened = false;
    }
}
