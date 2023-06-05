package com.mammedbrk.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mammedbrk.model.game.Level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LevelAccess {
    private final ObjectMapper mapper;
    private String directory = "src/main/resources/com.mammedbrk/game/"; // todo

    public LevelAccess() {
        this.mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void add(Level level, int levelNo) {
        try {
            mapper.writeValue(new FileWriter(directory + levelNo + ".json"), level);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Level get(int levelNo) {
        Level level;
        File file = new File(directory + levelNo + ".json");
        if (!file.exists())
            return null;
        try {
            level = mapper.readValue(file, Level.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return level;
    }

    public boolean exists(int levelNo) {
        File file = new File(directory + "/" + levelNo + ".json");
        return file.exists();
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
