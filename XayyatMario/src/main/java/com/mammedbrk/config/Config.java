package com.mammedbrk.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config extends Properties {
    private static final String CONFIG_PATH = "src/main/resources/com.mammedbrk/config/config.properties";
    private static Config instance;

    private Config() {
        super();
        try {
            FileReader reader = new FileReader(CONFIG_PATH);
            this.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Config getInstance() {
        if (instance == null)
            instance = new Config();
        return instance;
    }
}
