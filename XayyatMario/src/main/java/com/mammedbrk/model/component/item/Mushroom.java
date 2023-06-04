package com.mammedbrk.model.component.item;

import com.mammedbrk.model.Movable;

public class Mushroom extends Item implements Movable {
    private double speed;
    private int time;

    public Mushroom() {
    }

    public Mushroom(int x, int y) {
        super(x, y);
        speed = 0.5; // todo read from config
    }

    // Methods

    private void increaseTime() {
        time++;
    }

    @Override
    public void move() {
        if (time >= 3) {
            x += speed;
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
