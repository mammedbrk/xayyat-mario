package com.mammedbrk.model;

public class Level {
    private int no;
    private int coins;
    private int score;
    private Section currentSection;

    public Level() {
    }

    // Methods

    public void addCoin(int value) {
        coins += value;
    }

    public void addScore(int value) {
        coins += value;
    }

    // Getters and setters

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Section getCurrentSection() {
        return currentSection;
    }

    public void setCurrentSection(Section currentSection) {
        this.currentSection = currentSection;
    }
}
