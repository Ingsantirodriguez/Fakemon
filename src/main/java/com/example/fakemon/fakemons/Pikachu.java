package com.example.fakemon.fakemons;

public class Pikachu extends Fakemon {

    public Pikachu() {
        super();
        this.sound = "src/main/resources/com/example/fakemon/music/pikachu-sound.wav";
        this.imgPath = "src/main/resources/com/example/fakemon/images/Pikachu.png";
        this.imgCampeon = "src/main/resources/com/example/fakemon/gif/pikachu-ganador.gif";
        this.imgPelea = "src/main/resources/com/example/fakemon/gif/pikachu-ataca.gif";
        this.name = "Pikachu";
        this.basicLife = 100;
        this.currentLife = this.basicLife;
        this.lastLife = this.basicLife;
        this.attackDamage = 20;
        this.weakenDamage = 5;
        this.originalAttackDamage = this.attackDamage;
        this.incLife = 5;
        this.weakened = false;
        this.maximizeDamage=8;
    }
}