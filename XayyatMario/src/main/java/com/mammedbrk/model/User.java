package com.mammedbrk.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private int maxScore;
    private int numOfCoins;
    private List<Game> games;
    private List<Character> characters;

    public User() {
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        games = new ArrayList<>();
        characters = new ArrayList<>();
    }

    // Methods

    public void changeNumOfCoinsBy(int change) {
        numOfCoins += change;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void removeGame(Game game) {
        games.remove(game);
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public Character chosenCharacter() {
        for (Character character: characters) {
            if (character.isChosen())
                return character;
        }
        return null;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getNumOfCoins() {
        return numOfCoins;
    }

    public void setNumOfCoins(int numOfCoins) {
        this.numOfCoins = numOfCoins;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
