package com.example.fakemon.fakemons;

public class Bulbasaur extends Fakemon {
    public Bulbasaur() {
        super();
        this.sound = "src/main/resources/com/example/fakemon/music/bulbasaur-sound.wav";
        this.name = "Bulbasaur";
        this.basicLife = 100;
        this.currentLife = this.basicLife;
        this.basicAttackDamage = 15;
        this.attackDamage = basicAttackDamage;
        this.incAttack = 10;
        this.weakenDamage = 5;
        this.originalAttackDamage = this.attackDamage;
        this.incLife = 5;
        this.weakened = false;
    }
}