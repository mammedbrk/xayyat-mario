package com.mammedbrk.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mammedbrk.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccess {
    private final ObjectMapper mapper;
    private final String directory = "json/user/";

    public UserAccess() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void add(User user) {
        try {
            mapper.writeValue(new FileWriter(directory + user.getId() + ".json"), user);
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
            return mapper.readValue(file, User.class);
        } catch (IOException e) {
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
                User user = mapper.readValue(file, User.class);
                if (user.getUsername().equals(username)) {
                    return user;
                }
            } catch (IOException e) {
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
                returnList.add(mapper.readValue(file, User.class));
            } catch (IOException e) {
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
