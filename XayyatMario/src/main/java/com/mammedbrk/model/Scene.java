package com.mammedbrk.model;

import com.mammedbrk.model.component.Component;

import java.util.List;

public class Scene {
    private int no;
    private List<Component> components;

    public Scene() {
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    // Getters and setters


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
