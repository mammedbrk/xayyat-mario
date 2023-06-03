package com.mammedbrk.model.component.item;

import com.mammedbrk.model.Movable;

public class Mushroom extends Item implements Movable {
    private double speed;
    private int time;

    public Mushroom() {
    }

    public Mushroom(int x, int y, double speed) {
        super(x, y);
        this.speed = speed;
    }

    // Methods

    private void increaseTime() {
        time++;
    }

    @Override
    public void move(int dx, int dy) {
        if (time <= 3) {
            x += dx;
            y += dy;
        }
    }

    // Getters and setters

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
