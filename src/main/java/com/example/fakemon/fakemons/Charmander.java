package com.example.fakemon.fakemons;

public class Charmander extends Fakemon{
    public Charmander(){
        super();
        this.sound = "src/main/resources/com/example/fakemon/music/charmander-sound.wav";
        this.imgPath = "src/main/resources/com/example/fakemon/images/Charmander.png";
        this.name = "Charmander";
        this.basicLife = 100;
        this.currentLife = this.basicLife;
        this.lastLife = this.basicLife;
        this.attackDamage = 18;
        this.weakenDamage = 5;
        this.originalAttackDamage = this.attackDamage;
        this.incLife = 5;
        this.weakened = false;
        this.maximizeDamage=7;
    }
}
