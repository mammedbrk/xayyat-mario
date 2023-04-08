package com.mammedbrk.access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mammedbrk.model.Character;
import com.mammedbrk.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterAccess {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String directory = "src/resources/character/";

    public void add(Character character) {
        try {
            FileWriter writer = new FileWriter(directory + character.getName() + ".json");
            gson.toJson(character, writer);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Character get(String name) {
        File dir = new File(directory);
        File[] files = dir.listFiles();
        if (files == null) {
            return null;
        }
        for (File file : files) {
            try {
                Character character = gson.fromJson(new FileReader(file), Character.class);
                if (character.getName().equals(name)) {
                    return character;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
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
                returnList.add(gson.fromJson(new FileReader(file), Character.class));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return returnList;
    }
}
