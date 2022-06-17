package com.example.fakemon.fakemons;

public class Squirtle extends Fakemon{
    public Squirtle(){
        super();
        this.sound = "src/main/resources/com/example/fakemon/music/squirtle-sound.wav";
        this.imgPath = "src/main/resources/com/example/fakemon/images/Squirtle.png";
        this.name = "Squirtle";
        this.basicLife = 100;
        this.currentLife = this.basicLife;
        this.lastLife = this.basicLife;
        this.attackDamage = 17;
        this.weakenDamage = 5;
        this.originalAttackDamage = this.attackDamage;
        this.incLife = 8;
        this.weakened = false;
        this.maximizeDamage=5;
    }
}
