package com.mammedbrk.model;

import java.util.List;

public class Section {
    private int no;
    private int time;
    private int score;
    private int coins;
    private List<Scene> scenes;
    private int x, y;

    public Section() {
    }

    public Section(int timeLeft) {
        this();
        this.time = timeLeft;
    }

    // Methods

    public void reduceTime() {
        time -= 1;
    }

    public void addScore(int value) {
        score += value;
    }

    public void addCoin(int value) {
        coins += value;
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    // Getter and setters

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
