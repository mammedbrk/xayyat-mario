package com.mammedbrk.model;

import com.mammedbrk.model.game.Game;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private int score;
    private int coins;
    private List<Game> games;
    private List<Character> characters;

    public User() {
        games = new ArrayList<>();
        characters = new ArrayList<>();
    }

    public User(int id, String username, String password) {
        this();
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password, int score, int coins, List<Game> games, List<Character> characters) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.score = score;
        this.coins = coins;
        this.games = games;
        this.characters = characters;
    }

    public void maxScore(int value) {
        score = Math.max(score, value);
    }

    public void addCoin(int value) {
        coins += value;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void removeGame(Game game) {
        for (int i = 0; i < games.size(); i++) {
            if (game.equals(games.get(i))) {
                putGame(i, new Game());
            }
        }
    }

    public void putGame(int index, Game game) {
        games.set(index, game);
    }

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
