package com.mammedbrk.model;

import java.util.LinkedList;
import java.util.List;

public class Section {
    transient private Level level;
    private int time;
    private int score;
    private int coins;
    private List<Scene> scenes;

    public Section() {
        scenes = new LinkedList<>();
    }

    public Section(int timeLeft) {
        this();
        this.time = timeLeft;
    }

    // Methods

    public void reduceTimeByOne() {
        time -= 1;
    }

    public void addScore(int value) {
        score += value;
    }

    public void changeCoinsBy(int value) {
        coins += value;
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    // Getter and setters

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }
}
