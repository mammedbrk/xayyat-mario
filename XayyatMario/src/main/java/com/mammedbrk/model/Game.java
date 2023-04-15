package com.mammedbrk.model;


public class Game {
    private int hearts;
    private int coins;
    private int score;
    private Character character;

    private Level currentLevel;

    public Game() {
    }

    // Methods

    public void reduceHeart() {
        hearts -= 1;
    }

    public void addCoin(int value) {
        coins += value;
    }

    public void addScore(int value) {
        score += value;
    }

    // Getters and setters

    public int getHearts() {
        return hearts;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
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

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}
