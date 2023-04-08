package com.mammedbrk.access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mammedbrk.model.Character;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CharacterAccess {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String directory = "src/main/resources/com.mammedbrk/character/";

    public Character get(String name) {
        File file = new File(directory + name + ".json");
        if (!file.exists()) {
            return null;
        }
        try {
            FileReader reader = new FileReader(file);
            return gson.fromJson(reader, Character.class);
        } catch (FileNotFoundException e) {
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
                returnList.add(gson.fromJson(new FileReader(file), Character.class));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return returnList;
    }
}
