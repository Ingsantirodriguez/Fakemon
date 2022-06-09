package com.example.fakemon.fakemons;

public class Pidgey extends Fakemon{
    public Pidgey(){
        super();
        this.sound = "src/main/resources/com/example/fakemon/music/pidgey-sound.wav";
        this.name = "Pidgey";
        this.basicLife = 100;
        this.currentLife = this.basicLife;
        this.attackDamage = 15;
        this.weakenDamage = 5;
        this.originalAttackDamage = this.attackDamage;
        this.incLife = 5;
        this.weakened = false;
    }
}
