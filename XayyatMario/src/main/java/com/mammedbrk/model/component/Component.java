package com.mammedbrk.model.component;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Component {
    @JsonIgnore
    public static int SIZE;
    protected double x, y;

    public Component() {
    }

    public Component(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @JsonIgnore
    public abstract String getType();

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

    public static void setSIZE(int SIZE) {
        Component.SIZE = SIZE;
    }
}
