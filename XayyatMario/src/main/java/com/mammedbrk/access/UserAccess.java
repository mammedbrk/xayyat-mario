package com.mammedbrk.access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mammedbrk.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<User> getAll() {
        List<User> returnList = new ArrayList<>();
        File dir = new File(directory);
        File[] files = dir.listFiles();
        if (files == null) {
            return returnList;
        }
        for (File file: files) {
            try {
                returnList.add(gson.fromJson(new FileReader(file), User.class));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return returnList;
    }

    public int lastId() {
        File dir = new File(directory);
        File[] files = dir.listFiles();
        if (files == null) {
            return 0;
        }
        return files.length;
    }
}
