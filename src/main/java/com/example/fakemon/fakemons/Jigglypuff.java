package com.example.fakemon.fakemons;

public class Jigglypuff extends Fakemon{
    public Jigglypuff(){
        super();
        this.sound = "src/main/resources/com/example/fakemon/music/jigglypuff-sound.wav";
        this.imgPath = "src/main/resources/com/example/fakemon/images/Jigglypuff.png";
        this.imgCampeon = "src/main/resources/com/example/fakemon/gif/jigglypuff-campeon.gif";
        this.imgPelea = "src/main/resources/com/example/fakemon/gif/jigglypuff-ataque.gif";
        this.name = "Jigglypuff";
        this.basicLife = 100;
        this.currentLife = this.basicLife;
        this.lastLife = this.basicLife;
        this.attackDamage = 70;
        this.weakenDamage = 20;
        this.originalAttackDamage = this.attackDamage;
        this.incLife = 5;
        this.weakened = false;
        this.maximizeDamage=4;
    }
}
