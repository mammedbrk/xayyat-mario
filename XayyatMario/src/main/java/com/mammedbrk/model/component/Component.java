package com.mammedbrk.model.component;

public abstract class Component {
    protected int x, y;

    public Component() {
    }

    public Component(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters and setters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
