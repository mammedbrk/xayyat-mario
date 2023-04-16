package com.mammedbrk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private int maxScore;
    private int coins;
    @JsonIgnore
    private Game currentGame;
    private List<Game> games;
    private Character chosenCharacter;
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

    public void addCoin(int value) {
        coins += value;
    }

    public void putGame(int index, Game game) {
        games.set(index, game);
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void removeGame(Game game) {
        for (int i = 0; i < games.size(); i++) {
            if (game.equals(games.get(i))) {
                putGame(i, new Game());
            }
        }
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void maximizeScore(int value) {
        maxScore = Math.max(maxScore, value);
    }

    // Getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Character getChosenCharacter() {
        return chosenCharacter;
    }

    public void setChosenCharacter(Character chosenCharacter) {
        this.chosenCharacter = chosenCharacter;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
