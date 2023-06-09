package com.mammedbrk.model.component.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Movable;
import com.mammedbrk.model.interfaces.Timer;

public class Mushroom extends Item implements Gravitational, Movable, Timer {
    @JsonIgnore
    private static double speed;
    @JsonIgnore
    private double gravity;
    private int time;
    private boolean move;

    public Mushroom() {
    }

    public Mushroom(double x, double y) {
        super(x, y);
    }

    public Mushroom(double x, double y, int time, boolean move) {
        super(x, y);
        this.time = time;
        this.move = move;
    }

    @Override
    public void changeTime() {
        if (time < 3) // todo
            time++;
        else move = true;
    }

    @Override
    public void move() {
        if (move)
            x = x + speed;
    }

    @Override
    public void changeDirection() {
        speed = -speed;
    }

    @Override
    public void applyGravity() {
        gravity = gravity + Current.gravity; // todo
        y += gravity;
    }

    @Override
    public void resetGravity() {
        gravity = 0;
    }

    public static void setSpeed(double speed) {
        Mushroom.speed = speed;
    }
}
