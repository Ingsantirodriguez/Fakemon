package com.example.fakemon.fakemons;

public class Pikachu extends Fakemon {

    public Pikachu() {
        super();
        this.sound = "src/main/resources/com/example/fakemon/music/pikachu-sound.wav";
        this.name = "Pikachu";
        this.basicLife = 100;
        this.currentLife = this.basicLife;
        this.attackDamage = 15;
        this.weakenDamage = 5;
        this.originalAttackDamage = this.attackDamage;
        this.incLife = 5;
        this.weakened = false;
    }
}