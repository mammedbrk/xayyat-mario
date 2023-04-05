package com.mammedbrk.model;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private int timeLeft;
    private int score;
    private int numOfCoins;
    private List<Scene> scenes;

    public Section() {
        scenes = new ArrayList<>();
    }

    public Section(int timeLeft) {
        this();
        this.timeLeft = timeLeft;
    }

    // Methods

    public void reduceTileLeft() {
        timeLeft -= 1;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void addNumOfCoins(int coin) {
        numOfCoins += coin;
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    // Getter and setters

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumOfCoins() {
        return numOfCoins;
    }

    public void setNumOfCoins(int numOfCoins) {
        this.numOfCoins = numOfCoins;
    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }
}
