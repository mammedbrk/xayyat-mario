package com.mammedbrk.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int numOfLives;
    private int totalScore;
    private int numOfCoins;
    private Character character;
    private List<Level> levels;

    public Game() {
        numOfLives = 3;
        character = new Character();
        levels = new ArrayList<>();
    }

    // Methods

    public void reduceNumOfLives() {
        numOfLives -= 1;
    }

    public void addScore(int score) {
        totalScore += score;
    }

    public void addNumOfCoins(int coin) {
        numOfCoins += coin;
    }

    public void addSection(Level level) {
        levels.add(level);
    }

    // Getters and setters

    public int getNumOfLives() {
        return numOfLives;
    }

    public void setNumOfLives(int numOfLives) {
        this.numOfLives = numOfLives;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getNumOfCoins() {
        return numOfCoins;
    }

    public void setNumOfCoins(int numOfCoins) {
        this.numOfCoins = numOfCoins;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public List<Level> getSections() {
        return levels;
    }

    public void setSections(List<Level> levels) {
        this.levels = levels;
    }
}
