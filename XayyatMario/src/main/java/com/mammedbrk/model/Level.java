package com.mammedbrk.model;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<Section> sections;

    public Level() {
        sections = new ArrayList<>();
    }

    // Getters and setters

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
