package com.mammedbrk.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mammedbrk.model.Scene;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SceneAccess {
    private final ObjectMapper mapper;
    private final String directory = "src/main/resources/com.mammedbrk/game/";

    public SceneAccess() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void add(Scene scene, int levelNo, int sectionNo) {
        try {
            mapper.writeValue(new FileWriter(directory + levelNo + "/" + sectionNo + "/" + scene.getNo() + ".json"), scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Scene get(int levelNo, int sectionNo, int sceneNo) {
        Scene scene;
        try {
            scene = mapper.readValue(new File(directory + "/" + levelNo + "/" + sectionNo + "/" + sceneNo + ".json"), Scene.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return scene;
    }
}
