package com.mammedbrk.model;

public class Character {
    private String name;
    private String imageAddress;
    private int speed;
    private int jumpAbility;
    private int collectAbility;
    private int shootAbility;
    private boolean chosen;

    public Character() {
    }

    public Character(String name, String imageAddress) {
        this.name = name;
        this.imageAddress = imageAddress;
    }

    public Character(String name, String imageAddress, int speed, int jumpAbility, int collectAbility, int shootAbility) {
        this.name = name;
        this.imageAddress = imageAddress;
        this.speed = speed;
        this.jumpAbility = jumpAbility;
        this.collectAbility = collectAbility;
        this.shootAbility = shootAbility;
    }


    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getJumpAbility() {
        return jumpAbility;
    }

    public void setJumpAbility(int jumpAbility) {
        this.jumpAbility = jumpAbility;
    }

    public int getCollectAbility() {
        return collectAbility;
    }

    public void setCollectAbility(int collectAbility) {
        this.collectAbility = collectAbility;
    }

    public int getShootAbility() {
        return shootAbility;
    }

    public void setShootAbility(int shootAbility) {
        this.shootAbility = shootAbility;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }
}
