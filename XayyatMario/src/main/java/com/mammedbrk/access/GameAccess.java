package com.mammedbrk.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mammedbrk.model.game.Game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameAccess {
    private final ObjectMapper mapper;
    private String directory = "json/game/"; // todo

    public GameAccess() {
        this.mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void add(Game game) {
        try {
            mapper.writeValue(new FileWriter(directory + (lastId()+1) + ".json"), game);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Game get(int id) {
        Game game;
        File file = new File(directory + id + ".json");
        if (!file.exists())
            return null;
        try {
            game = mapper.readValue(file, Game.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return game;
    }

    public boolean exists(int id) {
        File file = new File(directory + "/" + id + ".json");
        return file.exists();
    }

    public int lastId() {
        File dir = new File(directory);
        File[] files = dir.listFiles();
        if (files == null) {
            return 0;
        }
        return files.length;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
