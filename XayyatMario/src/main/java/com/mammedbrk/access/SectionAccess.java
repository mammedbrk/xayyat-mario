package com.mammedbrk.access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mammedbrk.model.Scene;
import com.mammedbrk.model.Section;

import java.io.*;

public class SectionAccess {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String directory = "src/main/resources/com.mammedbrk/game/";

    public Section get(int levelNo, int sectionNo) {
        File file = new File(directory + "/" + levelNo + "/" + sectionNo + ".json");
        if (!file.exists()) {
            return null;
        }
        FileReader reader;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Section section = gson.fromJson(reader, Section.class);
        for (Scene scene: section.getScenes()) {
            scene.setSection(section);
        }
        return section;
    }

    public void add(Section section, int levelNo) {
        try {
            FileWriter writer = new FileWriter(directory + "/" + levelNo + "/" + section.getNo() + ".json");
            gson.toJson(section, writer);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
