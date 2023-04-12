package com.mammedbrk.model;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private int lives;
    private Character character;
    private List<Level> levels;
    private Scene scene;

    public Game() {
        lives = 3;
        character = new Character();
        levels = new LinkedList<>();
    }

    // Methods

    public int getCoins() {
        int coins = 0;
        for (Level level: levels) {
            coins += level.getCoins();
        }
        return coins;
    }

    public int getScore() {
        int score = 0;
        for (Level level: levels) {
            score += level.getScore();
        }
        return score;
    }

    public void reduceLives() {
        lives -= 1;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    // Getters and setters

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
