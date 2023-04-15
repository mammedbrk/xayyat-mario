package com.mammedbrk.model;

public class Character {
    private String name;
    private String imageAddress;
    private double speed;
    private double jumpAbility;
    private double collectAbility;
    private double shootAbility;
    private int price;

    public Character() {
    }

    public Character(String name, String imageAddress) {
        this.name = name;
        this.imageAddress = imageAddress;
    }

    public Character(String name, String imageAddress, int speed, int jumpAbility, int collectAbility, int shootAbility, int price) {
        this.name = name;
        this.imageAddress = imageAddress;
        this.speed = speed;
        this.jumpAbility = jumpAbility;
        this.collectAbility = collectAbility;
        this.shootAbility = shootAbility;
        this.price = price;
    }

    // Methods

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        return this.name.equals(((Character) obj).name);
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getJumpAbility() {
        return jumpAbility;
    }

    public void setJumpAbility(double jumpAbility) {
        this.jumpAbility = jumpAbility;
    }

    public double getCollectAbility() {
        return collectAbility;
    }

    public void setCollectAbility(double collectAbility) {
        this.collectAbility = collectAbility;
    }

    public double getShootAbility() {
        return shootAbility;
    }

    public void setShootAbility(double shootAbility) {
        this.shootAbility = shootAbility;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
