package com.mammedbrk.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int numOfLives;
    private int totalScore;
    private int numOfCoins;
    private Character character;
    private List<Section> sections;

    public Game() {
        numOfLives = 3;
        character = new Character();
        sections = new ArrayList<>();
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

    public void addSection(Section section) {
        sections.add(section);
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

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
