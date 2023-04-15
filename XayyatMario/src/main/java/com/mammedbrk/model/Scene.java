package com.mammedbrk.model;

import com.mammedbrk.model.gamecomponent.Tile;

import java.util.List;

public class Scene {
    private int no;
    private List<Tile> components;

    public Scene() {
    }

    // Methods

    public void addComponent(Tile component) {
        components.add(component);
    }

    // Getters and setters


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public List<Tile> getComponents() {
        return components;
    }

    public void setComponents(List<Tile> components) {
        this.components = components;
    }
}
