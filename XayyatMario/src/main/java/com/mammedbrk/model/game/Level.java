package com.mammedbrk.model;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<Section> sections;

    public Level() {
        this.sections = new ArrayList<>();
    }

    public Level(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
