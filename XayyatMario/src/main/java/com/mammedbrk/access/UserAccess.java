package com.mammedbrk.access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mammedbrk.model.User;

import java.io.*;

public class UserAccess {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String directory = "json/user/";

    public void add(User user) {
        try {
            FileWriter writer = new FileWriter(directory + user.getId() + ".json");
            gson.toJson(user, writer);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User get(int id) {
        File file = new File(directory + id + ".json");
        if (!file.exists()) {
            return null;
        }
        try {
            FileReader reader = new FileReader(file);
            return gson.fromJson(reader, User.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public User get(String username) {
        File dir = new File(directory);
        File[] files = dir.listFiles();
        if (files == null) {
            return null;
        }
        for (File file : files) {
            try {
                User user = gson.fromJson(new FileReader(file), User.class);
                if (user.getUsername().equals(username)) {
                    return user;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public int lastId() {
        int id = 0;
        File dir = new File(directory);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                try {
                    id = Math.max(id, gson.fromJson(new FileReader(file), User.class).getId());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return id;
    }
}
