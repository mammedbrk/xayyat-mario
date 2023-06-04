package com.mammedbrk.model.component;

public abstract class Component {
    protected double x, y;

    public Component() {
    }

    public Component(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Getters and setters

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
