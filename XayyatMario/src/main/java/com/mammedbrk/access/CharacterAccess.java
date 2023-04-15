package com.mammedbrk.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mammedbrk.model.Character;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterAccess {
    private final ObjectMapper mapper;
    private final String directory = "src/main/resources/com.mammedbrk/character/";

    public CharacterAccess() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public Character get(String name) {
        File file = new File(directory + name + ".json");
        if (!file.exists()) {
            return null;
        }
        try {
            return mapper.readValue(file, Character.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Character> getAll() {
        List<Character> returnList = new ArrayList<>();
        File dir = new File(directory);
        File[] files = dir.listFiles();
        if (files == null) {
            return returnList;
        }
        for (File file: files) {
            try {
                returnList.add(mapper.readValue(file, Character.class));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return returnList;
    }

    public void add(Character character) {
        try {
            mapper.writeValue(new FileWriter(directory + character.getName() + ".json"), character);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
