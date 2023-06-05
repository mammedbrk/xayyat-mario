package com.mammedbrk.model.game;


import com.mammedbrk.model.component.Mario;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Level> levels;
    private int hearts;
    private int marioState;
    private Mario mario;
    private int score;
    private int coins;
    private int checkpointSaves;
    private int levelNo;
    private int sectionNo;

    public Game() {
        levels = new ArrayList<>();
        hearts = 3; // todo
        marioState = 0;
        mario = new Mario();
        score = 0;
        coins = 0;
        checkpointSaves = 0;
        levelNo = 0;
        sectionNo = 0;
    }

    public Game(List<Level> levels, int hearts, int marioState) {
        this.levels = levels;
        this.hearts = hearts;
        this.marioState = marioState;
        mario = new Mario();
        score = 0;
        coins = 0;
        checkpointSaves = 0;
        levelNo = 0;
        sectionNo = 0;
    }

    public Game(List<Level> levels, int hearts, int marioState, Mario mario, int score, int coins, int checkpointSaves, int levelNo, int sectionNo) {
        this.levels = levels;
        this.hearts = hearts;
        this.marioState = marioState;
        this.mario = mario;
        this.score = score;
        this.coins = coins;
        this.checkpointSaves = checkpointSaves;
        this.levelNo = levelNo;
        this.sectionNo = sectionNo;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public void addCoin(int value) {
        coins += value;
    }

    public void addScore(int value) {
        score += value;
    }

    public void increaseCheckpointSaves() {
        checkpointSaves++;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public int getHearts() {
        return hearts;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public int getMarioState() {
        return marioState;
    }

    public void setMarioState(int marioState) {
        this.marioState = marioState;
    }

    public Mario getMario() {
        return mario;
    }

    public void setMario(Mario mario) {
        this.mario = mario;
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

    public int getCheckpointSaves() {
        return checkpointSaves;
    }

    public void setCheckpointSaves(int checkpointSaves) {
        this.checkpointSaves = checkpointSaves;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public int getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(int sectionNo) {
        this.sectionNo = sectionNo;
    }
}
