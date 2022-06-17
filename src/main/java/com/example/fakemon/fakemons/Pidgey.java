package com.example.fakemon.fakemons;

public class Pidgey extends Fakemon{
    public Pidgey(){
        super();
        this.sound = "src/main/resources/com/example/fakemon/music/pidgey-sound.wav";
        this.imgPath = "src/main/resources/com/example/fakemon/images/Pidgey.png";
        this.name = "Pidgey";
        this.basicLife = 100;
        this.currentLife = this.basicLife;
        this.lastLife = this.basicLife;
        this.attackDamage = 15;
        this.weakenDamage = 5;
        this.originalAttackDamage = this.attackDamage;
        this.incLife = 5;
        this.weakened = false;
        this.maximizeDamage=10;
    }
}
