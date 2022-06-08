package com.example.fakemon.fakemons;

public class Bulbasaur extends Fakemon{
    public Bulbasaur(){
        super();
        this.sound = "src/main/resources/com/example/fakemon/music/bulbasaur-sound.wav";
        this.name = "Bulbasaur";
        this.basicLife = 100;
        this.currentLife = this.basicLife;
        this.attackDamage = 15;
        this.weakenDamage = 5;
        this.originalAttackDamage = this.attackDamage;
        this.incLife = 5;
        this.weakened = false;
    }
    @Override
    public int getCurrentLife() {
        return this.currentLife;
    }

    @Override
    public int getBasicLife() {
        return this.basicLife;
    }

    @Override
    public String getSound() {
        return this.sound;
    }

    @Override
    public int getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Boolean isWeakened() {
        return this.weakened;
    }

    @Override
    public Boolean isStronger() {
        return this.stronger;
    }

    @Override
    public int weaken() {
        return this.weakenDamage;
    }

    @Override
    public void weakening(int d) {
        if (d < this.attackDamage) {
            this.attackDamage -= d;
        }
    }

    @Override
    public void regenerate() {
//        if(this.basicLife > this.currentLife){
//            this.currentLife += this.incLife;
//            System.out.println("regenerate..");
//        }
        this.currentLife += this.incLife;
        System.out.println("regenerate..");
    }

    @Override
    public void receiveAttack(int a) {
        this.currentLife -= a;

        if(this.currentLife < 0){
            this.currentLife = 0;
        }
    }

    @Override
    public void maximizeAttack() {
        this.attackDamage += 10;
    }

}
