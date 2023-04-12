package com.mammedbrk.model;

import com.mammedbrk.model.gamecomponent.GameComponent;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private int no;
    transient private Section section;
    private List<GameComponent> components;

    public Scene() {
        components = new ArrayList<>();
    }

    // Methods

    public void addComponent(GameComponent component) {
        components.add(component);
    }

    // Getters and setters

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public List<GameComponent> getComponents() {
        return components;
    }

    public void setComponents(List<GameComponent> components) {
        this.components = components;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
