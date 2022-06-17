package com.example.fakemon.fakemons;

public class Bulbasaur extends Fakemon {
    public Bulbasaur() {
        super();
        this.sound = "src/main/resources/com/example/fakemon/music/bulbasaur-sound.wav";
        this.imgPath = "src/main/resources/com/example/fakemon/images/Bulbasaur.png";
        this.name = "Bulbasaur";
        this.basicLife = 100;
        this.currentLife = this.basicLife;
        this.lastLife = this.basicLife;
        this.attackDamage = 15;
        this.weakenDamage = 5;
        this.originalAttackDamage = this.attackDamage;
        this.incLife = 5;
        this.weakened = false;
    }
}