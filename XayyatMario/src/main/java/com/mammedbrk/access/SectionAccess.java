package com.mammedbrk.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mammedbrk.model.Section;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SectionAccess {
    private final ObjectMapper mapper;
    private final String directory = "src/main/resources/com.mammedbrk/game/";

    public SectionAccess() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void add(Section section, int levelId, int sectionId) {
        try {
            mapper.writeValue(new FileWriter(directory + levelId + "/" + sectionId + ".json"), section);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Section get(int levelNo, int sectionNo) {
        Section section;
        try {
            File file = new File(directory + "/" + levelNo + "/" + sectionNo + ".json");
            if (!file.exists()) {
                return null;
            }
            section = mapper.readValue(file, Section.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return section;
    }

    public boolean exists(int levelNo, int sectionNo) {
        File file = new File(directory + "/" + levelNo + "/" + sectionNo + ".json");
        return file.exists();
    }
}
