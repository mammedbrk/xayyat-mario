package com.mammedbrk.model;

import java.util.LinkedList;
import java.util.List;

public class Level {
    transient private Game game;
    private List<Section> sections;

    public Level() {
        sections = new LinkedList<>();
    }

    // Methods

    public int getCoins() {
        int coins = 0;
        for (Section section: sections) {
            coins += section.getCoins();
        }
        return coins;
    }

    public int getScore() {
        int score = 0;
        for (Section section: sections) {
            score += section.getScore();
        }
        return score;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    // Getters and setters


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
